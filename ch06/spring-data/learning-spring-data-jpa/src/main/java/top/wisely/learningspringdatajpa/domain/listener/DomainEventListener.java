package top.wisely.learningspringdatajpa.domain.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import top.wisely.learningspringdatajpa.domain.domain_event.PersonSaved;
import top.wisely.learningspringdatajpa.domain.model.Company;
import top.wisely.learningspringdatajpa.domain.model.Employee;
import top.wisely.learningspringdatajpa.repository.EmployeeRepository;

import java.util.Optional;

@Component
public class DomainEventListener {

    private EmployeeRepository employeeRepository;

    public DomainEventListener(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Async
    @TransactionalEventListener
    public void handlePersonSavedEvent(PersonSaved personSaved){
        Company company = new Company("某某公司", "hefei");
        Optional<Employee> employeeOptional = employeeRepository.findById(personSaved.getId());
        employeeOptional.ifPresent(employee -> {
            employee.setName(personSaved.getName());
            employee.setAge(personSaved.getAge());
            employeeRepository.save(employee);
            return;
        });
        employeeRepository.save(new Employee(personSaved.getId(), personSaved.getName(), personSaved.getAge(), company));
    }


}
