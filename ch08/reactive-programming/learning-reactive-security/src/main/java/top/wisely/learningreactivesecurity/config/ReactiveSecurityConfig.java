package top.wisely.learningreactivesecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import top.wisely.learningreactivesecurity.security.CustomReactiveUserDetailsService;

@Configuration
@EnableReactiveMethodSecurity
public class ReactiveSecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        return http.authorizeExchange()
                .anyExchange().authenticated()
             .and()
                .csrf().disable()
                .httpBasic()
             .and()
                .build();
    }

    @Bean
    CustomReactiveUserDetailsService reactiveUserDetailsService(){
        return new CustomReactiveUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
