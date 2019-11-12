package io.github.wiselyman.annotations;

import io.github.wiselyman.config.AConfig;
import io.github.wiselyman.registrar.CBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CBeanDefinitionRegistrar.class)
public @interface EnableC {
}
