import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {



    public static void main(String[] args) throws IOException {

        Review r3 = new Review("Carla Brown", "Average story, decent characters but predictable ending.", 3, LocalDate.of(2025, 3, 5));
        Review r5 = new Review("Ella Martinez", "Terrible editing and confusing storyline.", 1, LocalDate.of(2025, 5, 12));
        Review r6 = new Review("Frank Wilson", "A solid sequel that expands the universe nicely.", 4, LocalDate.of(2025, 5, 30));

        ArrayList<Review> revBookTwo = new ArrayList<Review>();
        revBookTwo.add(r5);
        revBookTwo.add(r6);
        revBookTwo.add(r3);


        Book b2 = new Book("", "Harry Potter and the Goblet of Fire", new Author("J.K. Rowling", "British"),
                LocalDate.of(2000, 7, 8), revBookTwo);


        FileOutputStream fileout = new FileOutputStream("books.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileout);
        out.writeObject(b2);
        out.close();
        fileout.close();

        System.out.println("Object info saved! :)");

    }
}