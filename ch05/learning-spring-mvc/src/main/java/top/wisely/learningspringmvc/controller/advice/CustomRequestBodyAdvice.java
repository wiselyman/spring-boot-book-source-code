package top.wisely.learningspringmvc.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import top.wisely.learningspringmvc.annotation.ProcessTag;
import top.wisely.learningspringmvc.domain.Person;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Random;

@RestControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
       return methodParameter.getParameterAnnotation(ProcessTag.class) != null;

    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof Person) {
            Person person = (Person) body;
            String upperCaseName = person.getName().toUpperCase();
            return new Person(person.getId(), upperCaseName, person.getAge());
        }
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if(Person.class.isAssignableFrom((Class<?>) targetType)){
            return new Person(new Random().nextLong(),"Nobody",-1);
        }
        return body;
    }
}
