package top.wisely.learningspringbootactuator.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        if(MyEndpoint.status.isEmpty()){
           return Health.down().build();
        }else {
            return Health.up().build();
        }
    }
}
