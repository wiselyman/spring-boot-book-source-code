package top.wisely.learningkafka.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageProducer {

    KafkaTemplate<String, MessageEvent> kafkaTemplate;

    public MessageProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void send(){
        kafkaTemplate.send("my-topic","name", new MessageEvent(UUID.randomUUID().toString(), "wyf"));
    }



}
