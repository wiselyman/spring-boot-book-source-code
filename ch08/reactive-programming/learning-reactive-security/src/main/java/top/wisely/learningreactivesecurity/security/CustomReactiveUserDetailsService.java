package top.wisely.learningreactivesecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivesecurity.repository.SysUserRepository;

public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return sysUserRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User Not Found")))
                .map(UserDetails.class::cast);
    }
}
