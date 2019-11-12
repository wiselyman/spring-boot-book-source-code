package top.wisely.springfundamentals.injected;

import org.springframework.stereotype.Service;
import top.wisely.springfundamentals.beans.pojo.AnotherService;

@Service
public class MixInjectionService {
    private AnotherService anotherService;

    public MixInjectionService(AnotherService anotherService) {
        this.anotherService = anotherService;
    }

    public void doMyThing(){
        anotherService.doAnotherThing();
    }
}