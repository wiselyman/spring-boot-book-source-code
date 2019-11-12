package top.wisely.functionalprogramming.optional;

import java.util.Optional;

public class CreateOptionalDemo {
    public static void main(String[] args) {

        Optional<String> emptyOptional = Optional.empty();

        String str = "wyf";
        Optional<String> nonNullOptional = Optional.of(str);

        String str1 = "wyf";
        String str2 = null;
        Optional<String> nullableOptional1 = Optional.ofNullable(str1);
        Optional<String> nullableOptional2 = Optional.ofNullable(str2);

        System.out.println(emptyOptional.equals(nullableOptional2));

    }
}
