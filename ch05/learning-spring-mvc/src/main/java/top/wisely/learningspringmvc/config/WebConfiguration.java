package top.wisely.learningspringmvc.config;



import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.*;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.wisely.learningspringmvc.argument_return.BeanArgumentResolver;
import top.wisely.learningspringmvc.argument_return.RequestResponsePersonMethodProcessor;
import top.wisely.learningspringmvc.converter.PersonFormatAnnotationFormatterFactory;
import top.wisely.learningspringmvc.converter.StringToLengthConverter;
import top.wisely.learningspringmvc.http_message_converter.AnotherPersonHttpMessageConverter;
import top.wisely.learningspringmvc.converter.PersonFormatter;
import top.wisely.learningspringmvc.interceptor.CustomInterceptor;
import top.wisely.learningspringmvc.servlet.CustomFilter;
import top.wisely.learningspringmvc.servlet.CustomListener;
import top.wisely.learningspringmvc.servlet.CustomServlet;


import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

@Configuration
//@ServletComponentScan({"top.wisely.learningspringmvc.servlet"})
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(personFormatter());
        registry.addConverter(new StringToLengthConverter());
        registry.addFormatterForFieldAnnotation(new PersonFormatAnnotationFormatterFactory());
    }

    @Bean
    public Formatter personFormatter(){
        return new PersonFormatter();
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(customInterceptor());
//    }
//
//    @Bean
//    public CustomInterceptor customInterceptor(){
//        return new CustomInterceptor();
//    }

//    @Bean
//    HttpMessageConverter anotherPersonHttpMessageConverter(){
//        return new AnotherPersonHttpMessageConverter();
//    }

    @Bean
    HttpMessageConverters httpMessageConverters(){
        return new HttpMessageConverters(new AnotherPersonHttpMessageConverter());
    }


//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new AnotherPersonHttpMessageConverter());
//    }

    @Bean
    RequestResponsePersonMethodProcessor requestResponsePersonMethodProcessor(){
        return new RequestResponsePersonMethodProcessor(httpMessageConverters().getConverters());
    }

    @Bean
    HandlerMethodArgumentResolver beanArgumentResolver(){
        return new BeanArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(beanArgumentResolver());
        resolvers.add(requestResponsePersonMethodProcessor());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(requestResponsePersonMethodProcessor());
    }



//    @Bean
//    ConfigurableWebBindingInitializer ConfigurableWebBindingInitializer(FormattingConversionService mvcConversionService){
//        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
//        initializer.setPropertyEditorRegistrar(registry ->
//                registry.registerCustomEditor(Person.class, new PersonEditor()));

//        initializer.setValidator(new PersonValidator());

//        mvcConversionService.addFormatter(new PersonFormatter());
//        mvcConversionService.addConverter(new StringToLengthConverter());
//        mvcConversionService.addFormatterForFieldAnnotation(new PersonFormatAnnotationFormatterFactory());
//        initializer.setConversionService(mvcConversionService);
//        return initializer;
//    }

    @Bean
    Converter stringToLengthConverter(){
        return new StringToLengthConverter();
    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer
//                .setUseSuffixPatternMatch(true)
//                .setUseRegisteredSuffixPatternMatch(true)
//                .addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController.class));
//
//    }


//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.mediaType("ap",
//                new MediaType("application","another-person", Charset.defaultCharset()));
//    }

//    @Bean
//    public CustomServlet customServlet(){
//        return new CustomServlet("Custom Servlet");
//    }
//
//    @Bean
//    public CustomServlet customServlet2(){
//        return new CustomServlet("Custom Servlet2");
//    }
//
//    @Bean
//    public CustomFilter customFilter(){
//        return new CustomFilter();
//    }
//
//    @Bean
//    public CustomListener customListener(){
//        return new CustomListener();
//    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
//        registrationBean.setServlet(new CustomServlet("Custom Servlet by ServletRegistrationBean"));
//        registrationBean.setUrlMappings(Arrays.asList("/custom-servlet"));
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new CustomFilter());
//        registrationBean.setUrlPatterns(Arrays.asList("/*"));
//        return registrationBean;
//    }
//
//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
//        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
//        registrationBean.setListener(new CustomListener());
//        return registrationBean;
//    }

//    @Bean
//    String msg(){
//        return "Custom Servlet by Sevlet annotation";
//    }
//    @Bean
    ServletContextInitializer servletContextInitializer(){
      return servletContext -> {
         servletContext.addServlet("new servlet", new CustomServlet("Custom Servlet by ServletContextInitializer"))
                 .addMapping("/new-servlet");
         servletContext.addFilter("new filter", new CustomFilter())
                 .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
         servletContext.addListener(new CustomListener());

      };
    }

//    @Bean
    ConfigurableServletWebServerFactory customWebServerFactory(){
        TomcatServletWebServerFactory server = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        server.setPort(8443);
        server.addAdditionalTomcatConnectors(redirectConnector());
        return server;
    }

//    @Bean
    Connector redirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
            configurer.setTaskExecutor(callableTaskExecutor());
    }

    @Bean
    public ThreadPoolTaskExecutor callableTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("callable-task-");
        return taskExecutor;
    }

    @Bean
    public ThreadPoolTaskExecutor deferredTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("deferred-task-");
        return taskExecutor;
    }


}
