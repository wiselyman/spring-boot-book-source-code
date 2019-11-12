package top.wisely.springfundamentals.beans.annotated;


import org.springframework.stereotype.Component;

@Component
public class SomeService {
    public void doSomething(){
        System.out.println("我做了一些工作");
    }
}
