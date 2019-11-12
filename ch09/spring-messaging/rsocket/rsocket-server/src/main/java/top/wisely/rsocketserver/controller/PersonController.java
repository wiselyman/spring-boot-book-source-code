package top.wisely.rsocketserver.controller;

import org.reactivestreams.Publisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.rsocketserver.domain.model.Person;
import top.wisely.rsocketserver.repository.PersonRepository;



@Controller
public class PersonController {

    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @MessageMapping("people.findById")
    Mono<Person> getOne(Person person){
        return personRepository.findById(person.getId());
    }

    @MessageMapping("people.findAll")
    Flux<Person> all(Person person){
        return personRepository.findAll();
    }

    @MessageMapping("people.deleteById")
    Mono<Void> delete(Person person){
        return personRepository.deleteById(person.getId());
    }

    @MessageMapping("people.save")
    Flux<Person> save(Publisher<Person> people){
        return personRepository.saveAll(people);
    }

}
