package be.jeremy.functional.programming.exercise;

/**
 * @author Jeremy
 */
public interface Result {

    public class Success implements Result {}

    public class Failure implements Result {

        private final String errorMessage;

        public Failure(String s) {
            this.errorMessage = s;
        }

        public String getMessage() {
            return errorMessage;
        }
    }
}
