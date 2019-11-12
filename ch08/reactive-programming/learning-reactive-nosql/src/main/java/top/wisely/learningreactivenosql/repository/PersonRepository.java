package top.wisely.learningreactivenosql.repository;

//import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import top.wisely.learningreactivenosql.domain.model.Person;

//public interface PersonRepository extends ReactiveElasticsearchRepository<Person, String> {
//}

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}