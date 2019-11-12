package top.wisely.reactivestreamproducer.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.reactive.FluxSender;
import org.springframework.cloud.stream.reactive.StreamEmitter;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.messaging.Source;
import reactor.core.publisher.Flux;
import top.wisely.reactivestreamproducer.event.MessageEvent;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;


@Service
public class ProducerService {

    @StreamEmitter
    public void send(@Output(Source.OUTPUT)FluxSender output){
        output.send(Flux.interval(Duration.ofSeconds(5))
                         .map(i -> new MessageEvent(UUID.randomUUID().toString(),new Date())));
    }
}
