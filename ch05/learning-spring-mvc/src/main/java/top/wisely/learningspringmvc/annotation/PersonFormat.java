package top.wisely.learningspringmvc.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersonFormat {
    boolean style() default true;
}
