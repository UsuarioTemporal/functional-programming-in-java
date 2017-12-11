package be.jeremy.functional.programming.exercise;

import java.util.function.Supplier;

import static java.util.Arrays.stream;

/**
 * @author Jeremy
 */
public class Case<T> {

    private Supplier<Boolean> condition;

    private Supplier<Result<T>> value;

    public Case(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        this.condition = condition;
        this.value = value;
    }

    public static <T> Case<T> mCase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        Case<T> tCase = new Case<>(condition, value);
        tCase.condition = condition;
        tCase.value = value;

        return tCase;
    }

    public static <T> DefaultCase<T> mCase(Supplier<Result<T>> value) {
        return new DefaultCase<>(value);
    }

    public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... cases) {
        return stream(cases)
                .filter(c -> c.condition.get())
                .findFirst()
                .orElse(defaultCase)
                .value.get();
    }

    private static class DefaultCase<T> extends Case<T> {

        public DefaultCase(Supplier<Result<T>> value) {
            super(null, value);
        }
    }
}
