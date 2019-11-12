package top.wisely.functionalprogramming.method_reference;


import top.wisely.functionalprogramming.Gender;
import top.wisely.functionalprogramming.Person;
import top.wisely.functionalprogramming.custom_function.TriFunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class MethodRefDemo {

    public static void main(String[] args) {

        constructorRef();
        staticMethodRef();
        instanceMethodRef();
        instanceMethodOfArbitraryObjectOfType();
    }

    public static void constructorRef(){
        Supplier<Person> emptyConstructor = Person::new;
        Supplier<Person> emptyConstructorLambda = () -> new Person();
        Person person1 = emptyConstructor.get();
        Person person1Lambda = emptyConstructorLambda.get();


        Function<String, Person> nameConstructor = Person::new;
        Function<String, Person> nameConstructorLambda = name -> new Person(name);
        Person person2 = nameConstructor.apply("wyf");
        Person person2Lambda = nameConstructorLambda.apply("wyf");

        BiFunction<String, Gender, Person> nameAndGenderConstructor = Person::new;
        BiFunction<String, Gender, Person> nameAndGenderConstructorLambda =
                (name, gender) -> new Person(name, gender);
        Person person3 = nameAndGenderConstructor.apply("www", Gender.FEMALE);
        Person person3Lambda = nameAndGenderConstructorLambda.apply("www", Gender.FEMALE);

        TriFunction<String, Gender, Integer, Person> allConstructor = Person::new;
        TriFunction<String, Gender, Integer, Person> allConstructorLambda =
                (name, gender, weight) -> new Person(name, gender, weight);
        Person person4 = allConstructor.apply("www", Gender.FEMALE, 110);
        Person person4Lambda = allConstructorLambda.apply("www", Gender.FEMALE, 110);
    }

    public static  void staticMethodRef(){
        IntFunction<String> intToStringFunction = Integer::toString;
        IntFunction<String> intToStringFunLambda = i -> Integer.toString(i);
        System.out.println(intToStringFunction.apply(123));
        System.out.println(intToStringFunLambda.apply(123));
    }

    public static void instanceMethodRef(){
        Person person = new Person("www", Gender.FEMALE, 80);
        Consumer<String> walkConsumer = person::walk;
        walkConsumer.accept("黄山");
    }

    public static void instanceMethodOfArbitraryObjectOfType(){
        List<Person> people = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90));
        people.forEach(Person::sayName);
        people.forEach(person -> person.sayName());

    }


}
