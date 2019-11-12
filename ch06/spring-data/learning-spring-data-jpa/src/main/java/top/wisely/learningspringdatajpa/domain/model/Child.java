package top.wisely.learningspringdatajpa.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.wisely.learningspringdatajpa.domain.type.Gender;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Child {

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
