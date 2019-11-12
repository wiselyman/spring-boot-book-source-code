package top.wisely.learningspringmvc.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import top.wisely.learningspringmvc.domain.Person;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonClient {
    private RestTemplate restTemplate;
    public PersonClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void save(){
        HttpEntity<Person> request = new HttpEntity<>(new Person(1l, "wyf", 35));
        ResponseEntity<Person> savedPersonEntity = restTemplate
                .postForEntity("http://localhost:8080/people", request, Person.class);
        System.out.println(savedPersonEntity.getBody());
    }

    public void getOne(){
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        Person person = restTemplate
                .getForObject("http://localhost:8080/people/{id}",Person.class, params);
        System.out.println(person);
    }

    public void findByName(){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/people/findByName")
                .queryParam("name", "wyf");
        Person person = restTemplate
                .getForObject(builder.toUriString(),Person.class);
        System.out.println(person);
    }

}
