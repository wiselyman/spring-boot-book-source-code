package top.wisely;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.Stream;

public class FluxAndMono {
    public static void main(String[] args) {
//        fluxDemo();
//        monoDemo();
        operation();
    }

    private static void fluxDemo(){
        Flux<String> flux1 = Flux.just("a", "b", "b");
        Flux<String> flux2 = Flux.fromArray(new String[]{"c", "d", "e"});
        Flux<String> flux3 = Flux.fromStream(Stream.of("e", "f", "g"));
        Flux<Integer> flux4 = Flux.range(2019,3);
        System.out.println("-------------");
        flux1.subscribe(System.out::print);
        System.out.println();
        flux2.subscribe(System.out::print);
        System.out.println();
        flux3.subscribe(System.out::print);
        System.out.println();
        flux4.subscribe(new Subscriber<Integer>() {
            volatile Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {
                subscription = s;
                System.out.println("初始化请求一个数据");
                subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("当前数据:" + integer);
                System.out.println("再请求一个数据");
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("处理完成");
            }
        });
    }

    private static void monoDemo(){
        Mono<String> mono1 = Mono.just("a");
        Mono<String> mono2 = Mono.from(Flux.just("b"));
        System.out.println("-------------");
        mono1.subscribe(System.out::println);
        mono2.subscribe(new Subscriber<String>() {
            volatile Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {
                subscription = s;
                System.out.println("初始化请求一个数据");
                subscription.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println("当前数据:" + s);
                System.out.println("再请求一个数据");
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("处理完成");
            }
        });

    }

    private static void operation(){
        Flux<Integer> flux1 = Flux.just(1, 6, 4, 3, 5, 2);
        flux1
          .map(i -> i * 3)
          .filter(i -> i % 2 == 0)
          .sort(Comparator.comparingInt(Integer::intValue))
          .subscribe(System.out::println);

    }
}
