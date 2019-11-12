package top.wisely.addressservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaBindings {

    String PERSON_IN = "person-in";
    String ADDRESS_OUT = "address-out";

    @Input(PERSON_IN)
    SubscribableChannel personIn();

    @Output(ADDRESS_OUT)
    MessageChannel addressOut();
}
