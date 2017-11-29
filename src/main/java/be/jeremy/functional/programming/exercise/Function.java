package be.jeremy.functional.programming.exercise;

/**
 * Function interface for the exercise 2.1 from Functional Programming in Java (Pierre Yves Soumont)
 *
 * @author Jeremy
 */
@FunctionalInterface
public interface Function<T, U> {

    U apply(T t);

}