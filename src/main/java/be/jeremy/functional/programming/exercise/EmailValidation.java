package be.jeremy.functional.programming.exercise;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author Jeremy
 */
public class EmailValidation {

    static Pattern emailPattern = compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result> emailChecker = s ->
            s == null
                    ? new Result.Failure("email must not be null")
                    : s.length() == 0 ? new Result.Failure("email must not be empty")
                    : emailPattern.matcher(s).matches() ? new Result.Success()
                    : new Result.Failure("email " + s + " is invalid.");

    static Executable validate(String s) {
        Result result = emailChecker.apply(s);

        return (result instanceof Result.Success)
                ? () -> sendVerificationMail(s)
                : () -> logError(((Result.Failure) result).getMessage());
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Mail sent to " + s);
    }

    private static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    public static void main(String... args) {
        validate("this.is@my.email").exec();
        validate(null).exec();
        validate("").exec();
        validate("john.doe@acme.com").exec();
    }

}
