package top.wisely.learningspringwebflux.repository;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import top.wisely.learningspringwebflux.domain.model.Person;
import top.wisely.learningspringwebflux.utils.MyBeanUtils;

import java.util.HashSet;
import java.util.Set;

@Repository
public class PersonRepository implements CommonRepository{

    private static Set<Person> people = new HashSet<>();

    @Override
    public Person save(Person person) {
        people.add(person);
        return person;
    }



    @Override
    public Person findOne(Long id) {
        Person person = people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .get();
        return person;
    }

    @Override
    public Person replace(Long id, Person person) {
        Person oldPerson = people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().get();
        people.remove(oldPerson);
        people.add(person);
        return person;
    }

    @Override
    public Person patched(Long id, Person person) {
        Person patchedPerson = people.stream()
                .filter(p -> p.getId().equals(id))
                .map(p -> {
                    String[] ignoredNullPropertyNames = MyBeanUtils.ignoredNullPropertyNames(person);
                    BeanUtils.copyProperties(person, p, ignoredNullPropertyNames);
                    return p ;
                })
                .findFirst().get();
        return patchedPerson;
    }

    @Override
    public void delete(Long id) {
        people.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public Person findByName(String name) {
        Person person = people.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst().get();
        return person;
    }

    @Override
    public Set<Person> list() {
        return people;
    }
}
