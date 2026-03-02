package ct236.assignment2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *Class representing for testing of the Library class and its methods
 * @author Aftab Ahmmed
 *Student ID: 23344296
 */


public class LibraryTest {



    @Test
    public void mapCreate(){

        Book b1 = new Book(97685991,"Effective Java", "Joshua Bloch");
        Book b2 = new Book(12345678, "The Very Hungry Caterpillar", "Eric Carle");
        Book b3 = new Book(19876543, "Python 101", "John Smith");
        Book b4 = new Book(34567890, "Clean Code", "Robert C. Martin");
        Book b5 = new Book(56781234, "Design Patterns", "Erich Gamma");
        Book b6 = new Book(78906543, "Introduction to Algorithms", "Thomas H. Cormen");

        List<Book> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);

        Library l1 = new Library("Westside Library", "Seamus Quirke Rd.", b5, bookList);


        List<Library> libList = new ArrayList<>();
        libList.add(l1);



        Map<Book,List<Library>> libbook
               = new HashMap<Book,List<Library>>();

        libbook.put(b1,libList);

        System.out.println(libbook);

    }



}
