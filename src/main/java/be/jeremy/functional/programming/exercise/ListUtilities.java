package be.jeremy.functional.programming.exercise;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.*;

public class ListUtilities {

    public static <T> List<T> list() {
        return emptyList();
    }

    public static <T> List<T> list(T val) {
        return singletonList(val);
    }

    public static <T> List<T> list(List<T> ts) {
        return unmodifiableList(new ArrayList<>(ts));
    }

    @SafeVarargs
    public static <T> List<T> list(T... values) {
        return asList(values);
    }

    public static <T> T head(List<T> ts) {
        if (ts.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty !");
        }
        return ts.get(0);
    }

    public static <T> List<T> tail(List<T> ts) {
        if (ts.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty !");
        }
        List<T> tail = new ArrayList<>(ts);
        tail.remove(0);

        return unmodifiableList(tail);
    }

    public static Integer fold(List<Integer> l, Integer startValue, Function<Integer, Function<Integer, Integer>> f) {
        if (l.isEmpty()) {
            return startValue;
        }
        return fold(tail(l), f.apply(startValue).apply(head(l)), f);
    }

    public static <T> T foldLeft(List<Integer> l, T startValue, Function<T, Function<Integer, T>> f) {
        if (l.isEmpty()) {
            return startValue;
        }
        return foldLeft(tail(l), f.apply(startValue).apply(head(l)), f);
    }

    public static <T> T imperativeFoldRight(List<Integer> l, T startValue, Function<Integer, Function<T, T>> f) {
        Function<T, T> g = x -> x;
        for (Integer i : l) {
            Function<T, T> finalG = g;
            g = x -> finalG.apply(f.apply(i).apply(x));
        }

        return g.apply(startValue);
    }

    public static <T> T foldRight(List<Integer> l, T startValue, Function<Integer, Function<T, T>> f) {
        if (l.isEmpty()) {
            return startValue;
        }

        return f.apply(head(l)).apply(foldRight(tail(l), startValue, f));
    }
}
