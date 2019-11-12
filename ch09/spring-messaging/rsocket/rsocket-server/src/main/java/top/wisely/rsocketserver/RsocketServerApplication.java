package top.wisely.rsocketserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.wisely.rsocketserver.domain.model.Person;
import top.wisely.rsocketserver.repository.PersonRepository;

@SpringBootApplication
public class RsocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsocketServerApplication.class, args);
	}

//	@Bean
	CommandLineRunner initPersonData(PersonRepository personRepository){
		return args -> {
			personRepository.deleteAll().subscribe();
			personRepository.save(new Person("wyf", 35)).subscribe();
			personRepository.save(new Person("foo", 34)).subscribe();
			personRepository.save(new Person("bar", 36)).subscribe();
		};
	}
}
