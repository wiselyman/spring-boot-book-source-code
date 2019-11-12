package io.github.wiselyman.annotations;

import org.springframework.context.annotation.Import;
import io.github.wiselyman.config.AConfig;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AConfig.class)
public @interface EnableA {
}
