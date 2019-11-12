package top.wisely.springbootindepth.author;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "author")
public class AuthorProperties {
    private String name = "wyf";
    private Integer age = 35;
    private String motherTongue;
    private String secondLanguage;
    private String graduatedUniversity;
    private Integer graduationYear;
    private Address address = new Address();
    private List<Book> books = new ArrayList<>();
    private Map<String, String> remarks = new HashMap<>();

    @Getter
    @Setter
    public static class Address {
        private String province;
        private String city;
    }

    @Getter
    @Setter
    public static class Book{
        private String name;
        private Integer price;
    }
}
