package top.wisely.feignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.wisely.feignclient.domain.model.Person;

import java.util.List;

@FeignClient("discovery-client")
public interface PersonClient {
    @PostMapping("/people")
    Person save(@RequestBody Person person);

    @GetMapping("/people/{id}")
    Person getOne(@PathVariable("id") Long id);

    @GetMapping("/people/findByAge")
    List<Person> findByAge(@RequestParam("age") Integer age);

    @DeleteMapping("/people/{id}")
    String delete(@PathVariable("id") Long id);

}
