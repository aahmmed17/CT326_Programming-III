import java.time.LocalDate;

/**
 * Represents a reader's review of a book
 * This class is not serializable as per assignment
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class Review {

    String revName;
    String comm;
    int rate;
    private final LocalDate revDate;

    /**
     * Constructs a new Review with the specified parameters.
     *
     * @param reviewName the name of the reviewer
     * @param comment the review comment
     * @param rating the rating (1-5)
     * @param reviewDate the date of the review
     * @throws InvalidFormattingException if rating is invalid or review date is in the future
     */
    public Review(String reviewName, String comment, int rating, LocalDate reviewDate)
            throws InvalidFormattingException {

        if (reviewDate.isAfter(LocalDate.now())) {
            throw new InvalidFormattingException("Invalid Review Date!");
        }

        if (rating < 1 || rating > 5) {
            throw new InvalidFormattingException("Invalid Rating Value! (Must be between 1-5)");
        }

        this.revName = reviewName;
        this.comm = comment;
        this.rate = rating;
        this.revDate = reviewDate;
    }

    /**
     * Gets the review date.
     *
     * @return the review date
     */
    public LocalDate getRevDate() {
        return revDate;
    }

    /**
     * Gets the reviewer name.
     *
     * @return the reviewer name
     */
    public String getReviewerName() {
        return revName;
    }

    /**
     * Gets the review comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comm;
    }

    /**
     * Gets the rating.
     *
     * @return the rating
     */
    public int getRating() {
        return rate;
    }

    /**
     * Returns a string representation of the Review.
     *
     * @return string representation of the Review
     */
    @Override
    public String toString() {
        return String.format("Reviewer Name: %s Comment: %s Rating: %d/5 Review Date: %s\n",
                revName, comm, rate, revDate);
    }
}
