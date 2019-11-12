package top.wisely.learningjms.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "my-dest")
    @SendTo("confirm-dest")
    public String consume(MessageEvent event){
        System.out.println("在consume方法中处理事件" + event);
        return "接收到了:" + event.toString();
    }

    @JmsListener(destination = "my-dest")
    public void consume2(MessageEvent event){
        System.out.println("在consume2方法中处理事件" + event);
    }
}
