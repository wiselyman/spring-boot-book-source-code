package top.wisely.learningreactivesecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivesecurity.domain.model.Person;
import top.wisely.learningreactivesecurity.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {

    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('personAdd')")
    public Mono<Person> add(@RequestBody Person person){
        return personRepository.save(person);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('personList')")
    public Flux<Person> list(){
        return personRepository.findAll();
    }
}
