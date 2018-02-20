package be.jeremy.functional.programming.exercise.result;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Jeremy
 */
public abstract class Either<Error, Success> {

    public Either<Error, Success> orElse(Supplier<Either<Error, Success>> defaultValue) {
        return map(x -> this).getOrElse(defaultValue);
    }

    public abstract <V> Either<Error, V> map(Function<Success, V> f);
    public abstract <V> Either<Error, V> flatMap(Function<Success, Either<Error, V>> f);
    public abstract Success getOrElse(Supplier<Success> defaultValue);


    public static class Left<Error, Success> extends Either<Error, Success> {
        private final Error value;

        private Left(Error value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Left(%s)", value);
        }

        @Override
        public boolean equals(Object that) {
            if (that instanceof Left) {
                return Objects.equals(value, ((Left) that).value);
            }
            return this == that;
        }

        @Override
        public int hashCode() {

            return Objects.hash(value);
        }

        @Override
        public <V> Either<Error, V> map(Function<Success, V> f) {
            return new Left<>(value);
        }

        @Override
        public <V> Either<Error, V> flatMap(Function<Success, Either<Error, V>> f) {
            return new Left<>(value);
        }

        @Override
        public Success getOrElse(Supplier<Success> defaultValue) {
            return defaultValue.get();
        }

    }

    public static class Right<Error, Success> extends Either<Error, Success> {
        private final Success value;

        private Right(Success value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Right(%s)", value);
        }

        @Override
        public boolean equals(Object that) {
            if (that instanceof Right) {
                return Objects.equals(value, ((Right) that).value);
            }
            return this == that;
        }

        @Override
        public <V> Either<Error, V> map(Function<Success, V> f) {
            return new Right<>(f.apply(value));
        }

        @Override
        public <V> Either<Error, V> flatMap(Function<Success, Either<Error, V>> f) {
            return f.apply(value);
        }

        @Override
        public Success getOrElse(Supplier<Success> defaultValue) {
            return value;
        }

    }

    public static <T, U> Either<T, U> left(T value) {
        return new Left<>(value);
    }

    public static <T, U> Either<T, U> right(U value) {
        return new Right<>(value);
    }

}
