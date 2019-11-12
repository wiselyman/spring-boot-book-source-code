package top.wisely.rsocketclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import top.wisely.rsocketclient.client.ControllerClient;

@SpringBootApplication
public class RsocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsocketClientApplication.class, args);
	}

	@Bean
	RSocketRequester rSocketRequester(RSocketRequester.Builder builder){
		return builder
				.connectTcp("localhost", 9898)
				.block();
	}

	@Bean
	CommandLineRunner webclient(ControllerClient client){
		return args -> {
			client.getOne();
			Thread.sleep(1000);
			client.getAll();
			Thread.sleep(1000);
			client.delete();
			Thread.sleep(1000);
			client.save();
			Thread.sleep(1000);
			client.getAll();
		};
	}
}
