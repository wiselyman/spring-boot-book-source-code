package top.wisely.learningspringsecurityinbattle.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wisely.learningspringsecurityinbattle.domain.model.SysUser;

import java.util.HashMap;
import java.util.Map;
@RestController
public class SecurityController {

    @GetMapping("/userCan1")
    @PreAuthorize("hasAuthority('userCan1')")
    public Map<String, Object> userCan1(@AuthenticationPrincipal SysUser sysUser){
        return getReturnMap(sysUser,"userCan1");
    }

    @GetMapping("/userCan2")
    @PreAuthorize("hasAuthority('userCan2')")
    public Map<String, Object> userCan2(@AuthenticationPrincipal SysUser sysUser){
        return getReturnMap(sysUser,"userCan2");

    }

    @GetMapping("/adminCan1")
    @PreAuthorize("hasAuthority('adminCan1')")
    public Map<String, Object> adminCan1(@AuthenticationPrincipal SysUser sysUser){
        return getReturnMap(sysUser,"adminCan1");
    }

    @GetMapping("/adminCan2")
    @PreAuthorize("hasAuthority('adminCan2')")
    public Map<String, Object> adminCan2(@AuthenticationPrincipal SysUser sysUser){
        return getReturnMap(sysUser,"adminCan2");
    }

    private Map<String, Object> getReturnMap(@AuthenticationPrincipal SysUser sysUser, String currentAuthority) {
        Map<String, Object> map = new HashMap<>();
        map.put("user-authorities" , sysUser.getAuthorities());
        map.put("current-authority", "userCan1");
        return map;
    }

}
