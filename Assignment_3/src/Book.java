
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Book in a library system with serialization capabilities.
 * Implements custom serialization to handle Review objects separately in a .csv file
 *
 * @author Your Name
 * @studentID Your Student ID
 */
public class Book implements Serializable {

    private String isbn;
    private String title;
    Author auth;
    private LocalDate pubDate;
    private transient List<Review> revs;

    /**
     * Constructs a new Book with the specified parameters.
     *
     * @param isbn the book's ISBN
     * @param title the book's title
     * @param author the book's author
     * @param publicationDate the book's publication date
     * @param reviews list of reviews for the book
     * @throws InvalidFormattingException if publication date is in the future
     */
    public Book(String isbn, String title, Author author,
                LocalDate publicationDate, List<Review> reviews) throws InvalidFormattingException {

        if (publicationDate.isAfter(LocalDate.now())) {
            throw new InvalidFormattingException("Invalid Publication Date!");
        }

        this.isbn = isbn;
        this.title = title;
        this.auth = author;
        this.pubDate = publicationDate;
        this.revs = (reviews != null) ? new ArrayList<>(reviews) : new ArrayList<>();
    }

    /**
     * Custom serialization method that writes Book attributes to ObjectOutputStream
     * and writes Review objects to a CSV file.
     *
     * @param out the ObjectOutputStream to write to
     * @throws IOException if an I/O error occurs
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        if (revs != null && !revs.isEmpty()) {
            // CHANGE: Use append mode (true) instead of overwrite (false)
            try (PrintWriter pw = new PrintWriter(new FileWriter("achievements.csv", true))) {
                for (Review r : revs) {
                    pw.printf("%s,%s,%s,%d,%s%n",
                            isbn,
                            r.revName.replace(",", ";"),
                            r.comm.replace(",", ";"),
                            r.rate,
                            r.getRevDate());
                }
            }
        }
    }

    /**
     * Custom deserialization method that reads Book attributes from ObjectInputStream
     * and reads Review objects from CSV file.
     *
     * @param in the ObjectInputStream to read from
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if class cannot be found during deserialization
     */
    @Serial
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        revs = new ArrayList<>();

        File reviewFile = new File("achievements.csv");
        if (!reviewFile.exists()) return;

        try (Scanner sc = new Scanner(reviewFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                // Use comma as delimiter and handle quoted fields
                String[] parts = parseCSVLine(line);

                if (parts.length >= 5 && parts[0].equals(isbn)) {
                    try {
                        String name = parts[1].replace(";", ",");
                        String comment = parts[2].replace(";", ",");
                        int rating = Integer.parseInt(parts[3].trim());
                        LocalDate date = LocalDate.parse(parts[4].trim());
                        revs.add(new Review(name, comment, rating, date));
                    } catch (Exception e) {
                        System.err.println("Error parsing review: " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Parses a CSV line, handling basic field separation.
     *
     * @param line the CSV line to parse
     * @return array of field values
     */
    private String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString());

        return fields.toArray(new String[0]);
    }

    /**
     * Returns a string representation of the Book.
     *
     * @return string representation of the Book
     */
    @Override
    public String toString() {
        return String.format("ISBN: %s%nBook: %s%n%s%nPublished: %s%nReviews: %s%n",
                isbn, title, auth.toString(), pubDate,
                (revs != null) ? revs.toString() : "No reviews");
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return the ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Gets the list of reviews for the book.
     *
     * @return the list of reviews
     */
    public List<Review> getReviews() {
        return (revs != null) ? new ArrayList<>(revs) : new ArrayList<>();
    }

    /**
     * Gets the book title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the publication date.
     *
     * @return the publication date
     */
    public LocalDate getPublicationDate() {
        return pubDate;
    }
}