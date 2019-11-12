package top.wisely.learningspringdatajpa.domain.domain_event;

import lombok.Value;

@Value
public class PersonSaved {
    private Long id;
    private String name;
    private Integer age;
}
