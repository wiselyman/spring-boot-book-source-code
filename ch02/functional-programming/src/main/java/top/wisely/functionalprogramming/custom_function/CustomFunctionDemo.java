package top.wisely.functionalprogramming.custom_function;

public class CustomFunctionDemo {
    public static void main(String[] args) {
        TriFunction<String, String, String, Integer> lengthTriFuntion =
                (str1, str2, str3) -> str1.length() + str2.length() + str3.length();
        System.out.println(lengthTriFuntion.apply("wyf", "www", "foo"));
    }
}
