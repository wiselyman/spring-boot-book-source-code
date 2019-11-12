package top.wisely.springfundamentals.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import top.wisely.springfundamentals.aop.PersonService;
import top.wisely.springfundamentals.awared.AwareSpringService;
import top.wisely.springfundamentals.beans.annotated.*;
import top.wisely.springfundamentals.custom_scan.CustomBeanService;
import top.wisely.springfundamentals.injected.DemoLoggerService;
import top.wisely.springfundamentals.beans.pojo.*;
import top.wisely.springfundamentals.injected.*;

/**
 * 为了演示清晰，每次只验证一个CommandLineRunner
 */

@Configuration
//@EnableA
//@EnableB
//@EnableB(isUppercase = false)
//@EnableC
//@EnableABC
@EnableAsync
public class JavaConfig {

    @Bean
    public AnotherService anotherService(){
        return new AnotherService("wyf");
    }


    @Bean
    public JavaConfigInjectService javaConfigInjectService(AnotherService anotherService){
        return new JavaConfigInjectService(anotherService);
    }
//    @Bean
//    public JavaConfigInjectService javaConfigInjectService(){
//        return new JavaConfigInjectService(anotherService());
//    }

    @Bean
    @Primary
    public AnotherService primaryAnotherService(){
        return new AnotherService("foo");
    }


    @Bean
    public MixInjectionService2 mixInjectionService2(SomeService someService){
        return new MixInjectionService2(someService);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public ScopeService3 scopeService3(){
        return new ScopeService3();
    }

    @Bean
    CommandLineRunner configClr(AnnotationInjectionService annotationInjectionService,
                                AnnotationOneInjectionService annotationOneInjectionService,
                                AnnotationPropertyInjectionService annotationPropertyInjectionService,
                                AnnotationSetterInjectionService annotationSetterInjectionService,
                                JavaConfigInjectService javaConfigInjectService,
                                MixInjectionService mixInjectionService,
                                MixInjectionService2 mixInjectionService2,
                                UsePrimaryService usePrimaryService,
                                UseQualifierService useQualifierService,
                                UseQualifierService2 useQualifierService2) {
        return args -> {
            System.out.println(args);
            annotationInjectionService.doMyThing();
            annotationOneInjectionService.doMyThing();
            annotationPropertyInjectionService.doMyThing();
            annotationSetterInjectionService.doMyThing();
            javaConfigInjectService.doMyThing();
            mixInjectionService.doMyThing();
            mixInjectionService2.doMyThing();
            usePrimaryService.doSomething();
            useQualifierService.doSomething();
            useQualifierService2.doSomething();
        };
    }

    @Bean
    ApplicationRunner configAr(){
     return  args -> System.out.println(args);
    }

//    @Bean
    CommandLineRunner scopeClr(ScopeInjectService scopeInjectService){
        return args -> {
          scopeInjectService.validateScope();
        };
    }

//    @Bean(initMethod = "exeAfterConstruct", destroyMethod = "exeBeforeDestroy")
//    @DependsOn("lifeService")
//    @Lazy
    public LifeService2 lifeService2(){
        return new LifeService2();
    }


//    @Bean
    CommandLineRunner profileClr(CommandService commandService){
       return args -> commandService.list();
    }

//    @Bean
    CommandLineRunner ExternalConfigClr(ExternalConfig externalConfig){
        return args -> externalConfig.showEnv();
    }


//    @Bean
    CommandLineRunner windowsConditionalClr(WindowsService windowsService){
        return args -> windowsService.show();
    }

//    @Bean
    CommandLineRunner enableAClr(String a){
        return args -> System.out.println(a);
    }

//    @Bean
    CommandLineRunner enbaleBClr(String b){
        return args -> System.out.println(b);
    }

//    @Bean
    CommandLineRunner enableCClr(String c){
        return args -> System.out.println(c);
    }

//    @Bean
    CommandLineRunner enableABCClr(String a, String b, String c){
        return args -> {
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        };
    }

//    @Bean
    CommandLineRunner awareClr(AwareSpringService awareSpringService){
        return args -> awareSpringService.doSomething();
    }

//    @Bean
    CommandLineRunner publishClr(EventPublishService eventPublishService){
        return args -> eventPublishService.publish();
    }

//    @Bean
    CommandLineRunner valueClr(ValueService valueService){
        return args -> valueService.doSomething();
    }

//    @Bean
    CommandLineRunner aopCle(PersonService personService){
        return args -> {
            personService.add("wyf");
            personService.remove("wyf");
            personService.query("wyf");
            personService.modify("wyf");
        };
    }

//    @Bean
    CommandLineRunner InjectLoggerAnnotationBeanPostProcessorClr(DemoLoggerService demoLoggerService){
        return args -> {
            demoLoggerService.doSomething();
        };
    }

//    @Bean
    CommandLineRunner customBeanDefinitionRegistryPostProcessorClr(CustomBeanService customBeanService){
        return args -> {
            customBeanService.doSomething();
        };
    }

}
