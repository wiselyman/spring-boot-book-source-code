package top.wisely.springfundamentals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import top.wisely.springfundamentals.beans.pojo.CommandService;

@Configuration
@Profile("production")
public class LinuxProfileConfig {
    @Bean
    CommandService commandService(){
        return new CommandService("ls");
    }
}
