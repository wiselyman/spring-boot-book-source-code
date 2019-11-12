package top.wisely.springfundamentals.injected;

import org.springframework.stereotype.Service;
import top.wisely.springfundamentals.beans.annotated.SomeService;

@Service
public class AnnotationOneInjectionService {
    private SomeService someService;

    public AnnotationOneInjectionService(SomeService someService) {
        this.someService = someService;
    }

    public void doMyThing(){
        someService.doSomething();
    }
}
