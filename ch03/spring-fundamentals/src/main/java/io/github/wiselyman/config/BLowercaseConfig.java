package io.github.wiselyman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BLowercaseConfig {
    @Bean
    public String b(){
        return "b";
    }
}
