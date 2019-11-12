package top.wisely.learningspringdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.learningspringdatajpa.domain.model.AnotherPerson;

public interface AnotherPersonRepository extends JpaRepository<AnotherPerson, Long> {
}
