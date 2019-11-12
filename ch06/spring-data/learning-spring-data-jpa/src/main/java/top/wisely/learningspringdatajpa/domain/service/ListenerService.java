package top.wisely.learningspringdatajpa.domain.service;

import org.springframework.stereotype.Service;

@Service
public class ListenerService {
    public void process(String msg){
        System.out.println("由Spring处理：" + msg);
    }
}
