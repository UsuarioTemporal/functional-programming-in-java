package be.jeremy.functional.programming.exercise;

import java.util.List;

import static be.jeremy.functional.programming.exercise.ListUtilities.append;
import static be.jeremy.functional.programming.exercise.ListUtilities.foldLeft;
import static be.jeremy.functional.programming.exercise.ListUtilities.head;
import static be.jeremy.functional.programming.exercise.ListUtilities.list;
import static be.jeremy.functional.programming.exercise.ListUtilities.tail;
import static be.jeremy.functional.programming.exercise.TailCall.ret;
import static be.jeremy.functional.programming.exercise.TailCall.sus;

/**
 * @author Jeremy
 */
public class Fibonacci {

    public static int iterative(int i) {
        int acc1 = 0;
        int acc2 = 1;

        if (i == 0) {
            return acc1;
        }
        if (i == 1) {
            return acc2;
        }

        for (int j = 2; j <= i; ++j) {
            int tmp = acc2;
            acc2 = acc1 + acc2;
            acc1 = tmp;
        }

        return acc2;
    }

    public static int recursive(int i) {
        if (i == 0) {
            return 0;
        }
        if (i <= 2) {
            return 1;
        }
        return recursive(i - 1) + recursive(i - 2);
    }

    public static int tailCallRecursive(int i) {
        return _tailCallRecursive(i, 0, 1);
    }

    private static int _tailCallRecursive(int i, int acc1, int acc2) {
        if (i == 0) return 0;
        if (i == 1) {
            return acc2;
        }
        return _tailCallRecursive(i - 1, acc2, acc1 + acc2);
    }

    public static Integer stackSafeRecursive(int i) {
        return _stackSafeRecursive(i, 0, 1).eval();
    }

    private static TailCall<Integer> _stackSafeRecursive(int i, Integer acc1, Integer acc2) {
        if (i == 0) return ret(0);
        if (i == 1) {
            return ret(acc2);
        }
        return TailCall.sus(() -> _stackSafeRecursive(i - 1, acc2, acc1 + acc2));
    }

    public static String sequences(int i) {
        List<Integer> seq = _sequences(i, 0, 1, list(0)).eval();
        
        return convertToString(seq);
    }

    private static <T> String convertToString(List<T> l) {
        if (l.isEmpty()) {
            return "";
        }
        if (tail(l).isEmpty()) {
            return head(l).toString();
        }
        return head(l) + foldLeft(tail(l), "", x -> y -> x + ", " + y);
    }

    private static TailCall<List<Integer>> _sequences(int i, Integer acc1, Integer acc2, List<Integer> acc) {
        if (i == 0) {
            return ret(acc);
        } else if (i == 1) {
            return ret(append(acc, acc2));
        } else {
            return sus(() -> _sequences(i - 1, acc2, acc1 + acc2, append(acc, acc2)));
        }
    }

}
