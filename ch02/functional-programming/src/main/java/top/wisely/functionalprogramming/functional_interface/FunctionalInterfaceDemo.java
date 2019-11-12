package top.wisely.functionalprogramming.functional_interface;


import org.apache.logging.log4j.util.PropertySource;
import top.wisely.functionalprogramming.Person;

import java.util.Comparator;
import java.util.function.*;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        Predicate<String> emptyPredicate = (String s) -> s.isEmpty();
        //Predicate<String> emptyPredicate =  s -> s.isEmpty();
        System.out.println(emptyPredicate.test("wyf"));

        Predicate<String> nonEmptyPredicate = emptyPredicate.negate();
        System.out.println(nonEmptyPredicate.test("wyf"));

        Predicate<Integer> greaterThan0 = i -> i > 0;
        Predicate<Integer> lessThan100 = i -> i < 100;
        Predicate<Integer> between0And100 = greaterThan0.and(lessThan100);
        System.out.println(between0And100.test(33));

        Predicate<Integer> greateThan0OrLessThan100 = greaterThan0.or(lessThan100);
        System.out.println(greateThan0OrLessThan100.test(100));

        IntPredicate intGreaterThan0 = i -> i > 0;
        System.out.println(intGreaterThan0.test(20));

        BiPredicate<String, Integer> isLongThanGivenLength = (str, len) -> str.length() > len;
        System.out.println(isLongThanGivenLength.test("wyf",2));

        Function<String, Integer> lengthFunction = str -> str.length();
        System.out.println(lengthFunction.apply("wyf"));

        Function<Integer, Integer> plusFunction = x -> x + x ;
        Function<Integer, Integer> multipleFunction = x -> x * x;
        Function<Integer, Integer> andThenFunction  = plusFunction.andThen(multipleFunction);
        System.out.println(andThenFunction.apply(2));

        Function<Integer, Integer> composeFunction = plusFunction.compose(multipleFunction);
        System.out.println(composeFunction.apply(2));

        IntFunction<Integer> intPlusFunction = x -> x + x ;
        System.out.println(intPlusFunction.apply(2));

        ToIntFunction<String> toIntFunction = str -> str.length();
        System.out.println(toIntFunction.applyAsInt("www"));

        IntToLongFunction intToLongFunction = i -> i;
        System.out.println(intToLongFunction.applyAsLong(1));

        BiFunction<String, String, Integer> totalLengthBiFunction = (str1, str2) -> str1.length() + str2.length();
        System.out.println(totalLengthBiFunction.apply("wyf","www"));


        Consumer<String> helloConsumer = str -> System.out.println("Hello " + str + "!");
        helloConsumer.accept("wyf");

        Supplier<Long> systemTime = () -> System.currentTimeMillis();
        System.out.println(systemTime.get());

        Comparator<Person> byWeightComparatorUsingLambda =
                (person1, person2) -> person1.getWeight().compareTo(person2.getWeight());

        Comparator<Person> byWeightComparatorUsingStatic =Comparator.comparing(Person::getWeight);

    }
}
