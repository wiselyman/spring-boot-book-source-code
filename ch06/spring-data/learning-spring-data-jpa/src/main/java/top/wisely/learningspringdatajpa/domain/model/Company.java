package top.wisely.learningspringdatajpa.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Company {
    private String companyName;
    private String city;
}
