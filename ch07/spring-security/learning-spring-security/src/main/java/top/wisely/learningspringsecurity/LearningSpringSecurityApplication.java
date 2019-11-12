package top.wisely.learningspringsecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import top.wisely.learningspringsecurity.domain.model.SysUser;
import top.wisely.learningspringsecurity.repository.SysUserRepository;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

@SpringBootApplication
public class LearningSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringSecurityApplication.class, args);
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

//	@Bean
//	CommandLineRunner createUser(SysUserRepository sysUserRepository, PasswordEncoder passwordEncoder){
//		return args -> {
//			SysUser user = new SysUser("wangyunfei", "wyf", passwordEncoder.encode("111111"));
//			sysUserRepository.save(user);
//		};
//	}

//	@Bean
	CommandLineRunner passwordOperation(PasswordEncoder passwordEncoder){
		return args -> {
			String passwordPlain = "123456";
			String passwordEncoded = passwordEncoder.encode(passwordPlain);
			boolean isMatched = passwordEncoder.matches(passwordPlain, passwordEncoded);
			System.out.println("明文密码为：" + passwordPlain);
			System.out.println("编码密码为：" + passwordEncoded);
			System.out.println("密码是否匹配：" + isMatched);
		};
	}

//	@Bean
	CommandLineRunner createUser(SysUserRepository sysUserRepository, PasswordEncoder passwordEncoder){
		return args -> {
			SysUser user = new SysUser("wangyunfei", "wyf", passwordEncoder.encode("111111"), "ROLE_USER");
			SysUser admin = new SysUser("administrator", "admin", passwordEncoder.encode("admin"), "ROLE_ADMIN");
			sysUserRepository.save(user);
			sysUserRepository.save(admin);
		};
	}




}
