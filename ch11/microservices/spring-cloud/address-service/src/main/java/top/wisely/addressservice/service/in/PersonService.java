package top.wisely.addressservice.service.in;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import top.wisely.addressservice.config.KafkaBindings;
import top.wisely.addressservice.domain.model.Person;
import top.wisely.addressservice.event.PersonSavedEvent;
import top.wisely.addressservice.repository.PersonRepository;

@Service
public class PersonService {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @StreamListener(target = KafkaBindings.PERSON_IN)
    public void updateAddress(PersonSavedEvent event){
        Person person = new Person();
        BeanUtils.copyProperties(event, person);
        personRepository.save(person);
    }

}
