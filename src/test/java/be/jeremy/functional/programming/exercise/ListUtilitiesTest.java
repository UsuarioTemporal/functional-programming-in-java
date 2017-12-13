package be.jeremy.functional.programming.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static be.jeremy.functional.programming.exercise.ListUtilities.list;

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

}