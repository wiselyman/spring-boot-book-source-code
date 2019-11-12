package top.wisely.springbootindepth.author;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorConfiguration {

    private final AuthorProperties authorProperties;

    public AuthorConfiguration(AuthorProperties authorProperties) {
        this.authorProperties = authorProperties;
    }

    @Bean
    public String strBean(){
        String str = authorProperties.getName() + "/"
                + authorProperties.getAge() + "/"
                + authorProperties.getMotherTongue() + "/"
                + authorProperties.getSecondLanguage() + "/"
                + authorProperties.getGraduatedUniversity() + "/"
                + authorProperties.getGraduationYear() + "/"
                + authorProperties.getAddress().getProvince() + "/"
                + authorProperties.getAddress().getCity() + "/";
        System.out.println(str);
        authorProperties.getBooks().forEach(book -> {
            System.out.println(book.getName());
            System.out.println(book.getPrice());
        });
        authorProperties.getRemarks().forEach((key,value) -> {
            System.out.println(key + ":" +value);
        });
        return str;
    }
}
