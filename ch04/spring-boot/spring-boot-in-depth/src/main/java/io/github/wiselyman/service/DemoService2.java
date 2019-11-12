package io.github.wiselyman.service;

import org.springframework.stereotype.Component;

@Component
public class DemoService2 {
    public void doSomething(){
        System.out.println("io.github.wiselyman.service包被扫描到了");
    }
}
