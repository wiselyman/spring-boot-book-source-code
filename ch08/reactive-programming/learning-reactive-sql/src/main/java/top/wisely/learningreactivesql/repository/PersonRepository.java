package top.wisely.learningreactivesql.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import reactor.core.publisher.Flux;
import top.wisely.learningreactivesql.domain.model.Person;

public interface PersonRepository extends R2dbcRepository<Person, Long> {
    @Query("select id, name, age  from person")
    Flux<Person> findAll();
}
