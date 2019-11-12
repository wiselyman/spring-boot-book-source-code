package top.wisely.springbootindepth.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AsyncTask {
    @Async("myTaskExecutor")
    public void loopPrint(Integer i){
        log.info("当前计数为：" + i);
    }

    @Async("customTaskExecutor")
    public void loopPrint2(Integer i){
        log.info("当前计数为：" + i);
    }
}
