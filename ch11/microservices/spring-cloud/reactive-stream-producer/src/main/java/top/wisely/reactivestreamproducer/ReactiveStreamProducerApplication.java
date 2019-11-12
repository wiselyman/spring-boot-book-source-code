package top.wisely.reactivestreamproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding(Source.class)
public class ReactiveStreamProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveStreamProducerApplication.class, args);
	}

}
