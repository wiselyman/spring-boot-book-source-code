package top.wisely.springfundamentals.custom_scan;


import top.wisely.springfundamentals.annotations.CustomBean;

@CustomBean
public class CustomBeanService {
    public void doSomething(){
        System.out.println("通过自定义的注解成功注册bean");
    }
}
