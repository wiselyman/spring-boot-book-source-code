package top.wisely.rsocketserver.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import top.wisely.rsocketserver.domain.model.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}
