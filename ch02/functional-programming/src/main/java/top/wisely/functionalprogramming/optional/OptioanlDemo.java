package top.wisely.functionalprogramming.optional;

import java.util.Optional;


public class OptioanlDemo {
    public static void main(String[] args) {
//        checkPresenceDemo();
//        conditionalDemo();
//        orElseDemo();
//        getDemo();
//        filterDemo();
        mapDemo();
    }



    private static void checkPresenceDemo() {
        String str1 = "wyf";
        String str2 = null;
        Optional<String> nullableOptional1 = Optional.ofNullable(str1);
        Optional<String> nullableOptional2 = Optional.ofNullable(str2);
        System.out.println(nullableOptional1.isPresent());
        System.out.println(nullableOptional2.isEmpty());
    }
    private static void conditionalDemo() {
        Optional<String> nullableOptional1 = Optional.ofNullable("wyf");
        Optional<String> nullableOptional2 = Optional.ofNullable(null);
        nullableOptional1.ifPresent(System.out::println);
        nullableOptional2.ifPresent(System.out::println);

    }

    private static void orElseDemo() {
        Optional<String> nullableOptional1 = Optional.ofNullable("wyf");
        Optional<String> nullableOptional2 = Optional.ofNullable(null);
        String name1 = nullableOptional1.orElse("www");
        String name2 = nullableOptional2.orElse("www");
        String name3 = nullableOptional1.orElseGet(() -> "wwwFromOrElseGet");
        String name4 = nullableOptional2.orElseGet(() -> "wwwFromOrElseGet");
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(name4);

    }

    private static void getDemo() {
        Optional<String> nameOptional = Optional.of("wyf");
        String name = nameOptional.get();
        System.out.println(name);
    }

    private static void filterDemo() {
        Optional<String> nameOptional = Optional.of("wyf");
        boolean isWyf = nameOptional.filter(name -> name.equals("wyf")).isPresent();
        System.out.println(isWyf);
    }
    private static void mapDemo() {
        Optional<String> nameOptional = Optional.of("wyf");
        String hello = nameOptional.map(name -> "Hello " + name).get();
        System.out.println(hello);
    }

}
