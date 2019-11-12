package top.wisely.learningreactivesecurity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import top.wisely.learningreactivesecurity.domain.model.SysUser;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("/user")
    public Mono<String> user(@AuthenticationPrincipal Mono<SysUser> userMono){
        return userMono.map(user -> user.getUsername());
    }
}
