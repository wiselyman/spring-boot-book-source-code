package top.wisely.springfundamentals.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import top.wisely.springfundamentals.annotations.InjectLogger;

import java.lang.annotation.Annotation;

@Component
public class InjectLoggerAnnotationBeanPostPorcessor implements BeanPostProcessor {
    private Class<? extends Annotation> changeAnnotationType;

    public InjectLoggerAnnotationBeanPostPorcessor() {
        this.changeAnnotationType = InjectLogger.class;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            if(field.isAnnotationPresent(changeAnnotationType)){
                Logger logger = LoggerFactory.getLogger(bean.getClass());
                field.set(bean, logger);
            }
        });

        return bean;
    }
}
