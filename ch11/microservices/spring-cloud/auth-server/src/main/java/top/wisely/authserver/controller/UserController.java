package top.wisely.authserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wisely.authserver.domain.model.SysUser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestController
public class UserController {
    @RequestMapping("/userInfo")
    public Map<String, String> userInfo(@AuthenticationPrincipal Jwt jwt){
        Map<String,String> map = new HashMap<>();
        map.put("username", jwt.getClaimAsString("user_name"));
        return map;
    }
}
