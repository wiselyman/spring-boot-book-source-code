package top.wisely.learningjms.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.Date;
import java.util.UUID;

@Component
public class MessageProducer {

    JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void send(){
        jmsTemplate.convertAndSend("my-dest",
                new MessageEvent(UUID.randomUUID().toString(), "wyf"));
    }

}
