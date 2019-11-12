package top.wisely.learningspringbatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvPerson {
    private String name;
    private String gender;
    private Integer age;
}
