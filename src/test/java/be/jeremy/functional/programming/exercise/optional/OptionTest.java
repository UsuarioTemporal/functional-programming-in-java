package be.jeremy.functional.programming.exercise.optional;

import org.junit.Test;

import static be.jeremy.functional.programming.exercise.List.list;
import static be.jeremy.functional.programming.exercise.optional.Option.none;
import static be.jeremy.functional.programming.exercise.optional.Option.sequence;
import static be.jeremy.functional.programming.exercise.optional.Option.some;
import static be.jeremy.functional.programming.exercise.optional.Option.traverse;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionTest {

    @Test
    public void testMap() {
        assertThat(none().map(Object::toString)).isEqualTo(none());
        assertThat(some(10L).map(Object::toString)).isEqualTo(some("10"));
    }

    @Test
    public void testFlatMap() {
        assertThat(none().flatMap(o -> some(o.toString()))).isEqualTo(none());
        assertThat(some(10L).flatMap(o -> some(o.toString()))).isEqualTo(some("10"));
    }

    @Test
    public void testFilter() {
        assertThat(Option.<Integer>none().filter(i -> i % 2 == 0)).isEqualTo(none());
        assertThat(some(5).filter(i -> i % 2 == 0)).isEqualTo(none());
        assertThat(some(10).filter(i -> i % 2 == 0)).isEqualTo(some(10));
        throw new NumberFormatException();
    }

    @Test
    public void testLift() {
        assertThat(Option.<Double, Double>lift(Math::abs).apply(some(-1.0))).isEqualTo(some(1.0));
    }

    @Test
    public void testLiftWhenThrow() {
        assertThat(Option.<Double, Double>liftWhenThrow(Math::abs).apply(some(-1.0))).isEqualTo(some(1.0));
        assertThat(Option.<Double, Double>liftWhenThrow(d -> {
            throw new RuntimeException();
        }).apply(some(1.0))).isEqualTo(none());
    }

    @Test
    public void testSequence() {
        assertThat(sequence(list())).isEqualTo(some(list()));
        assertThat(sequence(list(some(10), some(11), none(), some(12)))).isEqualTo(none());
        assertThat(sequence(list(some(10), some(11), some(12)))).isEqualTo(some(list(10, 11, 12)));
    }

    @Test
    public void testTraverse() {
        assertThat(traverse(list(), Option::some)).isEqualTo(some(list()));
        assertThat(traverse(list(1, 2, 3, 4), Option::some)).isEqualTo(some(list(1, 2, 3, 4)));
        assertThat(traverse(list(1, 2, 3, 4), x -> x % 2 == 0 ? some(x): none())).isEqualTo(none());
    }
}