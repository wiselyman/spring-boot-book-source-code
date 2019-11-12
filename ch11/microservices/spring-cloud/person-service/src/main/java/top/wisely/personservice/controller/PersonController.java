package top.wisely.personservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wisely.personservice.domain.model.Person;
import top.wisely.personservice.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {
    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public Person save(@RequestBody Person person){
        return personRepository.save(person);
    }
}

