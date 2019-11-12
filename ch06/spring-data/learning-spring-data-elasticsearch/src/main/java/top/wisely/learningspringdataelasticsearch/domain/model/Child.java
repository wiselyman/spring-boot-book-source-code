package top.wisely.learningspringdataelasticsearch.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.wisely.learningspringdataelasticsearch.domain.type.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    private String name;
    private Gender gender;
}
