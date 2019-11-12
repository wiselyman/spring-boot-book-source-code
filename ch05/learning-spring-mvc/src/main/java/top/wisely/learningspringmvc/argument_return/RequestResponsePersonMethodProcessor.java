package top.wisely.learningspringmvc.argument_return;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;
import top.wisely.learningspringmvc.annotation.ResponsePerson;
import top.wisely.learningspringmvc.domain.AnotherPerson;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.List;

public class RequestResponsePersonMethodProcessor extends AbstractMessageConverterMethodProcessor {

    public RequestResponsePersonMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return  parameter.hasMethodAnnotation(ResponsePerson.class) &&
                parameter.getParameterType().equals(AnotherPerson.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object object = readWithMessageConverters(webRequest, parameter, parameter.getNestedGenericParameterType());
        return object;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponsePerson.class) ||
                returnType.hasMethodAnnotation(ResponsePerson.class)) &&
                returnType.getParameterType().equals(AnotherPerson.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        AnotherPerson person = (AnotherPerson) returnValue;
        person.setName(person.getName().toUpperCase());
        writeWithMessageConverters(person, returnType, webRequest);
    }
}
