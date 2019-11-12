package io.github.wiselyman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BUppercaseConfig {
    @Bean
    public String b(){
        return "B";
    }
}
