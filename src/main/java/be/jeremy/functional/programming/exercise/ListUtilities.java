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

    public static <T, U> U foldLeft(List<T> l, U startValue, Function<U, Function<T, U>> f) {
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

    public static <T, U> U foldRight(List<T> l, U startValue, Function<T, Function<U, U>> f) {
        if (l.isEmpty()) {
            return startValue;
        }

        return f.apply(head(l)).apply(foldRight(tail(l), startValue, f));
    }

    public static <T> List<T> append(List<T> l, T elem) {
        List<T> newList = new ArrayList<>(l);
        newList.add(elem);

        return unmodifiableList(newList);
    }

    public static <T> List<T> prepend(List<T> l, T elem) {
        return foldLeft(l, list(elem), nl -> e -> append(nl, e));
    }

    public static <T> Function<List<T>, List<T>> reverse() {
        return l -> foldLeft(l, ListUtilities.list(), nl -> e -> prepend(nl, e));
    }

    public static <T, U> List<U> mapFoldLeft(List<T> l, Function<T, U> f) {
        return foldLeft(l, list(), nl -> e -> append(nl, f.apply(e)));
    }

    public static <T, U> List<U> mapFoldRight(List<T> l, Function<T, U> f) {
        return foldRight(l, list(), e -> nl -> prepend(nl, f.apply(e)));
    }

    public static List<Integer> range(int start, int end) {
        List<Integer> result = new ArrayList<>();
        int temp = start;

        while (temp < end) {
            result = append(result, temp);
            ++temp;
        }

        return result;
    }
}
