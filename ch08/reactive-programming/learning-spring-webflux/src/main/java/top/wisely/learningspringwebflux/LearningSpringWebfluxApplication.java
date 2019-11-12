package top.wisely.learningspringwebflux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.wisely.learningspringwebflux.client.ControllerClient;


@SpringBootApplication
public class LearningSpringWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringWebfluxApplication.class, args);
	}

	@Bean
	CommandLineRunner webClientClr(ControllerClient controllerClient){
		return args -> {
			controllerClient.add1();
			Thread.sleep(1000);
			controllerClient.findOne();
			Thread.sleep(1000);
			controllerClient.add2();
			Thread.sleep(1000);
			controllerClient.list();
			Thread.sleep(1000);
			controllerClient.delete();
			Thread.sleep(1000);
			controllerClient.list();
		};
	}
}
