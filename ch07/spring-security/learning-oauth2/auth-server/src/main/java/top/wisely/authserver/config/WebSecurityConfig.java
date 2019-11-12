package top.wisely.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import top.wisely.authserver.repository.SysUserRepository;
import top.wisely.authserver.security.CusotmUserDetailsService;

import java.security.KeyPair;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SysUserRepository sysUserRepository;
    @Bean
    protected UserDetailsService userDetailsService() {
        return new CusotmUserDetailsService(sysUserRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/.well-known/jwks.json").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwt -> {
            Collection<SimpleGrantedAuthority> authorities = ((Collection<String>) jwt.getClaims().get("authorities")).stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            return new JwtAuthenticationToken(jwt, authorities);
        });

    }

    @Bean
    public KeyPair keyPair(AuthorizationServerProperties properties, ApplicationContext context){
        Resource keyStore = context
                .getResource(properties.getJwt().getKeyStore());
        char[] keyStorePassword = properties.getJwt().getKeyStorePassword()
                .toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore,
                keyStorePassword);
        String keyAlias = properties.getJwt().getKeyAlias();
        char[] keyPassword = Optional
                .ofNullable(properties.getJwt().getKeyPassword())
                .map(String::toCharArray).orElse(keyStorePassword);
        return keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword);
    }

}
