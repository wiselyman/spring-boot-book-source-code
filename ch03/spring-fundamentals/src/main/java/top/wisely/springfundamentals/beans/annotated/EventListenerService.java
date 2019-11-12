package top.wisely.springfundamentals.beans.annotated;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import top.wisely.springfundamentals.event.MessageEvent;

//@Component
public class EventListenerService implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("onApplicationEvent接受到了：" + event.getMessage());
    }

}
