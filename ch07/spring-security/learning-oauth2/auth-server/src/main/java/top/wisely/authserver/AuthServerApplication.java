package top.wisely.authserver;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.FilterChainProxy;

import javax.servlet.Filter;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;


@SpringBootApplication
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

//	@Bean
	CommandLineRunner getEncodedPass(PasswordEncoder passwordEncoder){
		return args -> {
			System.out.println(passwordEncoder.encode("postman"));
			System.out.println(passwordEncoder.encode("app"));
		};
	}

//	@Bean
	CommandLineRunner publicKey(KeyPair keyPair){
		return args -> {
			System.out.println(Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
		};
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
