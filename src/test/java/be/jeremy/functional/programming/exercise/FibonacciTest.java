package be.jeremy.functional.programming.exercise;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jeremy
 */
public class FibonacciTest {

    @Test
    public void iterative() {
        assertThat(Fibonacci.iterative(0)).isEqualTo(0);
        assertThat(Fibonacci.iterative(1)).isEqualTo(1);
        assertThat(Fibonacci.iterative(6)).isEqualTo(8);
    }

    @Test
    public void recursive() {
        assertThat(Fibonacci.recursive(0)).isEqualTo(0);
        assertThat(Fibonacci.recursive(1)).isEqualTo(1);
        assertThat(Fibonacci.recursive(6)).isEqualTo(8);
    }

    @Test
    public void tailCallRecursive() {
        assertThat(Fibonacci.tailCallRecursive(0)).isEqualTo(0);
        assertThat(Fibonacci.tailCallRecursive(1)).isEqualTo(1);
        assertThat(Fibonacci.tailCallRecursive(6)).isEqualTo(8);
    }
}