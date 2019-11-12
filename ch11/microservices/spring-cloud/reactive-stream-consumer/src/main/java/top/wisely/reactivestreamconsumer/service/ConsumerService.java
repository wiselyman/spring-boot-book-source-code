package top.wisely.reactivestreamconsumer.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import top.wisely.reactivestreamconsumer.event.MessageEvent;

@Service
public class ConsumerService {

    @StreamListener(target = Sink.INPUT)
    public void consume(Flux<MessageEvent> messageEventFlux){
        messageEventFlux.subscribe(System.out::println);
    }
}
