package top.wisely.learningspringmvc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
@Slf4j
public class TaskService {

    public String callableTask() throws InterruptedException{
        Thread.sleep(5000);
        log.info("+++++Callable数据返回+++++");
        return "result from Callable";
    }

    public String deferredTask() {
        log.info("+++++DeferredResult数据返回+++++");
        return "result from DeferredResult";
    }
}
