package top.wisely.learningspringmvc.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"name", "age"})
@ToString
public class Person {
    public interface WithoutIdView {};
    public interface WithIdView extends WithoutIdView {};

    @NotNull(message = "id不能为空")
    private Long id;
    @Size(min = 3, max = 5, message = "name在3到5个字符之间")
    private String name;
    @Min(value = 18, message = "age不能低于18岁")
    private Integer age;

    @JsonView(WithIdView.class)
    public Long getId() {
        return id;
    }

    @JsonView(WithoutIdView.class)
    public String getName() {
        return name;
    }
    @JsonView(WithoutIdView.class)
    public Integer getAge() {
        return age;
    }
}
