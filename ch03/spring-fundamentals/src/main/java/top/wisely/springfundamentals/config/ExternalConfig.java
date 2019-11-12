package top.wisely.springfundamentals.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
        @PropertySource("classpath:author.properties"),
        @PropertySource("classpath:book.properties")
})
public class ExternalConfig {

    Environment env;

    public ExternalConfig(Environment env) {
        this.env = env;
    }

    @Value("${book.name}")
    private String bookName;

    public void showEnv(){
        System.out.println("作者名字是：" + env.getProperty("author.name"));
        System.out.println("书籍名称是：" + bookName);
    }
}
