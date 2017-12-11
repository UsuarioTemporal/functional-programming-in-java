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
}
