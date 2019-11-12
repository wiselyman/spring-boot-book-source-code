package top.wisely.learningjms.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConfirmConsumer {
    @JmsListener(destination = "confirm-dest")
    public void confirmReceived(String confirm){
        System.out.println(confirm);
    }
}
