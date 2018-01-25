package be.jeremy.functional.programming.exercise;

import static be.jeremy.functional.programming.exercise.TailCall.ret;
import static be.jeremy.functional.programming.exercise.TailCall.sus;

/**
 * @author Jeremy
 */
public abstract class List<T> {

    public abstract T head();

    public abstract List<T> tail();

    public abstract Boolean isEmpty();

    public abstract List<T> setHead(T t);

    public abstract List<T> drop(int i);

    public abstract List<T> dropWhile(Function<T, Boolean> p);

    public abstract List<T> init();

    public List<T> reverse() {
        return _reverse(list());
    }

    private List<T> _reverse(List<T> acc) {
        if (isEmpty()) {
            return acc;
        }
        return tail()._reverse(acc.cons(head()));
    }

    private List() {
    }

    public static final List NIL = new Nil();

    public static <T> List<T> list() {
        return NIL;
    }

    @SafeVarargs
    public static <T> List<T> list(T... ts) {
        List<T> result = list();

        for (int i = ts.length - 1; i >= 0; --i) {
            result = new Cons(ts[i], result);
        }

        return result;
    }

    public static <T> List<T> setHead(List<T> list, T t) {
        return list.setHead(t);
    }

    public List<T> cons(T t) {
        return new Cons<T>(t, this);
    }

    private static class Nil<T> extends List<T> {

        private Nil() {
        }

        @Override
        public T head() {
            throw new IllegalStateException("head called on empty list");
        }

        @Override
        public List<T> tail() {
            throw new IllegalStateException("tail called on empty list");
        }

        @Override
        public Boolean isEmpty() {
            return true;
        }

        @Override
        public List<T> setHead(T t) {
            throw new IllegalStateException("setHead called on empty list");
        }

        @Override
        public List<T> drop(int i) {
            return this;
        }

        @Override
        public List<T> dropWhile(Function<T, Boolean> p) {
            return this;
        }

        @Override
        public List<T> init() {
            return this;
        }

        @Override
        public String toString() {
            return "[NIL]";
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    private static class Cons<T> extends List<T> {

        private T head;
        private List<T> tail;

        private Cons(T head, List<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public List<T> tail() {
            return tail;
        }

        @Override
        public Boolean isEmpty() {
            return false;
        }

        @Override
        public List<T> setHead(T t) {
            return new Cons<>(t, this.tail);
        }

        @Override
        public List<T> drop(int i) {
            if (i <= 0) {
                return this;
            }
            return tail().drop(i - 1);
        }

        @Override
        public List<T> dropWhile(Function<T, Boolean> p) {
            if (p.apply(head())) {
                return tail().dropWhile(p);
            }
            return this;
        }

        @Override
        public List<T> init() {
            return reverse().tail().reverse();
        }

        @Override
        public String toString() {
            return String.format("[%sNIL]", toString(new StringBuilder(), this).eval());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cons) {
                return head.equals(((Cons) obj).head) && tail.equals(((Cons) obj).tail);
            }
            return false;
        }

        private TailCall<String> toString(StringBuilder builder, List<T> l) {
            if (l.isEmpty()) {
                return ret(builder.toString());
            } else {
                return sus(() -> toString(builder.append(l.head()).append(", "), l.tail()));
            }
        }
    }
}
