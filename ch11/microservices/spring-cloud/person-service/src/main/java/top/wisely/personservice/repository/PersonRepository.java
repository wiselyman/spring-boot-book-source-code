package top.wisely.personservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.wisely.personservice.domain.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
}
