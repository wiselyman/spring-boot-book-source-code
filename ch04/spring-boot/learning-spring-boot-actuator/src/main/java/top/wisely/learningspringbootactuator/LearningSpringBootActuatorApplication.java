package top.wisely.learningspringbootactuator;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@SpringBootApplication
@RestController
public class LearningSpringBootActuatorApplication {

	@GetMapping("/")
	public String hello(){
		return "Hello Spring Boot Actuator";
	}

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringBootActuatorApplication.class, args);
	}

}
