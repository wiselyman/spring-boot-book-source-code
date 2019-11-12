package top.wisely.functionalprogramming.custom_function;

@FunctionalInterface
public interface TriFunction<T, U, W, R> {
    R apply(T t, U u, W w);
}
