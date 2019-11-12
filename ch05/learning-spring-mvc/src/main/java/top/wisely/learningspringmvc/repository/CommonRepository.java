package top.wisely.learningspringmvc.repository;

import top.wisely.learningspringmvc.domain.Person;

public interface CommonRepository {
    Person save(Person person);
    Person findOne(Long id);
    Person replace(Long id, Person person);
    Person patched(Long id, Person person);
    void delete(Long id);
    Person findByName(String name);
}
