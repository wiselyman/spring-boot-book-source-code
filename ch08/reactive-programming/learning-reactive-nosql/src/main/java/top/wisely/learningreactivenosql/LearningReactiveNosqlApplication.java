package top.wisely.learningreactivenosql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.wisely.learningreactivenosql.client.ControllerClient;

@SpringBootApplication
public class LearningReactiveNosqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningReactiveNosqlApplication.class, args);
	}

	@Bean
	CommandLineRunner webClientClr(ControllerClient controllerClient){
		return args -> {
			String id = controllerClient.add1();
			controllerClient.add2();
			Thread.sleep(1000);
			controllerClient.list();
			Thread.sleep(1000);
			controllerClient.delete(id);
			Thread.sleep(1000);
			controllerClient.list();
		};
	}

}
