package io.github.wiselyman.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
public class AConfig {

    @Bean
    public String a(){
        return "A";
    }
}
