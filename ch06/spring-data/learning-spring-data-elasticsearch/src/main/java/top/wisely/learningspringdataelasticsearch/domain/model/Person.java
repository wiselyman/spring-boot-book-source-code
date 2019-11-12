package top.wisely.learningspringdataelasticsearch.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "person")
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Address address;
    Collection<Child> children;

    public Person(String name, Integer age, Address address, Collection<Child> children) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.children = children;
    }
}
