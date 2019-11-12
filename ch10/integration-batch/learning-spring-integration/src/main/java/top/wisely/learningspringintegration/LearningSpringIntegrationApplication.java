package top.wisely.learningspringintegration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.wisely.learningspringintegration.service.SendingGateway;

@SpringBootApplication
public class LearningSpringIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringIntegrationApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(SendingGateway gateway){
		return args -> {
			gateway.send("greeting.txt","hello world");
			gateway.send("greeting.txt", "hello wyf");
			gateway.send("greeting.txt", "good morning");
			gateway.send("greeting.txt", "hi world");
			gateway.send("greeting.txt", "hi wyf");
		};

	}
}
