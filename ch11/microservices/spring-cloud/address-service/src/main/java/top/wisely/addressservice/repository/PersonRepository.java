package top.wisely.addressservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.addressservice.domain.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
}
