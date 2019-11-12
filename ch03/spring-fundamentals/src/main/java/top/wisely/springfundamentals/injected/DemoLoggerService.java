package top.wisely.springfundamentals.injected;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import top.wisely.springfundamentals.annotations.InjectLogger;

@Component
public class DemoLoggerService {
    @InjectLogger
    private Logger log;

    public void doSomething(){
        log.info("通过自定义InjectLoggerAnnotationBeanPostPorcessor让注解@InjectLogger注入Logger对象");
    }
}
