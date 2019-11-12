package top.wisely.learningspringmvc.controller.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.wisely.learningspringmvc.annotation.ProcessTag;
import top.wisely.learningspringmvc.domain.Person;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(ProcessTag.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Person){
            Map<String, Object> map = new HashMap<>();
            map.put("person", body);
            map.put("extra-response-body", "demo-body");
            return map;
        }
        return body;
    }
}
