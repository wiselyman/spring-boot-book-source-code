package top.wisely.springfundamentals.aop;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Logging("人员新增操作")
    public void add(String name){
        System.out.println("人员新增");
    }

    @Logging("人员删除操作")
    public void remove(String name){
        System.out.println("人员删除");
    }

    @Logging("人员查询操作")
    public String query(String name){
        System.out.println("人员查询");
        return name + "先生";
    }

    @Logging("人员修改操作")
    public String modify(String name){
        System.out.println("人员修改");
        return name.toUpperCase();
    }
}
