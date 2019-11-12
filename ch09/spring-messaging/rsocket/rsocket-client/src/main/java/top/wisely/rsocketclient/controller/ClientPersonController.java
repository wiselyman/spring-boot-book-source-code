package top.wisely.rsocketclient.controller;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.rsocketclient.domain.model.Person;

import java.util.List;

@RestController
@RequestMapping("/people")
public class ClientPersonController {
    RSocketRequester rSocketRequester;

    public ClientPersonController(RSocketRequester rSocketRequester) {
        this.rSocketRequester = rSocketRequester;
    }
    @GetMapping("/{id}")
    public Mono<Person> getOne(@PathVariable String id){//"5d03608320802b1b10458227"
        return this.rSocketRequester
                .route("people.findById")
                .data(new Person(id))
                .retrieveMono(Person.class);
    }
    @GetMapping
    public Flux<Person> getAll(){
        return this.rSocketRequester
                .route("people.findAll")
                .data(new Person())
                .retrieveFlux(Person.class);
    }


    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return this.rSocketRequester
                .route("people.deleteById")
                .data(new Person(id))
                .send();
    }

    @PostMapping
    public Flux<Person> save(@RequestBody Flux<Person> personFlux){
        return this.rSocketRequester
                .route("people.save")
                .data(personFlux, Person.class)
                .retrieveFlux(Person.class);
    }
}
