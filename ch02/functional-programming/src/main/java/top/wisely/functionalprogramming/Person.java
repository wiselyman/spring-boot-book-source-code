package top.wisely.functionalprogramming;

import java.util.Objects;

public class Person {


    private String name; //名字

    private Gender gender; //性别

    private Integer weight = 0; //体重

    //无参构造
    public Person() {
        super();
    }

    //一个参数构造
    public Person(String name) {
        this.name = name;
    }

    //两个参数构造
    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    //全参构造
    public Person(String name, Gender gender, Integer weight) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void walk(String roadName){
        System.out.println(name + "在" + roadName +"上行走");
    }

    public void sayName(){
        System.out.println("我的名字是：" + name);
    }

    public boolean isMale(){
        if(gender.equals(Gender.MALE))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "名字是：" + name + "性别是：" + gender + "体重是：" + weight;
    }


    @Override
    public boolean equals(Object obj) {
        Person person = (Person) obj;
        if(this.getGender().equals(person.getGender()) &&
                this.getName().equals(person.getName()) &&
                this.getWeight() == person.getWeight())
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, gender);
    }
}
