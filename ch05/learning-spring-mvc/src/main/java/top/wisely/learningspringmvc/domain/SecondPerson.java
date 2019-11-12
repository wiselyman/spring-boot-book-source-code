package top.wisely.learningspringmvc.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Map;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@JsonIgnoreProperties({"name","age"})
//public class SecondPerson {
//    private Long id;
//    private String name;
//    private Integer age;
//    @JsonIgnore
//    private Float height;
//    @JsonIgnore
//    private Date birthday;
//}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SecondPerson {
    @JsonProperty("person-id")
    private Long id;
    @JsonProperty("person-name")
    private String name;
    @JsonProperty("person-age")
    private Integer age;
    private Float height;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}


//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class SecondPerson {
//    private Long id;
//    private String name;
//    private Integer age;
//    @JsonValue
//    public String toJson(){
//        return this.toString(); //用lombok提供的toString()作为Json的输出
//    }
//
//}

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class SecondPerson {
//    @JsonInclude //默认为JsonInclude.Include.ALWAYS一直包含
//    private Long id;
//    @JsonInclude(JsonInclude.Include.NON_EMPTY) //不空字符串的情况包含
//    private String name;
//    @JsonInclude(JsonInclude.Include.USE_DEFAULTS) //默认值的时候包含
//    private Integer age;
//}

//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class SecondPerson {
//    private Long id;
//    private String name;
//    private Integer age = 22;
//
//
//    @JsonGetter("personId")
//    public Long getId() {
//        return id;
//    }
//    @JsonGetter("personName")
//    public String getName() {
//        return name;
//    }
//    @JsonGetter("personAge")
//    public Integer getAge() {
//        return age;
//    }
//}


