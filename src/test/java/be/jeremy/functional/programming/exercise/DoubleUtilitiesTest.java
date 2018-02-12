package be.jeremy.functional.programming.exercise;

import org.junit.Test;

import static be.jeremy.functional.programming.exercise.DoubleUtilities.mean;
import static be.jeremy.functional.programming.exercise.DoubleUtilities.sum;
import static be.jeremy.functional.programming.exercise.DoubleUtilities.variance;
import static be.jeremy.functional.programming.exercise.optional.Option.none;
import static be.jeremy.functional.programming.exercise.optional.Option.some;
import static org.assertj.core.api.Assertions.assertThat;

public class DoubleUtilitiesTest {

    @Test
    public void testSum() {
        assertThat(sum.apply(List.list())).isEqualTo(0.0);
        assertThat(sum.apply(List.list(1.0, 2.0, 3.0))).isEqualTo(6.0);
    }

    @Test
    public void testMean() {
        assertThat(mean.apply(List.list())).isEqualTo(none());
        assertThat(mean.apply(List.list(1.0, 2.0, 3.0))).isEqualTo(some(2.0));
    }

    @Test
    public void testVariance() {
        assertThat(variance.apply(List.list())).isEqualTo(none());
        assertThat(variance.apply(List.list(1.0, 2.0, 3.0))).isEqualTo(some(2.0 / 3.0));
    }

}