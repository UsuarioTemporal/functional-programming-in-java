/*
 * Copyright (c) eHealth
 */
package be.jeremy.functional.programming.exercise.result;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Jeremy Vanpé (eh056)
 */
public abstract class Result<V> {

    public Result<V> orElse(Supplier<Result<V>> defaultValue) {
        return  map(s -> this).getOrElse(defaultValue);
    }

    public abstract V getOrElse(final V defaultValue);
    public abstract V getOrElse(final Supplier<V> defaultValue);
    public abstract <U> Result<U> map(Function<V, U> f);
    public abstract <U> Result<U> flatMap(Function<V, Result<U>> f);

    private Result() {}

    public static class Failure<V> extends Result<V> {

        private final RuntimeException exception;

        private Failure(String msg) {
            this.exception = new IllegalStateException(msg);
        }

        private Failure(RuntimeException exception) {
            this.exception = exception;
        }

        private Failure(Exception exception) {
            this.exception = new IllegalStateException(exception);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", exception.getMessage());
        }

        @Override
        public V getOrElse(V defaultValue) {
            return defaultValue;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            return new Failure<>(exception);
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            return new Failure<>(exception);
        }

        @Override
        public boolean equals(Object that) {
            if (that instanceof Failure) {
                return Objects.equals(exception, ((Failure) that).exception);
            }
            return this == that;
        }
    }

    public static class Success<V> extends Result<V> {

        private final V value;

        private Success(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Success(%s)", value);
        }

        @Override
        public V getOrElse(V defaultValue) {
            return value;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return value;
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            return new Success<>(f.apply(value));
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            return f.apply(value);
        }

        @Override
        public boolean equals(Object that) {
            if (that instanceof Success) {
                return Objects.equals(value, ((Success) that).value);
            }
            return this == that;
        }
    }

    public static <V> Result<V> failure(String message) {
        return new Failure<>(message);
    }

    public static <V> Result<V> failure(RuntimeException exception) {
        return new Failure<>(exception);
    }

    public static <V> Result<V> failure(Exception exception) {
        return new Failure<>(exception);
    }

    public static <V> Result<V> success(V value) {
        return new Success<>(value);
    }
}
