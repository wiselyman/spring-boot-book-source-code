package top.wisely.feignservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wisely.feignservice.client.ResourceClient;

@RestController
public class FeignController {
    ResourceClient client;

    public FeignController(ResourceClient client) {
        this.client = client;
    }

    @GetMapping("/remote1")
    @PreAuthorize("isAuthenticated()")
    public String remote1(){
       return client.invokeRemote1();
    }

    @GetMapping("/remote2")
    @PreAuthorize("isAuthenticated()")
    public String remote2(){
        return client.invokeRemote2();
    }

}
