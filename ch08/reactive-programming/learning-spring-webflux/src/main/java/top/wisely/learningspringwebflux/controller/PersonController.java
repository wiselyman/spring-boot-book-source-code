package top.wisely.learningspringwebflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.learningspringwebflux.domain.model.Person;
import top.wisely.learningspringwebflux.repository.PersonRepository;


//@RestController
@RequestMapping("/people")
public class PersonController {

    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> add(@RequestBody Person person){
        return Mono.just(personRepository.save(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Person>> getById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(Mono.just(personRepository.findOne(id)));
    }
    @GetMapping
    public Flux<Person> list(){
        return Flux.fromIterable(personRepository.list());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") Long id) {
        personRepository.delete(id);
        return Mono.empty();
    }
}
