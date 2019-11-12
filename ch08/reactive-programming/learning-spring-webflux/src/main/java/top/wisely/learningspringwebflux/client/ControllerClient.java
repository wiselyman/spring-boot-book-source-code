package top.wisely.learningspringwebflux.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.learningspringwebflux.domain.model.Person;


@Component
public class ControllerClient {

    WebClient webClient;

    public ControllerClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public void add1(){
        System.out.println("添加第一条数据");
        Mono<Person> mono = webClient
                .post()
                .uri("http://localhost:8080/people")
                .body(Mono.just(new Person(1l, "wyf", 35)), Person.class)
                .retrieve()
                .bodyToMono(Person.class);
        mono.subscribe(System.out::println);
    }

    public void findOne(){
        System.out.println("查询一条数据");
        Mono<Person> mono = webClient
                .get()
                .uri("http://localhost:8080/people/{id}", 1l)
                .retrieve()
                .bodyToMono(Person.class);
        mono.subscribe(System.out::println);
    }

    public void add2(){
        System.out.println("添加第二条数据");
        Mono<Person> mono = webClient
                .post()
                .uri("http://localhost:8080/people")
                .body(Mono.just(new Person(2l, "foo", 34)), Person.class)
                .retrieve()
                .bodyToMono(Person.class);
        mono.subscribe(System.out::println);
    }


    public void list(){
        System.out.println("获取列表数据");
        Flux<Person> flux = webClient
                .get()
                .uri("http://localhost:8080/people")
                .retrieve()
                .bodyToFlux(Person.class);
        flux.subscribe(System.out::println);
    }

    public void delete(){
        System.out.println("删除一条数据");
        Mono<Void> mono = webClient
                .delete()
                .uri("http://localhost:8080/people/{id}", 1l)
                .retrieve()
                .bodyToMono(Void.class);
        mono.subscribe(System.out::println);
    }

}
