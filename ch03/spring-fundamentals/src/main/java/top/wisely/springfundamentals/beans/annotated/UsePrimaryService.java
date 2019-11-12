package top.wisely.springfundamentals.beans.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.wisely.springfundamentals.beans.pojo.AnotherService;

@Component
public class UsePrimaryService {
    private AnotherService service;

    public UsePrimaryService(AnotherService service) {
        this.service = service;
    }

    public void doSomething(){
        System.out.println("foo".equals(service.getPerson()));
    }
}


