package be.jeremy.functional.programming.exercise;

public class FunctionUtils {

    // Implement the compose function for the exercise 2.1
    public static Function<Integer, Integer> compose(Function<Integer, Integer> f, Function<Integer, Integer> g) {
        return new Function<Integer, Integer>() {
            public Integer apply(Integer arg) {
                return f.apply(g.apply(arg));
            }
        };
    }

    // Implement the compose function for the exercise 2.2
    public static Function<Integer, Integer> composeWithLambda(Function<Integer, Integer> f, Function<Integer, Integer> g) {
        return arg -> f.apply(g.apply(arg));
    }

    // Implement the add operation for the exercise 2.3
    public static Function<Integer, Function<Integer, Integer>> add() {
        return x -> y -> x + y;
    }

    // Implement the curried compose for the exercise 2.4
    public static Function<Function<Integer, Integer>, Function<Function<Integer, Integer>, Function<Integer, Integer>>> curriedCompose() {
        return x -> y -> z -> x.apply(y.apply(z));
    }

    // Implement the polymorphic compose for the exercise 2.5
    public static <T, S, V> Function<Function<S, V>, Function<Function<T, S>, Function<T, V>>> polymorphicCompose() {
        return x -> y -> z -> x.apply(y.apply(z));
    }

    // Implement the andThen for the exercise 2.6
    public static <T, S, V> Function<Function<T, S>, Function<Function<S, V>, Function<T, V>>> andThen() {
        return x -> y -> z -> y.apply(x.apply(z));
    }

    // Implement partial first argument for the exercise 2.7
    public static <A, B, C> Function<B, C> partialFirst(A a, Function<A, Function<B, C>> f) {
        return f.apply(a);
    }

    // Implement partial second argument for the exercise 2.8
    public static <A, B, C> Function<A, C> partialSecond(B b, Function<A, Function<B, C>> f) {
        return a -> f.apply(a).apply(b);
    }

    public static <A, B, C, D> String func(A a, B b, C c, D d) {
        return String.format("%s, %s, %s, %s", a, b, c, d);
    }

    // Implement curryfunction for the exercise 2.9
    public static <A, B, C, D> Function<A, Function<B, Function<C, Function<D, String>>>> curriedFunc() {
        return a -> b -> c -> d -> String.format("%s, %s, %s, %s", a, b, c ,d);
    }

    // Implement curry function of a Tuple for exercise 2.10
    public static <A, B, C> Function<A, Function<B, C>> curriedBiFunction(Function<Tuple<A, B>, C> f) {
        return a -> b -> f.apply(new Tuple<>(a, b));
    }

    public static <A, B, C> Function<B, Function<A, C>> reverseArgs(Function<A, Function<B, C>> f) {
        return b -> a-> f.apply(a).apply(b);
    }
}
