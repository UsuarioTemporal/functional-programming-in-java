package be.jeremy.functional.programming.exercise.result;

import org.junit.Test;

import static be.jeremy.functional.programming.exercise.result.Either.left;
import static be.jeremy.functional.programming.exercise.result.Either.right;
import static org.assertj.core.api.Assertions.assertThat;

public class EitherTest {

    @Test
    public void map() {
        assertThat(left("fault").map(Object::toString)).isEqualTo(left("fault"));
        assertThat(right(10L).map(Object::toString)).isEqualTo(right("10"));
    }

    @Test
    public void flatMap() {
        assertThat(left("fault").flatMap(e -> right("10"))).isEqualTo(left("fault"));
        assertThat(right(10L).flatMap(e -> right("10"))).isEqualTo(right("10"));
    }
}