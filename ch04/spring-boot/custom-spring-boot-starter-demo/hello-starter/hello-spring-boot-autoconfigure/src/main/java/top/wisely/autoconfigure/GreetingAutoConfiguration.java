package top.wisely.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.wisely.GreetingService;

@Configuration
@ConditionalOnClass({GreetingService.class})
@EnableConfigurationProperties({GreetingProperties.class})
public class GreetingAutoConfiguration {

    private final GreetingProperties greetingProperties;

    public GreetingAutoConfiguration(GreetingProperties greetingProperties) {
        this.greetingProperties = greetingProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public GreetingService greetingService(){
        return new GreetingService(greetingProperties.getUser(),greetingProperties.getGreetings());
    }
}
