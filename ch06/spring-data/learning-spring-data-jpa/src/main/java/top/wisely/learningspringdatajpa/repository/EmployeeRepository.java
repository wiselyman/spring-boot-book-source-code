package top.wisely.learningspringdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.wisely.learningspringdatajpa.domain.model.Employee;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
}
