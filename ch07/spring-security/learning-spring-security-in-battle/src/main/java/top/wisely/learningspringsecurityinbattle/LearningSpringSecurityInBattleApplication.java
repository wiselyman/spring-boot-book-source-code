package top.wisely.learningspringsecurityinbattle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.wisely.learningspringsecurityinbattle.domain.model.SysAuthority;
import top.wisely.learningspringsecurityinbattle.domain.model.SysRole;
import top.wisely.learningspringsecurityinbattle.domain.model.SysUser;
import top.wisely.learningspringsecurityinbattle.repository.SysAuthorityRepository;
import top.wisely.learningspringsecurityinbattle.repository.SysRoleRepository;
import top.wisely.learningspringsecurityinbattle.repository.SysUserRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class LearningSpringSecurityInBattleApplication {

//	@Bean
//	AuditorAware<String> auditorProvider(){
//		return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
//	}

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringSecurityInBattleApplication.class, args);
	}

//	@Bean
	CommandLineRunner init(SysUserRepository sysUserRepository,
						   SysRoleRepository sysRoleRepository,
						   SysAuthorityRepository sysAuthorityRepository,
						   PasswordEncoder passwordEncoder){
		return args -> {
			SysAuthority authority1 = sysAuthorityRepository.save(new SysAuthority("userCan1","userCan1"));
			SysAuthority authority2 = sysAuthorityRepository.save(new SysAuthority("userCan2","userCan2"));
			SysAuthority authority3 = sysAuthorityRepository.save(new SysAuthority("adminCan1","adminCan1"));
			SysAuthority authority4 = sysAuthorityRepository.save(new SysAuthority("adminCan2","adminCan2"));

			SysRole role1 = sysRoleRepository.save(new SysRole("普通用户",
					Stream.of(authority1,authority2).collect(Collectors.toSet())));
			SysRole role2 = sysRoleRepository.save(new SysRole("管理员",
					Stream.of(authority1,authority2,authority3,authority4).collect(Collectors.toSet())));

			SysUser user1 = sysUserRepository.save(new SysUser("wyf",
					passwordEncoder.encode("111111"),
					"wangyunfei",
					true,
					Stream.of(role1).collect(Collectors.toSet())));

			SysUser user2 = sysUserRepository.save(new SysUser("admin",
					passwordEncoder.encode("admin"),
					"administrator",
					true,
					Stream.of(role2).collect(Collectors.toSet())));

		};
	}

}
