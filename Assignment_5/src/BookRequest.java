/**
 * Handles book requests to be processed by a Library
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class BookRequest {

    public enum Type { CHECKOUT, RETURN }

    private final int bookId;
    private final Type type;
    private final int amount;

    public static final BookRequest POISON_PILL =
            new BookRequest(-1, Type.RETURN, 0);


    /**
     *
     * @param bookId the book ID
     * @param type type of book
     * @param amount the number of books in stock
     */

    public BookRequest(int bookId, Type type, int amount) {
        this.bookId = bookId;
        this.type = type;
        this.amount = amount;
    }

    /**
     * Retrieves the ID of requested book
     * @return the
     */
    public int getBookId() { return bookId; }

    /**
     * Method whichRetrieves the type for the individual book
     * @return type of the book
     */
    public Type getType() { return type; }

    /**
     *Getter method for the amount of the book in stock in the library
     * @return
     */
    public int getAmount() { return amount; }
}

