package be.jeremy.functional.programming.exercise;

import static be.jeremy.functional.programming.exercise.TailCall.ret;

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

}
