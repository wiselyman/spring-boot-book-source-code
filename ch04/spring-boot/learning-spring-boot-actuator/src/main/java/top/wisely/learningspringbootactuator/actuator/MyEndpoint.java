package top.wisely.learningspringbootactuator.actuator;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "my")
public class MyEndpoint {
    public static Map<String, Boolean> status = new HashMap<>();

    @ReadOperation
    public Map<String, Boolean> findAll() {
        return status;
    }
    @ReadOperation
    public Boolean findOne(@Selector String id) {
        return status.get(id);
    }
    @WriteOperation
    public String save(@Selector String id) {
        status.put(id, true);
        return "保存成功";
    }
    @DeleteOperation
    public String delete(@Selector String id) {
        status.remove(id);
        return "删除成功";
    }
}
