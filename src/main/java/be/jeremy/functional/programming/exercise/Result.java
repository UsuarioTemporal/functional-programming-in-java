package be.jeremy.functional.programming.exercise;

/**
 * @author Jeremy
 */
public interface Result<T> {

    void bind(Effect<T> success, Effect<T> failure);

    public static <T> Result<T> success(T value) {
        return new Success(value);
    }

    public static <T> Result<T> failure(T value) {
        return new Failure(value);
    }

    class Success<T> implements Result<T> {
        private T value;

        private Success(T value) {
            this.value =value;
        }

        @Override
        public void bind(Effect<T> success, Effect<T> failure) {
            success.apply(value);
        }

    }

    class Failure<T> implements Result<T> {

        private final T value;

        private Failure(T value) {
            this.value = value;
        }

        @Override
        public void bind(Effect<T> success, Effect<T> failure) {
            failure.apply(value);
        }
    }
}
