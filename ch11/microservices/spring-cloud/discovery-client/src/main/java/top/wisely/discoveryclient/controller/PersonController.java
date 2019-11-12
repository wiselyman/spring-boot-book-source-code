package top.wisely.discoveryclient.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.wisely.discoveryclient.domain.model.Person;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Value("${server.port}")
    Integer port;

    @PostMapping
    public Person save(@RequestBody Person person){
        person.setServerPort(port);
        return person;
    }

    @GetMapping("/{id}")
    public Person getOne(@PathVariable Long id){
        return new Person(id, "wyf", 35 , port);
    }

    @GetMapping("/findByAge")
    public List<Person> findByAge(@RequestParam Integer age){
        return Arrays.asList(new Person(1l, "wyf", 35 , port),
                             new Person(2l, "foo", 35 , port));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return "从端口为：" + port + "的服务删除";
    }

}
