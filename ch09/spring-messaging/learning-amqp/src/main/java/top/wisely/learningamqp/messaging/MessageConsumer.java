package top.wisely.learningamqp.messaging;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

//    @RabbitListener(queuesToDeclare = @Queue(name = "my-dest"))
//    @SendTo("confirm-dest")
    public String consume(MessageEvent event){
        System.out.println("在consume方法中处理事件" + event);
        return "接收到了:" + event.toString();
    }

//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(name = "direct-exchange", type = ExchangeTypes.DIRECT),
//            value = @Queue(name = "direct-queue"),
//            key = "some-key"
//    ))
    public void consumeDirect(MessageEvent event){
        System.out.println("在consumeDirect方法中处理事件" + event);
    }

//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(name = "direct-exchange", type = ExchangeTypes.DIRECT),
//            value = @Queue(name = "direct-queue2"),
//            key = "some-key"
//    ))
    public void consumeDirect2(MessageEvent event){
        System.out.println("在consumeDirect2方法中处理事件" + event);
    }


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "topic-exchange", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "topic-queue"),
            key = "some.*.topic"
    ))
    public void consumeTopic(MessageEvent event){
        System.out.println("在consumeTopic方法中处理事件" + event);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = "topic-exchange", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "topic-queue2"),
            key = "#.topic"
    ))
    public void consumeTopic2(MessageEvent event){
        System.out.println("在consumeTopic2方法中处理事件" + event);
    }
}
