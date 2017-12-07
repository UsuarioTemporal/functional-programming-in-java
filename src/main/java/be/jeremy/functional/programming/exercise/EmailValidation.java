package be.jeremy.functional.programming.exercise;

import java.util.regex.Pattern;

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

    static Function<String, Result<String>> emailChecker = s ->
            s == null
                    ? failure("email must not be null")
                    : s.length() == 0 ? failure("email must not be empty")
                    : emailPattern.matcher(s).matches() ? success(s)
                    : failure("email " + s + " is invalid.");

    public static void main(String... args) {
        emailChecker.apply("this.is@my.email").bind(successEffect, failureEffect);
        emailChecker.apply(null).bind(successEffect, failureEffect);
        emailChecker.apply("").bind(successEffect, failureEffect);
        emailChecker.apply("john.doe@acme.com").bind(successEffect, failureEffect);
    }

}
