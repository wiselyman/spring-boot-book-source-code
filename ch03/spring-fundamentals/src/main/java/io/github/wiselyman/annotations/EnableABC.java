package io.github.wiselyman.annotations;


import io.github.wiselyman.config.AConfig;
import io.github.wiselyman.registrar.CBeanDefinitionRegistrar;
import io.github.wiselyman.selector.BForABCSelector;
import io.github.wiselyman.selector.BSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AConfig.class, BForABCSelector.class, CBeanDefinitionRegistrar.class})
public @interface EnableABC {
    boolean isUppercase() default true;
}
