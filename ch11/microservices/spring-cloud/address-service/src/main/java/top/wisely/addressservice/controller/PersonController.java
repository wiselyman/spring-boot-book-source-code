package top.wisely.addressservice.controller;

import org.springframework.web.bind.annotation.*;
import top.wisely.addressservice.domain.model.Person;
import top.wisely.addressservice.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> findAll(){
        return personRepository.findAll();
    }
}

