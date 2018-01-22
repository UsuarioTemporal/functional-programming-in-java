package be.jeremy.functional.programming.exercise;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Jeremy Vanpé
 */
public class ListTest {

    @Test
    public void toStr() {
        assertThat(List.list().toString()).isEqualTo("[NIL]");
        assertThat(List.list(1, 2, 3, 4).toString()).isEqualTo("[1, 2, 3, 4, NIL]");
    }

    @Test
    public void drop() {
        assertThat(List.list().drop(2)).isEqualTo(List.list());
        assertThat(List.list(1,2,3, 4).drop(0)).isEqualTo(List.list(1, 2, 3, 4));
        assertThat(List.list(1,2,3, 4).drop(2)).isEqualTo(List.list(3, 4));
    }
}