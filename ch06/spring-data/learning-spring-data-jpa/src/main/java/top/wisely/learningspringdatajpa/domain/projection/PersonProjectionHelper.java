package top.wisely.learningspringdatajpa.domain.projection;

import org.springframework.stereotype.Component;
import top.wisely.learningspringdatajpa.domain.model.Person;
@Component
public class PersonProjectionHelper {

    public String getInfo(Person person){
        return person.toString();
    }
}
