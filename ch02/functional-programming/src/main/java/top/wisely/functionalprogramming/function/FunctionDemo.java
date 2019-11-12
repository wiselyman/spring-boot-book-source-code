package top.wisely.functionalprogramming.function;

import top.wisely.functionalprogramming.Gender;
import top.wisely.functionalprogramming.Person;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {

        Function<String,Person> personDef = Person::new;//1
        Person person = personDef.apply("wyf");//2
//        Person person = new Person("wyf");
        Consumer<Gender> genderConsumer = person::setGender;//3
        genderConsumer.accept(Gender.MALE);//4
//        person.setGender(Gender.MALE);

        System.out.println(person);
    }
}
