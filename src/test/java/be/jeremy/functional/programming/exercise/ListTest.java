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
        assertThat(List.list(1, 2, 3, 4).drop(0)).isEqualTo(List.list(1, 2, 3, 4));
        assertThat(List.list(1, 2, 3, 4).drop(2)).isEqualTo(List.list(3, 4));
    }

    @Test
    public void dropWhile() {
        Function<Integer, Boolean> even = i -> i % 2 == 0;
        assertThat(List.<Integer>list().dropWhile(even)).isEqualTo(List.list());
        assertThat(List.list(1, 2, 3, 4).dropWhile(even)).isEqualTo(List.list(1, 2, 3, 4));
        assertThat(List.list(2, 4, 3, 6).dropWhile(even)).isEqualTo(List.list(3, 6));
    }

    @Test
    public void reverse() {
        assertThat(List.list().reverse()).isEqualTo(List.list());
        assertThat(List.list(1, 2, 3, 4).reverse()).isEqualTo(List.list(4, 3, 2, 1));
    }

    @Test
    public void reverseViaFoldLeft() {
        assertThat(List.reverseViaFoldLeft(List.list())).isEqualTo(List.list());
        assertThat(List.reverseViaFoldLeft(List.list(1, 2, 3, 4))).isEqualTo(List.list(4, 3, 2, 1));
    }

    @Test
    public void init() {
        assertThat(List.list().init()).isEqualTo(List.list());
        assertThat(List.list(1, 2, 3, 4).init()).isEqualTo(List.list(1, 2, 3));
    }

    @Test
    public void sum() {
        assertThat(List.sum(List.list())).isEqualTo(0);
        assertThat(List.sum(List.list(1, 2, 3, 4))).isEqualTo(10);
    }

    @Test
    public void stackSafeSum() {
        assertThat(List.stackSafeSum(List.list())).isEqualTo(0);
        assertThat(List.stackSafeSum(List.list(1, 2, 3, 4))).isEqualTo(10);
    }

    @Test
    public void product() {
        assertThat(List.product(List.list())).isEqualTo(1.0);
        assertThat(List.product(List.list(1.0, 2.0, 3.0, 4.0))).isEqualTo(24);
    }

    @Test
    public void foldRight() {
        assertThat(List.foldRight(List.<Integer>list(), 0, x -> y -> x + y)).isEqualTo(0);
        assertThat(List.foldRight(List.list(1, 2, 3, 4), 0, x -> y -> x + y)).isEqualTo(10);

        assertThat(List.foldRight(List.<Double>list(), 1.0, x -> y -> x * y)).isEqualTo(1.0);
        assertThat(List.foldRight(List.list(1.0, 2.0, 3.0, 4.0), 1.0, x -> y -> x * y)).isEqualTo(24);
    }

    @Test
    public void foldRightViaFoldLeft() {
        assertThat(List.foldRightViaFoldLeft(List.<Integer>list(), 0, x -> y -> x + y)).isEqualTo(0);
        assertThat(List.foldRightViaFoldLeft(List.list(1, 2, 3, 4), 0, x -> y -> x + y)).isEqualTo(10);

        assertThat(List.foldRightViaFoldLeft(List.<Double>list(), 1.0, x -> y -> x * y)).isEqualTo(1.0);
        assertThat(List.foldRightViaFoldLeft(List.list(1.0, 2.0, 3.0, 4.0), 1.0, x -> y -> x * y)).isEqualTo(24);
    }

    @Test
    public void tailCallFoldRight() {
        assertThat(List.tailCallFoldRight(List.<Integer>list(), 0, x -> y -> x + y)).isEqualTo(0);
        assertThat(List.tailCallFoldRight(List.list(1, 2, 3, 4), 0, x -> y -> x + y)).isEqualTo(10);

        assertThat(List.tailCallFoldRight(List.<Double>list(), 1.0, x -> y -> x * y)).isEqualTo(1.0);
        assertThat(List.tailCallFoldRight(List.list(1.0, 2.0, 3.0, 4.0), 1.0, x -> y -> x * y)).isEqualTo(24);
    }

    @Test
    public void length() {
        assertThat(List.length(List.list())).isEqualTo(0);
        assertThat(List.length(List.list(1, 2, 3, 4))).isEqualTo(4);
    }

    @Test
    public void stackSafeLength() {
        assertThat(List.stackSafeLength(List.list())).isEqualTo(0);
        assertThat(List.stackSafeLength(List.list(1, 2, 3, 4))).isEqualTo(4);
    }

    @Test
    public void foldLeft() {
        assertThat(List.list(10, 15, 25).foldLeft("0", x -> y -> "(" + x + " + " + y + ")"))
                .isEqualTo("(((0 + 10) + 15) + 25)");
    }

    @Test
    public void concat() {
        assertThat(List.concat(List.list(), List.list())).isEqualTo(List.list());
        assertThat(List.concat(List.list(1, 2, 3), List.list())).isEqualTo(List.list(1, 2, 3));
        assertThat(List.concat(List.list(), List.list(1, 2, 3))).isEqualTo(List.list(1, 2, 3));
        assertThat(List.concat(List.list(1, 2, 3), List.list(4, 5, 6))).isEqualTo(List.list(1, 2, 3, 4, 5, 6));
    }

    @Test
    public void flatten() {
        assertThat(List.flatten(List.list())).isEqualTo(List.list());
        assertThat(List.flatten(List.list(List.list(1, 2, 3)))).isEqualTo(List.list(1, 2, 3));
        assertThat(List.flatten(List.list(List.list(1, 2, 3), List.list(4, 5, 6), List.list(), List.list(7, 8))))
                .isEqualTo(List.list(1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void threeTimes() {
        assertThat(List.threeTimes(List.list())).isEqualTo(List.list());
        assertThat(List.threeTimes(List.list(1, 2, 3))).isEqualTo(List.list(3, 6, 9));
    }

    @Test
    public void doubleToString() {
        assertThat(List.doubleToString(List.list())).isEqualTo(List.list());
        assertThat(List.doubleToString(List.list(1.0, 2.0, 3.0))).isEqualTo(List.list("1.0", "2.0", "3.0"));
    }

    @Test
    public void map() {
        assertThat(List.<Integer>list().map(x -> x * 3)).isEqualTo(List.list());
        assertThat(List.list(1, 2, 3).map(x -> x * 3)).isEqualTo(List.list(3, 6, 9));
    }

    @Test
    public void filter() {
        assertThat(List.<Integer>list().filter(x -> x % 2 == 0)).isEqualTo(List.list());
        assertThat(List.list(1, 2, 3, 4, 5, 6).filter(x -> x % 2 == 0)).isEqualTo(List.list(2, 4, 6));
    }

    @Test
    public void filterViaFlatmap() {
        assertThat(List.filterViaFlatmap(List.<Integer>list(), x -> x % 2 == 0)).isEqualTo(List.list());
        assertThat(List.filterViaFlatmap(List.list(1, 2, 3, 4, 5, 6), x -> x % 2 == 0)).isEqualTo(List.list(2, 4, 6));
    }

    @Test
    public void flatmap() {
        assertThat(List.<Integer>list().flatMap(x -> List.list(x, -x))).isEqualTo(List.list());
        assertThat(List.list(1, 2, 3).flatMap(x -> List.list(x, -x))).isEqualTo(List.list(1, -1, 2, -2, 3, -3));
    }
}