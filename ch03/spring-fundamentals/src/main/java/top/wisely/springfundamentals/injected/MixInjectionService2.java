package top.wisely.springfundamentals.injected;

import top.wisely.springfundamentals.beans.annotated.SomeService;


public class MixInjectionService2 {
    private SomeService someService;

    public MixInjectionService2(SomeService someService) {
        this.someService = someService;
    }

    public void doMyThing(){
        someService.doSomething();
    }
}
