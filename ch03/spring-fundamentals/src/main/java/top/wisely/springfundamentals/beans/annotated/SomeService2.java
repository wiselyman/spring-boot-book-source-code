package top.wisely.springfundamentals.beans.annotated;


import org.springframework.stereotype.Service;

@Service
public class SomeService2 {
    public void doSomething(){
        System.out.println("我也做了一些工作");
    }
}
