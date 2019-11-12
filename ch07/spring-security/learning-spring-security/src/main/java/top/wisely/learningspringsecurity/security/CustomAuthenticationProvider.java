package top.wisely.learningspringsecurity.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.wisely.learningspringsecurity.domain.model.SysUser;
import top.wisely.learningspringsecurity.repository.SysUserRepository;

import java.util.Optional;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    SysUserRepository sysUserRepository;

    PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(SysUserRepository sysUserRepository, PasswordEncoder passwordEncoder) {
        this.sysUserRepository = sysUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameFromRequest = authentication.getName();
        String passwordFromRequest = authentication.getCredentials().toString();
        Optional<SysUser> sysUserOptional = sysUserRepository.findByUsername(usernameFromRequest);
        SysUser sysUser = sysUserOptional
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if(passwordEncoder.matches(passwordFromRequest, sysUser.getPassword()) &&
            sysUser.isAccountNonExpired() &&
            sysUser.isAccountNonLocked() &&
            sysUser.isCredentialsNonExpired() &&
            sysUser.isEnabled())
            return new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword(), sysUser.getAuthorities());
        else
            throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}
