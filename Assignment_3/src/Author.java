import java.io.Serializable;

/**
 * Represents an author of a book.
 * Implements Serializable to support object serialization.
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class Author implements Serializable {

    private String authName;
    private String authNation;

        /**
         * Constructs a new Author with the specified parameters
         *
         * @param authorName the author's name
         * @param authorNation the author's nationality
         */
    public Author(String authorName, String authorNation) {
        this.authName = authorName;
        this.authNation = authorNation;
    }

    /**
     * Returns a string representation of the Author
     *
     * @return string representation of the Author
     */
    @Override
    public String toString() {
        return String.format("Author: %s, Nationality: %s", authName, authNation);
    }

    /**
     * Gets the author's name
     *
     * @return the authors name
     */
    public String getName() {
        return authName;
    }

    /**
     * Gets the author's nationality.
     *
     * @return the nationality
     */
    public String getNationality() {
        return authNation;
    }
}
