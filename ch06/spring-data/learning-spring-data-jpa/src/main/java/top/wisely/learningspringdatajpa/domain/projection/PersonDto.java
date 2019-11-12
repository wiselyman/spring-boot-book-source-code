package top.wisely.learningspringdatajpa.domain.projection;

import lombok.Value;

//public class PersonDto {
//    private final String name;
//    private final Integer age;
//
//    public PersonDto(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//}
@Value
public class PersonDto {
    String name;
    Integer age;
}
