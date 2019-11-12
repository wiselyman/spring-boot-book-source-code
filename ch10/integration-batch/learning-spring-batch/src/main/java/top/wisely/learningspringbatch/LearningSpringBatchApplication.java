package top.wisely.learningspringbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class LearningSpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBatchApplication.class, args);
	}

	@Bean
	CommandLineRunner jobClr(JobLauncher jobLauncher,
							 Job job){
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addDate("time", new Date())
					.addString("input.file.name","people.csv")
					.toJobParameters();

			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		};
	}

}
