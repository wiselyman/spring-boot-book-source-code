package top.wisely.learningspringbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import top.wisely.learningspringbatch.domain.model.Person;
import top.wisely.learningspringbatch.dto.CsvPerson;
import top.wisely.learningspringbatch.listener.MyJobListener;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    @StepScope
    FlatFileItemReader<CsvPerson> itemReader(@Value("#{jobParameters['input.file.name']}") Resource resource){
        return new FlatFileItemReaderBuilder<CsvPerson>()
                .name("从csv中读取数据")
                .resource(resource)
                .linesToSkip(1)
                .targetType(CsvPerson.class)
                .delimited().names("name", "gender", "age")
                .build();
    }

    @Bean
    ItemProcessor<CsvPerson, Person> genderProcessor(){
        return item -> {
            Person person = new Person();
            BeanUtils.copyProperties(item, person);
            if (item.getGender().equals("M")){
                person.setGender("男");
            }else {
                person.setGender("女");
            }
            return person;
        };
    }


    @Bean
    JdbcBatchItemWriter<Person> itemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("insert into person (name, gender, age) values (:name, :gender, :age)").beanMapped()
                .build();
    }


    @SuppressWarnings("unchecked")
    @Bean
    Step csvToMysqlStep(StepBuilderFactory stepBuilderFactory,
                        ItemReader itemReader,
                        ItemProcessor genderProcessor,
                        ItemProcessor validatingItemProcessor,
                        ItemWriter itemWriter){
        return stepBuilderFactory.get("csv导入到myql的step")
                .<CsvPerson, Person>chunk(5)
                .reader(itemReader)
                .processor(genderProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    Job csvToMysqlJob(JobBuilderFactory jobBuilderFactory,
                      Step csvToMysqlStep,
                      MyJobListener myJobListener){
        return jobBuilderFactory.get("导入csv到mysql的任务")
                .start(csvToMysqlStep)
                .listener(myJobListener)
                .build();


    }
}
