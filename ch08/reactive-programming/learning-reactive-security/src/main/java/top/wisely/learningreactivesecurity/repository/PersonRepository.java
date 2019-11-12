package top.wisely.learningreactivesecurity.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import top.wisely.learningreactivesecurity.domain.model.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}
