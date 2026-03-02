package ct236.assignment2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *Class representing for testing of the Book class and its methods
 * @author Aftab Ahmmed
 *Student ID: 23344296
 */


public class BookTest {

    List<Book> bookList = new ArrayList<>();


    @BeforeEach
    public void testSetup(){

    Book b1 = new Book(97685991, "Effective Java", "Joshua Bloch");
    Book b2 = new Book(12345678, "The Very Hungry Caterpillar", "Eric Carle");
    Book b3 = new Book(19876543, "Python 101", "John Smith");
    Book b4 = new Book(34567890, "Clean Code", "Robert C. Martin");
    Book b5 = new Book(56781234, "Design Patterns", "Erich Gamma");
    Book b6 = new Book(78906543, "Introduction to Algorithms", "Thomas H. Cormen");

        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
        bookList.add(b6);

    }


    @Test
    public void titleSortedPrint(){

        bookList.sort(Comparator.naturalOrder());
        System.out.println(bookList);
    }

    @Test
    public void isbnSortedPrint() {

       Comparator<Book> isbncomp = new Comparator<Book>() {
           @Override
           public int compare(Book b1, Book b2) {
               return Long.compare(b1.getIsbn(), b2.getIsbn());
           }
       };
        

        Collections.sort(bookList, isbncomp);
        System.out.println(bookList);
    }

    @Test
    public void authorSortedPrint(){

        Comparator<Book> authcomp =
                (Book b1, Book b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor());

        Collections.sort(bookList, authcomp.reversed());
        System.out.println(bookList);
    }

    @Test
    public void binarySearch(){

        Book key = new Book(78906543, "Introduction to Algorithms", "Thomas H. Cormen");
        bookList.sort(Comparator.naturalOrder());

        int bookIndex = Collections.binarySearch(bookList,key);
        System.out.println(("Book found at index:"+bookIndex));

    }

}
