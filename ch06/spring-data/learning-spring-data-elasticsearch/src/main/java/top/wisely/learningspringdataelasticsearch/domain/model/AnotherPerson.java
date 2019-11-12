package top.wisely.learningspringdataelasticsearch.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "another-person")
public class AnotherPerson {
    @Id
    private String id;
    private String name;
    private Integer age;

    public AnotherPerson(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
