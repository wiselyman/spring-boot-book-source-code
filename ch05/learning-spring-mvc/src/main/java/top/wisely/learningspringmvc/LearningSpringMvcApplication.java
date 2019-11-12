package top.wisely.learningspringmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import top.wisely.learningspringmvc.client.PersonClient;
import top.wisely.learningspringmvc.domain.Person;

@SpringBootApplication
public class LearningSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringMvcApplication.class, args);
	}

	@Bean
	CommandLineRunner personClientClr(PersonClient personClient){
		return args -> {
			personClient.save();
			personClient.getOne();
			personClient.findByName();
		};
	}
}
