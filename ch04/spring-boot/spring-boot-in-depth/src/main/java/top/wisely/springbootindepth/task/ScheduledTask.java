package top.wisely.springbootindepth.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(fixedRate = 5000)
    public void fixedRateDemo(){
        log.info("每隔5秒钟执行一次");
    }

    @Scheduled(fixedDelay = 10000)
    public void fixedDelayDemo(){
        log.info("在上次执行完成10秒钟之后执行");
    }

    @Scheduled(cron = "0 * * * * SAT,SUN" )
    public void cronDemo(){
        log.info("周六周日每分钟执行一次");
    }
}
