package be.jeremy.functional.programming.exercise.result;

import org.junit.Test;

import static be.jeremy.functional.programming.exercise.result.Either.right;
import static be.jeremy.functional.programming.exercise.result.Result.failure;
import static be.jeremy.functional.programming.exercise.result.Result.success;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @Test
    public void orElse() {
        assertThat(failure("fault").orElse(() -> success("default"))).isEqualTo(success("default"));
        assertThat(success("10").orElse(() -> success("default"))).isEqualTo(success("10"));
    }

    @Test
    public void getOrElse() {
        assertThat(failure("fault").getOrElse("default")).isEqualTo("default");
        assertThat(success("10").getOrElse("default")).isEqualTo("10");
    }

    @Test
    public void getOrElseSupplier() {
        assertThat(failure("fault").getOrElse(() -> "default")).isEqualTo("default");
        assertThat(success("10").getOrElse(() -> "default")).isEqualTo("10");
    }

    @Test
    public void map() {
        Result<?> fault = failure("fault");

        assertThat(fault.map(Object::toString)).isEqualTo(fault);
        assertThat(right(10L).map(Object::toString)).isEqualTo(right("10"));
    }

    @Test
    public void flatMap() {
        Result<String> fault = failure("fault");

        assertThat(fault.flatMap(e -> success("10"))).isEqualTo(fault);
        assertThat(success(10L).flatMap(e -> success("10"))).isEqualTo(success("10"));
    }

}