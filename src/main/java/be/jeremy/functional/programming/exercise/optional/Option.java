package be.jeremy.functional.programming.exercise.optional;

import java.util.function.Supplier;

/**
 * @author Jeremy
 */
public abstract class Option<T> {

    public abstract T getOrThrow();
    public abstract T getOrElse(T defaultValue);
    public abstract T getOrElse(Supplier<T> defaultValueSupplier);

    @SuppressWarnings("rawtypes")
    private static Option none = new None();

    private static class None<T> extends Option<T> {

        private None() {}

        @Override
        public T getOrThrow() {
            throw new IllegalArgumentException("get called on None");
        }

        @Override
        public T getOrElse(T defaultValue) {
            return defaultValue;
        }

        @Override
        public T getOrElse(Supplier<T> defaultValueSupplier) {
            return defaultValueSupplier.get();
        }

        @Override
        public String toString() {
            return "None";
        }
    }

    private static class Some<T> extends Option<T> {

        private final T value;

        private Some(T value) {
            this.value = value;
        }

        @Override
        public T getOrThrow() {
            return value;
        }

        @Override
        public T getOrElse(T defaultValue) {
            return value;
        }

        @Override
        public T getOrElse(Supplier<T> defaultValueSupplier) {
            return value;
        }

        @Override
        public String toString() {
            return String.format("Some(%s)", value);
        }
    }

    public static <T> Option<T> some(T value) {
        return new Some<>(value);
    }

    public static <T> Option<T> none() {
        return none;
    }
}
