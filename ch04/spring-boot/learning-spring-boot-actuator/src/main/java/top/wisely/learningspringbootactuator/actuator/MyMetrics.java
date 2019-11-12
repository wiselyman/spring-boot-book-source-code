package top.wisely.learningspringbootactuator.actuator;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.stereotype.Component;

@Component
public class MyMetrics implements MeterBinder{

    @Override
    public void bindTo(MeterRegistry registry) {
        Gauge.builder("top.wisely.size", () -> MyEndpoint.status.size())
                .baseUnit("个")
            .description("获取自定义端点中状态数量")
            .register(registry);
    }
}
