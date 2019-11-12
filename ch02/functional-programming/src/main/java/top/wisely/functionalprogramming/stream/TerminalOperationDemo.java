package top.wisely.functionalprogramming.stream;

import top.wisely.functionalprogramming.Gender;
import top.wisely.functionalprogramming.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;


public class TerminalOperationDemo {
    public static void main(String[] args) {
//        aggregateDemo();
//        forEachDemo();
//        matchDemo();
//        findDemo();
//        toArrayDemo();
//        reduceDemo();
//         collectionCollectDemo();
//        stringCollectDemo();
//        aggregateCollectDemo();
//        collectingAndThenDemo();
//        toMapDemo();
        groupingByToMap();
    }




    private static void aggregateDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();
//
//        System.out.println(peopleStream.count());
//        System.out.println(peopleStream.max(Comparator.comparing(Person::getWeight)));
        System.out.println(peopleStream.min(Comparator.comparing(Person::getWeight)));
    }

    private static void forEachDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

        peopleStream.forEach(System.out::println);
    }

    private static void matchDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

//        boolean isAllMale = peopleStream.allMatch(Person::isMale);
//        boolean isAnyMale = peopleStream.anyMatch(Person::isMale);
        boolean isNonMale = peopleStream.noneMatch(Person::isMale);


    }


    private static void findDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

        Person person = peopleStream.findAny().get();
        Person firstPerson = peopleStream.findFirst().get();
    }

    private static void toArrayDemo() {
        Stream<Person> peopleStream = Arrays.asList(new Person("wyf", Gender.MALE, 100),
                new Person("www", Gender.FEMALE, 80),
                new Person("foo", Gender.FEMALE, 90),
                new Person("foo", Gender.FEMALE, 90)).stream();

        Object[] people =  peopleStream.toArray();

    }

    private static void reduceDemo() {

        int reduced1 = Stream.of(1, 2, 3).reduce((a, b) -> a + b).get();
        int reduced2 = Stream.of(1, 2, 3).reduce(10, (a, b) -> a + b);
        int reduced3 = Stream.of(1, 2, 3).reduce((a, b) -> a * b).get();
        int reduced4 =Stream.of(1, 2, 3).reduce(10, (a, b) -> a * b);
        int reduced5 = Stream.of(1, 2, 3).reduce(Integer::max).get();
        int reduced6 = Stream.of(1, 2, 3).reduce(10,Integer::max);
        int reduced7 = Stream.of(1, 2, 3).reduce(Integer::min).get();
        int reduced8 = Stream.of(1, 2, 3).reduce(10,Integer::min);

        System.out.println(reduced1);
        System.out.println(reduced2);
        System.out.println(reduced3);
        System.out.println(reduced4);
        System.out.println(reduced5);
        System.out.println(reduced6);
        System.out.println(reduced7);
        System.out.println(reduced8);
    }


    private static void collectionCollectDemo() {
        List<String> streamToList = Stream.of("wyf", "www", "foo", "bar")
                                        .collect(Collectors.toList());
        Set<String> streamToSet = Stream.of("wyf", "wyf", "foo", "bar")
                                        .collect(Collectors.toSet());
        List<String> streamToCollection = Stream.of("wyf", "www", "foo", "bar")
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private static void stringCollectDemo() {
        String streamToString = Stream.of("wyf", "www", "foo", "bar")
                .collect(Collectors.joining(",", "[", "]"));
        String streamToStringOnlyDelimiter = Stream.of("wyf", "www", "foo", "bar")
                .collect(Collectors.joining(","));
        System.out.println(streamToString);
        System.out.println(streamToStringOnlyDelimiter);

    }

    private static void aggregateCollectDemo() {
        long count = Stream.of("wyf", "www", "foo", "bar").collect(Collectors.counting());
        String maxLengthString = Stream.of("wyf", "w", "fooo", "barbar").collect(Collectors.maxBy(Comparator.comparing(String::length))).get();
        String minLengthString = Stream.of("wyf", "ww", "fooo", "barbar").collect(Collectors.minBy(Comparator.comparing(String::length))).get();

        double average =  Stream.of("wyf", "ww", "fooo", "barbar").collect(Collectors.averagingInt(String::length));

        int sum = Stream.of("wyf", "ww", "fooo", "barbar").collect(Collectors.summingInt(String::length));
        IntSummaryStatistics summary = Stream.of("wyf", "ww", "fooo", "barbar").collect(Collectors.summarizingInt(String::length));

        System.out.println(count);
        System.out.println(maxLengthString);
        System.out.println(minLengthString);
        System.out.println(average);

        System.out.println(summary.getMax());
        System.out.println(summary.getMin());
        System.out.println(summary.getAverage());
        System.out.println(summary.getSum());
        System.out.println(summary.getCount());


    }

    private static void collectingAndThenDemo() {
        int size = Stream.of("wyf", "www", "foo", "bar")
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
//        int size = Stream.of("wyf", "www", "foo", "bar")
//                .collect(collectingAndThen(toList(),List::size));
        System.out.println(size);
    }

    private static void toMapDemo() {
        Map<String, Integer> map = Stream.of("wyf", "www", "foo", "bar")
                .collect(Collectors.toMap(Function.identity(), String::length));


    }

    private static void groupingByToMap() {
     Map<Integer,List<String>> groupingByMap  =  Stream.of("wyf", "www", "foobar", "barfoo")
             .collect(Collectors.groupingBy(String::length));
     Map<Boolean,List<String>> partitioningByMap = Stream.of("wyf", "www", "foobar", "barfoo")
                .collect(Collectors.partitioningBy(str -> str.length()== 6));
     partitioningByMap.forEach((bool,list) ->{
         System.out.println(bool);
         System.out.println(list);
     });
    }




}
