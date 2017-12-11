package be.jeremy.functional.programming.exercise;

import java.util.regex.Pattern;

import static be.jeremy.functional.programming.exercise.Case.mCase;
import static be.jeremy.functional.programming.exercise.Case.match;
import static be.jeremy.functional.programming.exercise.Result.failure;
import static be.jeremy.functional.programming.exercise.Result.success;
import static java.util.regex.Pattern.compile;

/**
 * @author Jeremy
 */
public class EmailValidation {

    private static Pattern emailPattern = compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    private static Effect<String> successEffect = s -> System.out.println("Mail sent to " + s);
    private static Effect<String> failureEffect = s -> System.err.println("Error message logged: " + s);

    private static Function<String, Result<String>> emailChecker = s -> match(
            mCase(() -> success(s)),
            mCase(() -> s == null, () -> failure("email must not be null")),
            mCase(() -> s.length() == 0, () -> failure("email must not be empty")),
            mCase(() -> !emailPattern.matcher(s).matches(), () -> failure("email " + s + " is invalid."))
    );

    public static void main(String... args) {
        emailChecker.apply("this.is@my.email").bind(successEffect, failureEffect);
        emailChecker.apply(null).bind(successEffect, failureEffect);
        emailChecker.apply("").bind(successEffect, failureEffect);
        emailChecker.apply("john.doe@acme.com").bind(successEffect, failureEffect);
    }

}
