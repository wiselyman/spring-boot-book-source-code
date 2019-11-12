package io.github.wiselyman.registrar;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class CBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        BeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(String.class)
                                            .addConstructorArgValue("C")
                                            .setScope(BeanDefinition.SCOPE_SINGLETON)
                                            .getBeanDefinition();
       registry.registerBeanDefinition("c",bd);
    }
}
