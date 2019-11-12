package top.wisely.learningspringwebflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import top.wisely.learningspringwebflux.controller.PersonHandler;
import top.wisely.learningspringwebflux.domain.model.Person;
import top.wisely.learningspringwebflux.repository.PersonRepository;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutingConfiguration {
    @Bean
    public RouterFunction<ServerResponse> personRouterFunction(PersonHandler personHandler,
                                                               PersonRepository personRepository){
        return route(POST("/people"), personHandler::add)
                .andRoute(GET("/people/{id}"), personHandler::getById)
                .andRoute(GET("/people"), serverRequest -> ServerResponse
                        .ok()
                        .body(Flux.fromIterable(personRepository.list()), Person.class))
                .andRoute(DELETE("/people/{id}"), personHandler::delete);

    }
}
