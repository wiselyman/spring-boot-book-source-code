package top.wisely.resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityController {

    @GetMapping("/userCan1")
    @PreAuthorize("hasAuthority('userCan1')")
    public Jwt userCan1(@AuthenticationPrincipal Jwt jwt){
        return jwt;
    }

    @GetMapping("/userCan2")
    @PreAuthorize("hasAuthority('userCan2')")
    public Jwt userCan2(@AuthenticationPrincipal Jwt jwt){
        return jwt;
    }

    @GetMapping("/adminCan1")
    @PreAuthorize("hasAuthority('adminCan1')")
    public Jwt adminCan1(@AuthenticationPrincipal Jwt jwt){
        return jwt;
    }

    @GetMapping("/adminCan2")
    @PreAuthorize("hasAuthority('adminCan2')")
    public Jwt adminCan2(@AuthenticationPrincipal Jwt jwt){
        return jwt;
    }

}