package top.wisely.learningkafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanCustomizer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.wisely.learningkafka.messaging.MessageEvent;

@SpringBootApplication
@EnableScheduling
@EnableKafkaStreams
public class LearningKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningKafkaApplication.class, args);
	}

	@Bean
	NewTopic topic(){
		return new NewTopic("confirm-topic",1 ,(short) 1);
	}


	@Bean
	public KStream<String, MessageEvent> kStream(StreamsBuilder streamsBuilder){
		KStream<String, MessageEvent> stream = streamsBuilder.stream("my-topic");
		stream.map((key, value) -> {
			value.setName(value.getName().toUpperCase());
			return new KeyValue<>(key,value);
		}).to("another-topic");
		return stream;
	}
}
