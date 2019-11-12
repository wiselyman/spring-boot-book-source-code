package top.wisely.learningreactivesql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import top.wisely.learningreactivesql.client.ControllerClient;
import top.wisely.learningreactivesql.database.ReactiveDatabaseInitializer;

import java.awt.*;


@SpringBootApplication
public class LearningReactiveSqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningReactiveSqlApplication.class, args);
	}


	@Bean
	CommandLineRunner webClientClr(ControllerClient controllerClient,
								   ReactiveDatabaseInitializer initializer){
		return args -> {
			initializer.initialize().block();
			Long id = controllerClient.add1();
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
