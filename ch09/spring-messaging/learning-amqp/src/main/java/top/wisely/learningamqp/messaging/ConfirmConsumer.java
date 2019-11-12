package top.wisely.learningamqp.messaging;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfirmConsumer {
    @RabbitListener(queuesToDeclare = {@Queue(name = "confirm-dest")})
    public void confirmReceived(String confirm){
        System.out.println(confirm);
    }
}

