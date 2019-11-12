package top.wisely.springfundamentals.beans.annotated;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import top.wisely.springfundamentals.event.MessageEvent;

@Component
public class EventPublishService {
    ApplicationEventPublisher publisher;

    public EventPublishService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(){
        System.out.println("EventPublishService正在处理，处理完成后通知EvenListenerService继续处理");
        publisher.publishEvent(new MessageEvent("EventPublishService处理完了"));
    }
}
