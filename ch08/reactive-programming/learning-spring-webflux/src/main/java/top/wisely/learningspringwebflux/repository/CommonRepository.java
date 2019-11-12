package top.wisely.learningspringwebflux.repository;

import top.wisely.learningspringwebflux.domain.model.Person;

import java.util.List;
import java.util.Set;

public interface CommonRepository {
    Person save(Person person);
    Person findOne(Long id);
    Person replace(Long id, Person person);
    Person patched(Long id, Person person);
    void delete(Long id);
    Person findByName(String name);
    Set<Person> list();
}
