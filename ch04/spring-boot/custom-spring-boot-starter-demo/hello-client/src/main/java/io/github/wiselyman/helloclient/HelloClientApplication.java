package io.github.wiselyman.helloclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.wisely.GreetingService;

@SpringBootApplication
public class HelloClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(GreetingService greetingService){
		return args -> {
			System.out.println(greetingService.greeting());
		};
	}
}
