package top.wisely.learningreactivesecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivesecurity.client.ControllerClient;
import top.wisely.learningreactivesecurity.domain.model.SysAuthority;
import top.wisely.learningreactivesecurity.domain.model.SysUser;
import top.wisely.learningreactivesecurity.repository.SysUserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LearningReactiveSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningReactiveSecurityApplication.class, args);
	}


	@Bean
	CommandLineRunner initUsersClr(PasswordEncoder passwordEncoder,
								   SysUserRepository sysUserRepository){
		return args -> {
			SysUser user = new SysUser("wyf",
					passwordEncoder.encode("111111"),
					Stream.of(new SysAuthority("personList")).collect(Collectors.toSet()));
			SysUser admin = new SysUser("admin",
					passwordEncoder.encode("admin"),
					Stream.of(new SysAuthority("personList"), new SysAuthority("personAdd"))
					      .collect(Collectors.toSet()));
			sysUserRepository.save(user).subscribe();
			sysUserRepository.save(admin).subscribe();
		};
	}

	@Bean
	CommandLineRunner webClientClr(ControllerClient controllerClient){
		return args -> {
			controllerClient.addPersonByUser();
			Thread.sleep(1000);
			controllerClient.addPersonByAdmin();
			Thread.sleep(1000);
			controllerClient.listPersonByUser();
			Thread.sleep(1000);
			controllerClient.listPersonByAdmin();
			Thread.sleep(1000);
			controllerClient.getUserInfo();
			Thread.sleep(1000);
			controllerClient.getAdminInfo();
		};
	}

//	@Bean
	CommandLineRunner webFilterClr(SecurityWebFilterChain chain){
		return args -> {
			chain.getWebFilters().subscribe(System.out::println);
		};
	}
}
