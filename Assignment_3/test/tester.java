import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for Book serialization and deserialization functionality.
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class tester {

    private List<Book> bookList;

    @Before
    public void setUp() {
        bookList = new ArrayList<>();

        // Create reviews with valid dates (not in future)
        Review r1 = new Review("Alice Johnson", "Excellent read", 5, LocalDate.of(2024, 1, 15));
        Review r2 = new Review("Bob Smith", "Good book", 4, LocalDate.of(2024, 2, 10));
        Review r3 = new Review("Carla Brown", "Average story", 3, LocalDate.of(2024, 3, 5));
        Review r4 = new Review("Karen Lopez", "Beautiful language but hard to follow at times.", 4, LocalDate.of(2025, 8, 2));
        Review r5 = new Review("Liam Nguyen", "Very informative and well-researched nonfiction.", 5, LocalDate.of(2025, 8, 20));
        Review r6 = new Review("Maria Gonzalez", "Didn’t connect with the characters at all.", 2, LocalDate.of(2025, 9, 5));
        Review r7 = new Review("Noah Patel", "Simple yet powerful message. Loved it.", 5, LocalDate.of(2025, 9, 12));
        Review r8 = new Review("Olivia Clark", "A heartfelt story that stayed with me.", 5, LocalDate.of(2025, 9, 25));


        List<Review> revBookOne = new ArrayList<>();
        revBookOne.add(r1);
        revBookOne.add(r2);

        List<Review> revBookTwo = new ArrayList<>();
        revBookTwo.add(r3);

        List<Review> revBookThree = new ArrayList<>();
        revBookThree.add(r4);
        revBookThree.add(r5);

        //Empty Reviews case
        List<Review> revBookFour = new ArrayList<>();

        List<Review> revBookFive = new ArrayList<>();
        revBookFive.add(r6);
        revBookFive.add(r7);
        revBookFive.add(r8);

        bookList.add(new Book("1234", "1984", new Author("George Orwell", "British"),
                LocalDate.of(1949, 6, 8), revBookOne));
        bookList.add(new Book("4123", "Harry Potter", new Author("J.K. Rowling", "British"),
                LocalDate.of(2000, 7, 8), revBookTwo));
        bookList.add(new Book("7890", "Mockingbird", new Author("Harper Lee", "American"),
                LocalDate.of(1960, 7, 11), revBookThree));
        bookList.add(new Book("5467", "The Hobbit", new Author("J.R.R. Tolkien", "British"),
                LocalDate.of(1937, 9, 21), revBookFour));
        bookList.add(new Book("3849", "The Road", new Author("Cormac McCarthy", "American"),
                LocalDate.of(2006, 9, 26), revBookFive));
    }

    /**
     * Tests the complete serialization and deserialization process with 5 books.
     * Verifies objects are properly serialized and deserialized with all attributes.
     */
    @Test
    public void testSerializationAndDeserialization() throws IOException, ClassNotFoundException {

        new File("books.ser").delete();
        new File("achievements.csv").delete();

        // Serialize
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("books.ser"))) {
            out.writeObject(bookList);
        }

        // Deserialize
        List<Book> loadedBooks;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("books.ser"))) {
            loadedBooks = (List<Book>) in.readObject();
        }


        assertNotNull("Deserialized list should not be null", loadedBooks);
        assertEquals("Should have 5 books", 5, loadedBooks.size());


        for (Book book : loadedBooks) {
            assertNotNull("Each book should not be null", book);
        }


        assertEquals(2, loadedBooks.get(0).getReviews().size());
        assertEquals(1, loadedBooks.get(1).getReviews().size());
        assertEquals(2, loadedBooks.get(2).getReviews().size());
        assertEquals(0, loadedBooks.get(3).getReviews().size());
        assertEquals(3, loadedBooks.get(4).getReviews().size());

        for(Book b: bookList)
        {
            System.out.println(b);
        }



    }

    /**
     * Tests that invalid inputs throw appropriate exceptions.
     */
    @Test
    public void testInvalidInputThrowsExceptions() {
        // Test invalid rating
        assertThrows(InvalidFormattingException.class, () ->
                new Review("Test", "Test", 6, LocalDate.of(2024, 1, 1))
        );

        // Test future publication date
        List<Review> reviews = List.of(new Review("Test", "Test", 3, LocalDate.of(2024, 1, 1)));
        assertThrows(InvalidFormattingException.class, () ->
                new Book("1234", "Test Book", new Author("Test", "Test"),
                        LocalDate.of(2026, 1, 1), reviews)
        );
    }
}