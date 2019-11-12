package top.wisely.feignclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import top.wisely.feignclient.client.PersonClient;
import top.wisely.feignclient.domain.model.Person;

@SpringBootApplication
@EnableFeignClients
public class FeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}

	@Bean
	CommandLineRunner personClientClr(PersonClient personClient){
		return args -> {
			System.out.println(personClient.save(new Person(1l, "wyf", 35)));
			System.out.println(personClient.getOne(1l));
			personClient.findByAge(35).forEach(System.out::println);
			System.out.println(personClient.delete(1l));
		};
	}
}
