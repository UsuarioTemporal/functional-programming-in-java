package be.jeremy.functional.programming.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static be.jeremy.functional.programming.exercise.ListUtilities.list;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ListUtilitiesTest {

    private static Function<Integer, Function<String, String>> addIS = x -> y -> "(" + x + " + " + y + ")";
    private static Function<String, Function<Integer, String>> addSI = x -> y -> "(" + x + " + " + y + ")";
    private static Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;

    @Test(expected = UnsupportedOperationException.class)
    public void emptyList() {
        List<Integer> l = list();

        l.add(10);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void singletonList() {
        List<Integer> l = list(10);

        l.add(11);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void varargsList() {
        List<Integer> l = list(10, 11, 12);

        l.add(15);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void newList() {
        ArrayList<Object> ts = new ArrayList<>();
        ts.add(10);

        List<Object> l = list(ts);

        l.add(11);
    }

    @Test
    public void head() {
        List<Integer> l = asList(2, 4, 6, 8);

        assertThat(ListUtilities.head(l)).isEqualTo(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void head_whenEmptyList() {
        ListUtilities.<Integer>head(Collections.emptyList());
    }

    @Test
    public void tail() {
        List<Integer> l = asList(2, 4, 6, 8);

        assertThat(ListUtilities.tail(l)).hasSize(3).containsSequence(4, 6, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tail_whenEmptyList() {
        ListUtilities.tail(Collections.emptyList());
    }

    @Test
    public void fold() {
        assertThat(ListUtilities.fold(ListUtilities.list(10, 15, 25), 0, add)).isEqualTo(50);
    }
    @Test
    public void fold_whenEmpty() {
        assertThat(ListUtilities.fold(ListUtilities.list(), 0, add)).isEqualTo(0);
    }

    @Test
    public void foldLeft() {
        assertThat(ListUtilities.foldLeft(ListUtilities.list(10, 15, 25), "0", addSI)).isEqualTo("(((0 + 10) + 15) + 25)");
    }
    @Test
    public void foldLeft_whenEmpty() {
        assertThat(ListUtilities.foldLeft(ListUtilities.list(), "0", addSI)).isEqualTo("0");
    }

    @Test
    public void imperativeFoldRight() {
        assertThat(ListUtilities.imperativeFoldRight(ListUtilities.list(10, 15, 25), "0", addIS)).isEqualTo("(10 + (15 + (25 + 0)))");
    }

    @Test
    public void imperativeFoldRight_whenEmptyList() {
        assertThat(ListUtilities.imperativeFoldRight(ListUtilities.list(), "0", addIS)).isEqualTo("0");
    }

    @Test
    public void foldRight() {
        assertThat(ListUtilities.foldRight(ListUtilities.list(10, 15, 25), "0", addIS)).isEqualTo("(10 + (15 + (25 + 0)))");
    }

    @Test
    public void foldRight_whenEmptyList() {
        assertThat(ListUtilities.foldRight(ListUtilities.list(), "0", addIS)).isEqualTo("0");
    }

    @Test
    public void prepend() {
        assertThat(ListUtilities.prepend(list(3, 4, 5), 2))
                .hasSize(4).containsSequence(2, 3, 4, 5);
    }

    @Test
    public void prepend_whenEmptyList() {
        assertThat(ListUtilities.prepend(list(), 2)).hasSize(1).containsSequence(2);
    }

    @Test
    public void reverse() {
        assertThat(ListUtilities.reverse().apply(list(2, 3, 4))).hasSize(3).containsSequence(4, 3, 2);
    }

    @Test
    public void reverse_whenEmptyList() {
        assertThat(ListUtilities.reverse().apply(list())).isEmpty();
    }

    @Test
    public void mapFoldLeft() {
        assertThat(ListUtilities.mapFoldLeft(list(2, 3, 5), x -> 2 * x)).hasSize(3).containsSequence(4, 6, 10);
    }

    @Test
    public void mapFoldRight() {
        assertThat(ListUtilities.mapFoldRight(list(2, 3, 5), x -> 2 * x)).hasSize(3).containsSequence(4, 6, 10);
    }

    @Test
    public void range() {
        assertThat(ListUtilities.range(1, 5)).containsSequence(1, 2, 3, 4);
    }
}
