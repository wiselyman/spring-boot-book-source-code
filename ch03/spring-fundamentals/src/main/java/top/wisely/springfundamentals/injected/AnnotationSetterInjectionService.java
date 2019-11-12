package top.wisely.springfundamentals.injected;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wisely.springfundamentals.beans.annotated.SomeService2;
import top.wisely.springfundamentals.beans.pojo.AnotherService;
import top.wisely.springfundamentals.beans.annotated.SomeService;

@Service
public class AnnotationSetterInjectionService {

    private SomeService someService;

    private SomeService2 someService2;

    @Autowired
    public void setSomeService(SomeService someService) {
        this.someService = someService;
    }

    @Autowired
    public void setSomeService2(SomeService2 someService2) {
        this.someService2 = someService2;
    }

    public void doMyThing(){
        someService.doSomething();
        someService2.doSomething();
    }
}
