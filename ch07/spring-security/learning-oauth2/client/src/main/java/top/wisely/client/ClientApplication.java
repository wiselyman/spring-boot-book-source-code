package top.wisely.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;

import javax.servlet.Filter;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	CommandLineRunner filters(Filter springSecurityFilterChain){
		return args -> {
			FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
			filterChainProxy.getFilterChains()
					.stream()
					.flatMap(chain -> chain.getFilters().stream())
					.forEach(filter -> System.out.println(filter.getClass()));
		};
	}
}
