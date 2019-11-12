package top.wisely.learningspringsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import top.wisely.learningspringsecurity.domain.model.SysUser;
import top.wisely.learningspringsecurity.repository.SysUserRepository;
import top.wisely.learningspringsecurity.security.CusotmUserDetailsService;
import top.wisely.learningspringsecurity.security.CustomAuthenticationProvider;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    SysUserRepository sysUserRepository;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new CustomAuthenticationProvider(sysUserRepository, passwordEncoder()));
//    }

//    @Bean
//    AuthenticationProvider authenticationProvider(SysUserRepository sysUserRepository){
//        return new CustomAuthenticationProvider(sysUserRepository, passwordEncoder());
//    }
    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(new CusotmUserDetailsService(sysUserRepository));
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        auth.authenticationProvider(authProvider);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new CusotmUserDetailsService(sysUserRepository));
//    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/everyCanAccess").permitAll()
                .antMatchers("/authenticatedCanAccess").authenticated()
                .antMatchers("/adminCanAccess").hasRole("ADMIN")
                .antMatchers("/userCanAccess").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/threeCanAccess").access("@webSecurity.checkUsernameLenEq3(authentication)")
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(){
        BasicAuthenticationEntryPoint authenticationEntryPoint = new BasicAuthenticationEntryPoint();
        authenticationEntryPoint.setRealmName("wisely");
        return authenticationEntryPoint;
    }

    @Bean
    UserDetailsService userDetailsService(SysUserRepository sysUserRepository){
        return new CusotmUserDetailsService(sysUserRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
