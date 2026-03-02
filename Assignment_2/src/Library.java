package ct236.assignment2;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Library with a collection of books
 * @author Aftab Ahmmed
 *Student ID: 23344296
 */

public class Library {

        String libName;
        String libLocation;
        Book libFeature = new Book(34567890, "Clean Code", "Robert C. Martin");
        List<Book> coll = new ArrayList<>();


    /**
     * Creates a Library with a name, location, featured book and collection of books
     * @param libraryName string value of the name of the Library
     * @param libraryLocation string value of the location of the Library
     * @param librarianFeaturedBook a book instance representing the featured book of the Library
     * @param collection a list containing book instances representing the collection of the Library
     */
    public Library(String libraryName, String libraryLocation, Book librarianFeaturedBook, List<Book> collection){
        this.libName = libraryName;
        this.libLocation = libraryLocation;
        this.libFeature = librarianFeaturedBook;
        this.coll = collection;
    }


    /**
     * Adds a book instance to the Library's collection
     * @param bookAdd A book instance to be added to a List
     */
    public void addBookToLib(Book bookAdd){
        coll.add(bookAdd);
    }


    @Override
    public String toString(){
        return  String.format("Library Name: %s \n Address: %s \nFeatured Book: %s \nBook List: %s",libName,libLocation,libFeature,coll);
    }


}
