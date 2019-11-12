package top.wisely.learningkafka.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConfirmConsumer {
    @KafkaListener(topics = {"confirm-topic"})
    public void confirmReceived(String confirm){
        System.out.println(confirm);
    }
}

