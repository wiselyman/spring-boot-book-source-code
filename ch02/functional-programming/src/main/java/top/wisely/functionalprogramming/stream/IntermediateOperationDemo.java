package top.wisely.functionalprogramming.stream;

import top.wisely.functionalprogramming.Gender;
import top.wisely.functionalprogramming.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateOperationDemo {
    public static void main(String[] args) {

        filterDemo();
        distinctDemo();
        distinctAndFilterDemo();
        mapDemo();
        flatMapDemo();
        primitiveMapDemo();
        othersDemo();

    }



    public static void filterDemo(){
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

        peopleStream.filter(person -> person.getGender().equals(Gender.MALE))
                .forEach(person -> System.out.println(person));

//        peopleStream.filter(Person::isMale)
//                .forEach(System.out::println);
    }

    public static void distinctDemo(){
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

        peopleStream.distinct()
                .forEach(System.out::println);
    }

    private static void distinctAndFilterDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();
        peopleStream.distinct()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .forEach(System.out::println);
    }

    private static void mapDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();
//       Stream<String> weightStream = peopleStream.map(person -> person.getName());
       //Stream<String> weightStream = peopleStream.map(Person::getName);
        Stream<Integer> lengthStream = peopleStream.map(Person::getName)
                                                .map(String::length);

    }

    private static void flatMapDemo() {
        List<Person> people1 = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80));
        List<Person> people2 = Arrays.asList(new Person("foo", Gender.MALE, 90),
                new Person("bar", Gender.FEMALE, 110));
        List<List<Person>> peopleList = Arrays.asList(people1, people2);


        peopleList.stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);

//         peopleList.stream()
//                .map(Collection::stream)
//                .forEach(personStream -> {
//                    personStream.forEach(System.out::println);
//                });


    }

    private static void primitiveMapDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

       IntStream intStream = peopleStream.mapToInt(Person::getWeight);


    }


    private static void othersDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();
//        peopleStream.skip(1).forEach(System.out::println);
//        peopleStream.limit(2).forEach(System.out::println);
        peopleStream.sorted(Comparator.comparing(Person::getWeight)).forEach(System.out::println);

    }




}
