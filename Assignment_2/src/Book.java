package ct236.assignment2;
import java.util.Objects;

/**
 * Class representing a book for a list of books at a library
 * @author Aftab Ahmmed
 *Student ID: 23344296
 */

public class Book implements Comparable<Book> {

    long isbn;
    String title;
    String author;

    /**
     * Creates a book with an isbn, title and author
     * @param isbn a set number associated with each book
     * @param title a string for the title for each book
     * @param author a string for the author of each book
     */
    public Book(long isbn, String title, String author){
        this.isbn = isbn;
        this.title = title;
        this.author = author;

    }

    /**
     *
     * @param otherbook the object to be compared.
     * @return the integer result representing the comparison between two books
     */
    @Override
    public int compareTo(Book otherbook) {
        return this.title.compareToIgnoreCase(otherbook.getTitle());
    }

    /**
     * Get the title of with the book
     * @return the string title of the book
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Get the ISBN associated with the book
     * @return the long ISBN of the book
     */
    public long getIsbn()
    {
        return isbn;
    }

    /**
     * Get the Author of the book
     * @return the string of the book author's name
     */
    public String getAuthor()
    {
        return author;
    }


    @Override
    public String toString(){
        return String.format("%s by %s (ISBN: %d)\n", title, author, isbn);

    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + (int) (isbn^(isbn>>>32));
        hash = 31 * hash + (title!= null ? title.hashCode():0);
        hash = 31 * hash + (author!= null ? author.hashCode():0);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return this.isbn == book.isbn;
    }

}
