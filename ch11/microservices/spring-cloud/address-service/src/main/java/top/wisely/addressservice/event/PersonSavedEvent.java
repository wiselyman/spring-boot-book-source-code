package top.wisely.addressservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonSavedEvent {
    private String id;
    private String name;
    private Integer age;
}
