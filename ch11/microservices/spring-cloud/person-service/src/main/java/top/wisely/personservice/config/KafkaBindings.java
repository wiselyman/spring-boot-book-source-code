package top.wisely.personservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaBindings {

    String ADDRESS_IN = "address-in";
    String PERSON_OUT = "person-out";

    @Input(ADDRESS_IN)
    SubscribableChannel addressIn();

    @Output(PERSON_OUT)
    MessageChannel personOut();
}
