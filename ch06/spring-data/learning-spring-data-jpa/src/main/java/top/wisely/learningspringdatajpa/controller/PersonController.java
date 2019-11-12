package top.wisely.learningspringdatajpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import top.wisely.learningspringdatajpa.domain.model.Person;
import top.wisely.learningspringdatajpa.repository.PersonRepository;


@RestController
@RequestMapping("/people")
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    public Person findOne(@PathVariable("id") Person person){
        return person;
    }
    //http://localhost:8080/people?page=0&size=2&sort=name,desc
    @GetMapping
    public Page<Person> findAllByPage(Pageable pageable){
       return personRepository.findAll(pageable);
    }

}
