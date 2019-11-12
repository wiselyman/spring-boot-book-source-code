package top.wisely.learningcaching;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import top.wisely.learningcaching.domain.model.Student;
import top.wisely.learningcaching.service.StudentService;


@SpringBootApplication
@EnableCaching
public class LearningCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningCachingApplication.class, args);
	}


	@Bean
	CommandLineRunner cacheableOperation(StudentService studentService){
		return args -> {
			System.out.println("第一次查询，需要查询数据，然后缓存数据，方法被调用，有查询SQL");
			System.out.println(studentService.findOne(1l));
			System.out.println("第一次查询，从缓存中获取数据而不调用方法，下面不会有SQL");
			System.out.println(studentService.findOne(1l));
		};
	}

//	@Bean
	CommandLineRunner cachePutOperation(StudentService studentService){
		return args -> {
			Student student = studentService.saveOrUpdate(new Student("wyf"));
			Long id = student.getId();
			System.out.println("因使用@CachePut已经将key为id的值存入了缓存，下面的查询将不会有SQL");
			System.out.println(studentService.findOne(id));

			student.setName("wangyunfei");
			studentService.saveOrUpdate(new Student("wyf"));
			System.out.println("@CachePut在修改时会被调用，缓存新的值，下面的查询也将不会有SQL");
			System.out.println(studentService.findOne(id));
		};
	}

//	@Bean
	CommandLineRunner cacheEvitOperation(StudentService studentService){
		return args -> {
			studentService.remove(1l);
			System.out.println("通过@CacheEvict删除了key为id的缓存，所以下面的查询会有SQL");
			studentService.findOne(1l);
		};
	}






}
