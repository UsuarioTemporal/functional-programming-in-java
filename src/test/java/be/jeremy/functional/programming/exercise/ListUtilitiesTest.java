package be.jeremy.functional.programming.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static be.jeremy.functional.programming.exercise.ListUtilities.list;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ListUtilitiesTest {

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
}