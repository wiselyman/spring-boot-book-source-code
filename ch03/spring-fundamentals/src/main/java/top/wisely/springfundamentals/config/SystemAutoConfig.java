package top.wisely.springfundamentals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import top.wisely.springfundamentals.beans.pojo.CommandService;
import top.wisely.springfundamentals.beans.pojo.WindowsService;
import top.wisely.springfundamentals.conditions.OnWindowsCondition;

@Configuration
public class SystemAutoConfig {

    @Bean
    @Conditional(OnWindowsCondition.class)
    public WindowsService windowsService(){
        return new WindowsService();
    }

}
