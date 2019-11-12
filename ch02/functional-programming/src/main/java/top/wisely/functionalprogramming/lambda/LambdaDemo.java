package top.wisely.functionalprogramming.lambda;

import top.wisely.functionalprogramming.Gender;
import top.wisely.functionalprogramming.Person;

import java.util.*;

public class LambdaDemo {
    public static void main(String[] args) {
        anonymousLambdaDemo();
        lambdaAsAugment();
    }

    public static void anonymousLambdaDemo(){


        Comparator<Person> byWeightComparator = new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getWeight().compareTo(person2.getWeight());
            }
        };

        Comparator<Person> byWeightComparatorUsingLambda =
                (Person person1, Person person2) -> person1.getWeight().compareTo(person2.getWeight());



        Person person1 = new Person("wyf", Gender.MALE, 100);
        Person person2 = new Person("www", Gender.FEMALE, 80);

        System.out.println(byWeightComparator.compare(person1, person2) == byWeightComparatorUsingLambda.compare(person1,person2));

    }

    public static void lambdaAsAugment(){
        List<Person> people = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                                        new Person("www", Gender.FEMALE, 80),
                                        new Person("foo", Gender.FEMALE, 90));

        people.sort((p1, p2) -> p1.getWeight().compareTo(p2.getWeight()));
       // people.sort(Comparator.comparing(Person::getWeight));

        people.forEach(person -> System.out.println(person.getName() + "的体重：" + person.getWeight()));

    }





}
