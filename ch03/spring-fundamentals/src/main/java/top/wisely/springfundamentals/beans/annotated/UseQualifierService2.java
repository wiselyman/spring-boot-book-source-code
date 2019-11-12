package top.wisely.springfundamentals.beans.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import top.wisely.springfundamentals.beans.pojo.AnotherService;

@Component
public class UseQualifierService2 {
    private AnotherService service;

    public UseQualifierService2(@Qualifier("primaryAnotherService") AnotherService service) {
        this.service = service;
    }

    public void doSomething(){
        System.out.println("foo".equals(service.getPerson()));
    }

}
