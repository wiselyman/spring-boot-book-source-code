package top.wisely.springfundamentals.beans.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnotherService {
    private String person;

    public AnotherService(String person) {
        this.person = person;
    }

    public void doAnotherThing(){
        System.out.println(person + "做了另外的事情");
    }

}
