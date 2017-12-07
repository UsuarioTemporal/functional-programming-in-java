package be.jeremy.functional.programming.exercise;

/**
 * @author Jeremy
 */
@FunctionalInterface
public interface Effect<T> {

    void apply(T t);
}
