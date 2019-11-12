package top.wisely.rsocketclient.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.rsocketclient.domain.model.Person;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Component
public class ControllerClient {
    WebClient webClient;

    public ControllerClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public void getOne(){
        System.out.println("查询一条数据");
        Mono<Person> mono = webClient
                .get()
                .uri("http://localhost:8080/people/{id}", "5d03608320802b1b10458227")
                .retrieve()
                .bodyToMono(Person.class);
        mono.subscribe(System.out::println);
    }

    public void getAll(){
        System.out.println("查询所有");
        Flux<Person> flux = webClient
                .get()
                .uri("http://localhost:8080/people")//8
                .retrieve()
                .bodyToFlux(Person.class);
        flux.subscribe(System.out::println);
    }

    public void delete(){
        System.out.println("删除一条数据");
        Mono<Void> mono = webClient
                .delete()
                .uri("http://localhost:8080/people/{id}", "5d03608320802b1b10458227")
                .retrieve()
                .bodyToMono(Void.class);
        mono.subscribe();
    }


    public void save(){
        System.out.println("新增多个");
        List<Person> people = Arrays.asList(new Person("aaa", 36),
                                            new Person("bbb", 37),
                                            new Person("ccc", 38));
        Flux<Person> flux = webClient
                .post()
                .uri("http://localhost:8080/people")
                .body(Flux.fromIterable(people), Person.class)
                .retrieve()
                .bodyToFlux(Person.class);

        flux.subscribe(System.out::println);
    }

}
