package top.wisely.learningcaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.learningcaching.domain.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
