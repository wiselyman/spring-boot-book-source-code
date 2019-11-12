package top.wisely.learningspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import top.wisely.learningspringsecurity.domain.model.SysUser;
import top.wisely.learningspringsecurity.repository.SysUserRepository;

import java.util.*;

@RestController
public class IndexController {


    @GetMapping("/")
    public String hello(){
        return "Hello Spring Security";
    }

    @GetMapping("/user")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal SysUser sysUser,
                           @CurrentSecurityContext SecurityContext securityContext,
                           @CurrentSecurityContext(expression = "authentication") Authentication authentication,
                           @CurrentSecurityContext(expression = "authentication.principal") Object principal,
                           @CurrentSecurityContext(expression = "authentication.details") Object details){
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication auth = context.getAuthentication();
//        Object principal = auth.getPrincipal();
//        Object details = auth.getDetails();
        Map<String, Object> map = new HashMap<>();
        map.put("sysUser", sysUser);
        map.put("authentication", authentication);
        map.put("principal", principal);
        map.put("details", details);
        return map;
    }

    @GetMapping("/everyCanAccess")
    public String everyCanAccess(){
        return "任何用户可访问";
    }

    @GetMapping("/authenticatedCanAccess")
    public String authenticatedCanAccess(){
        return "任何登录用户可访问";
    }

    @GetMapping("/userCanAccess")
    public String userCanAccess(){
        return "角色为ROLE_USER或ROLE_ADMIN的用户都可访问";
    }

    @GetMapping("/adminCanAccess")
    public String adminCanAccess(){
        return "角色为ROLE_ADMIN用户可访问";
    }

    @GetMapping("/threeCanAccess")
    public String threeCanAccess(){
        return "只有用户名字符串长度为3的用户可以访问";
    }


    @GetMapping("/methodAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String methodAdmin(){
        return "只有角色为ROLE_ADMIN的用户可访问";
    }

    @GetMapping("/methodDiffName")
    @PreAuthorize("#user.username != authentication.name")
    public String methodDiffName(@RequestBody SysUser user){
        return "传输的用户名和当前用户名不相同的可访问";
    }

    @GetMapping("/methodNameThree")
    @PreAuthorize("@webSecurity.checkUsernameLenEq3(authentication)")
    public String methodNameThree(){
        return "只有用户名字符串长度为3的用户可以访问";
    }

    @GetMapping("/methodAnotherName3")
    @PostAuthorize("returnObject.length() == 5")
    public String anotherTree(@AuthenticationPrincipal SysUser sysUser){
        return "Hi" + sysUser.getUsername();
    }

    @GetMapping("/methodFilterIn")
    @PreAuthorize("hasRole('USER')")
    @PreFilter("filterObject%2 == 0")
    public List<Integer>  methodFilterIn (@RequestParam List<Integer> numbers){
        return numbers;
    }

    @GetMapping("/methodFilterOut")
    @PostFilter("hasRole('USER') and filterObject%2 == 0")
    public Integer[] methodFilterIn (){
        Integer[] numbers = {1,2,3,4,5,6,7,8,9};
        return numbers;
    }

    @Autowired
    SysUserRepository sysUserRepository;

    @GetMapping("/findRoleAdminMyself")
    public Optional<SysUser> findMyself(){
        return sysUserRepository.findRoleAdminMyself();
    }
}
