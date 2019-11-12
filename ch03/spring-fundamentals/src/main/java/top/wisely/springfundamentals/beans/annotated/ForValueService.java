package top.wisely.springfundamentals.beans.annotated;

import org.springframework.stereotype.Service;

@Service
public class ForValueService {
    public String generate(String name){
        return "Hello " + name;
    }
}
