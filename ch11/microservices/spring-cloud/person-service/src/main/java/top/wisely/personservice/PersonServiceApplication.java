package top.wisely.personservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import top.wisely.personservice.config.KafkaBindings;


@SpringBootApplication
@EnableBinding({KafkaBindings.class})
public class PersonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServiceApplication.class, args);
	}


}
