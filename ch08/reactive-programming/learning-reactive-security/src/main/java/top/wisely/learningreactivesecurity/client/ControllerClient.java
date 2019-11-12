package top.wisely.learningreactivesecurity.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivesecurity.domain.model.Person;

import java.nio.charset.Charset;

@Component
public class ControllerClient {

    WebClient webClient;

    public ControllerClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public void addPersonByUser(){
        System.out.println("普通用户添加Peron，不具备权限");
        Mono<Person> mono = webClient
                .post()
                .uri("http://localhost:8080/people")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("wyf:111111".getBytes(Charset.defaultCharset())))
                .body(Mono.just(new Person("aaa", 33)), Person.class)
                .retrieve()
                .bodyToMono(Person.class);
        mono.subscribe(System.out::println);
    }

    public void addPersonByAdmin(){
        System.out.println("管理员用户添加Peron，具备权限");
        Mono<Person> mono = webClient
            .post()
            .uri("http://localhost:8080/people")
            .body(Mono.just(new Person("bbb", 34)), Person.class)
            .header(HttpHeaders.AUTHORIZATION,
                    "Basic " + Base64Utils.encodeToString("admin:admin".getBytes(Charset.defaultCharset())))
            .retrieve()
            .bodyToMono(Person.class);
        mono.subscribe(System.out::println);

    }

    public void listPersonByUser(){
        System.out.println("普通用户查看Person列表，具备权限");
        Flux<Person> flux = webClient
                .get()
                .uri("http://localhost:8080/people")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("wyf:111111".getBytes(Charset.defaultCharset())))
                .retrieve()
                .bodyToFlux(Person.class);
        flux.subscribe(System.out::println);
    }

    public void listPersonByAdmin(){
        System.out.println("管理员用户查看Person列表，具备权限");
        Flux<Person> flux = webClient
                .get()
                .uri("http://localhost:8080/people")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes(Charset.defaultCharset())))
                .retrieve()
                .bodyToFlux(Person.class);
        flux.subscribe(System.out::println);
    }

    public void getUserInfo(){
        System.out.println("普通用户查看用户信息，登录即可");
        Mono<String> mono = webClient
                .get()
                .uri("http://localhost:8080/user")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("wyf:111111".getBytes(Charset.defaultCharset())))
                .retrieve()
                .bodyToMono(String.class);
        mono.subscribe(System.out::println);
    }

    public void getAdminInfo(){
        System.out.println("管理员用户查看用户信息，登录即可");
        Mono<String> mono = webClient
                .get()
                .uri("http://localhost:8080/user")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("admin:admin".getBytes(Charset.defaultCharset())))
                .retrieve()
                .bodyToMono(String.class);
        mono.subscribe(System.out::println);
    }


}
