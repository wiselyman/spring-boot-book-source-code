package top.wisely.functionalprogramming.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import java.util.Optional;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CreateStreamDemo {
    public static void main(String[] args) throws IOException {

        Stream<String> singleStringStream = Stream.of("wyf");
        Stream<String> stringStream = Stream.of("www", "wyf", "foo", "bar");

        Stream<String> emptyStringStream = Stream.empty();

        String str1 = "wyf";
        String str2 = null;

        Stream<String> str1Stream = Stream.ofNullable(str1); //等同于Stream.of
        Stream<String> str2Stream = Stream.ofNullable(str2); //等同于Stream.empty


        Integer[] numArray = {1, 2, 3, 4, 5};
        Stream<Integer> numStream = Arrays.stream(numArray);


        String filePathStr = "/Users/wangyunfei/Documents/MyBook/book/Souce_Code/functional-programming/src/main/java/top/wisely/functionalprogramming/stream/test.txt";
        Path path = Paths.get(filePathStr);
        Stream<String> lineStream = Files.lines(path);//返回一个字符串行的Stream

        Collection<String> collection = Arrays.asList("www", "wyf", "foo", "bar");
        Stream<String> collectionStream = collection.stream();

        Stream<String> streamBuilder = Stream.<String>builder().add("www").add("wyf").add("foo").add("bar").build();


        Stream<String> generatedStream = Stream.generate(() -> "wyf").limit(10);

        Stream<Integer> iteratedStream = Stream.iterate(20, n -> n+1 ).limit(5);//20,21,22,23,24


        IntStream intStream = IntStream.range(1,5); //开始包含，结束不包含
        LongStream longStreamClosed = LongStream.rangeClosed(1,5);//开始包含，结束包含
        DoubleStream doubleStream = new Random().doubles(3);

       IntStream intStream1 = "wyf".chars();

        Optional<String> nameOptional = Optional.of("wyf");
        Stream<String> stringOptionalStream = nameOptional.stream();



    }



}
