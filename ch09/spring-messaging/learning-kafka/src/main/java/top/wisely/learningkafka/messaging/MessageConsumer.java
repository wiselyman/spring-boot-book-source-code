package top.wisely.learningkafka.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = {"my-topic"})
    @SendTo("confirm-topic")
    public String consume(MessageEvent event){
        System.out.println("在consume方法中处理事件" + event);
        return "接收到了:" + event.toString();
    }

    @KafkaListener(topics = {"another-topic"})
    public void consumeAnother(MessageEvent event){
        System.out.println("在consumeAnother方法中处理another-topic中的事件" + event);
    }
}
