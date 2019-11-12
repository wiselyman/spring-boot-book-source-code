package top.wisely.feignservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("resource-server")
public interface ResourceClient {

    @GetMapping("/remote1")
    public String invokeRemote1();

    @GetMapping("/remote2")
    public String invokeRemote2();

}
