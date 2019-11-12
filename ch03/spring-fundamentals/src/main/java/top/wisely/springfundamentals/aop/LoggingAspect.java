package top.wisely.springfundamentals.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(top.wisely.springfundamentals.aop.Logging)")
    public void annotationPointCut(){}


    @Before("annotationPointCut()")
    public void beforeAnnotationPointCut(JoinPoint joinPoint){
       String name = (String) joinPoint.getArgs()[0];
       MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
       String action = methodSignature.getMethod().getAnnotation(Logging.class).value();
       System.out.println("对" + name + "进行了"+ action);
    }

    @AfterReturning(pointcut = "annotationPointCut()", returning = "retName")
    public void afterReturningAnnotationPointCut(JoinPoint joinPoint, String retName){
        String name = (String) joinPoint.getArgs()[0];
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String action = methodSignature.getMethod().getAnnotation(Logging.class).value();
        System.out.println("对" + name + "进行了"+ action + "，返回的名字为：" + retName);

    }

}
