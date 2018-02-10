package be.jeremy.functional.programming.exercise.optional;

import org.junit.Test;

import static be.jeremy.functional.programming.exercise.optional.Option.none;
import static be.jeremy.functional.programming.exercise.optional.Option.some;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionTest {

    @Test
    public void testMap() {
        assertThat(none().map(Object::toString)).isEqualTo(none());
        assertThat(some(10L).map(Object::toString)).isEqualTo(some("10"));
    }
}