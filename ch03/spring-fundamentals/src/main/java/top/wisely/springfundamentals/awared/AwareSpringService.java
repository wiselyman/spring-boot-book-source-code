package top.wisely.springfundamentals.awared;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import top.wisely.springfundamentals.event.MessageEvent;

import java.io.IOException;

@Component
public class AwareSpringService implements BeanNameAware,
                                           ResourceLoaderAware,
                                           BeanFactoryAware,
                                           EnvironmentAware,
                                           ApplicationEventPublisherAware,
                                           ApplicationContextAware{

    private String beanName;
    private ResourceLoader resourceLoader;
    private BeanFactory beanFactory;
    private Environment environment;
    private ApplicationEventPublisher publisher;
    private ApplicationContext context;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void doSomething() throws IOException{
        beanNameDemo();
        resourceLoaderDemo();
        beanFactoryDemo();
        environmentDemo();
        publisherDemo();
        contextDemo();
    }

    private void beanNameDemo(){
        System.out.println("注入的beanName为：" + beanName);
    }

    private void resourceLoaderDemo() throws IOException {
        Resource resource = resourceLoader.getResource("https://avatars3.githubusercontent.com/u/1981770");
        System.out.println("通过注入的ResourceLoader加载的文件长度为：" + resource.contentLength());

    }

    private void beanFactoryDemo(){
        AwareSpringService service =  beanFactory.getBean(AwareSpringService.class);
        System.out.println("通过注入的BeanFactory获得的当前Bean的名称为：" + service.beanName);
    }

    private void environmentDemo(){
        String osName = environment.getProperty("os.name");
        System.out.println("通过注入的Environment获得的操作系统名称为：" + osName);
    }

    private void publisherDemo(){
        System.out.println("通过注入的ApplicationEventPublisher发送了系统事件");
        publisher.publishEvent(new MessageEvent("来自AwareSpringService的消息"));
    }

    private void contextDemo(){
        System.out.println("通过注入的ApplicationContext获得容器的显示名称：" + context.getDisplayName());
    }


}
