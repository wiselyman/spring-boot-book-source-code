package io.github.wiselyman.annotations;

import io.github.wiselyman.selector.BSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BSelector.class)
public @interface EnableB {

    boolean isUppercase() default true;
}
