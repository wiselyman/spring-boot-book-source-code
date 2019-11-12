package top.wisely.learningspringwebflux.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.learningspringwebflux.domain.model.Person;
import top.wisely.learningspringwebflux.repository.PersonRepository;

@Component
public class PersonHandler {
    PersonRepository personRepository;

    public PersonHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Mono<ServerResponse> add(ServerRequest request){
      Mono<Person> personMono = request.bodyToMono(Person.class);
      return personMono.flatMap(person -> ServerResponse
              .status(HttpStatus.CREATED)
              .body(Mono.just(personRepository.save(person)),Person.class));
    }

    public Mono<ServerResponse> getById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse
                .ok()
                .body(Mono.just(personRepository.findOne(id)), Person.class);

    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        personRepository.delete(id);
        return ServerResponse
                .noContent()
                .build(Mono.empty());
    }
}
