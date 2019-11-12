package top.wisely.learningamqp.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageProducer {

    RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @Scheduled(fixedRate = 2000)
    public void send(){
        rabbitTemplate.convertAndSend("my-dest",
                new MessageEvent(UUID.randomUUID().toString(), "wyf"));
    }

//    @Scheduled(fixedRate = 2000)
    public void sendDirect(){
        rabbitTemplate.convertAndSend("direct-exchange", "some-key",
                new MessageEvent(UUID.randomUUID().toString(), "wyf"));
    }

    @Scheduled(fixedRate = 2000)
    public void sendTopic(){
        rabbitTemplate.convertAndSend("topic-exchange", "some.key.topic",
                new MessageEvent(UUID.randomUUID().toString(), "wyf"));
    }
}
