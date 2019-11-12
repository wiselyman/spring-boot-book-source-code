package top.wisely.springfundamentals.injected;



import top.wisely.springfundamentals.beans.pojo.AnotherService;

public class JavaConfigInjectService {
    private AnotherService anotherService;


    public JavaConfigInjectService(AnotherService anotherService) {
        this.anotherService = anotherService;
    }

    public void doMyThing(){
        anotherService.doAnotherThing();
    }
}
