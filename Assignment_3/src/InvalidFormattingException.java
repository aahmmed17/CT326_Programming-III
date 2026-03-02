/**
 * Custom exception for invalid formatting in Book and Review objects.
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class InvalidFormattingException extends RuntimeException {

    /**
     * Constructs a new InvalidFormattingException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidFormattingException(String message) {
        super(message);
    }
}
