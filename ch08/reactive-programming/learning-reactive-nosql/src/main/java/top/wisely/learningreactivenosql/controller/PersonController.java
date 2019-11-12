package top.wisely.learningreactivenosql.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivenosql.domain.model.Person;
import top.wisely.learningreactivenosql.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {

    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> add(@RequestBody Person person){
        return personRepository.save(person);
    }

    @GetMapping("/{id}")
    public Mono<Person> getById(@PathVariable String id) {
        return personRepository.findById(id);
    }
    @GetMapping
    public Flux<Person> list(){
        return personRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return personRepository.deleteById(id);
    }
}
