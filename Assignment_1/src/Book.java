/*
Java class used representing a Book and it's details (title and author)
Author: Aftab Ahmmed
Exam Number: 23344296
 */
package ie.nuigalway.ct326.testing;

public class Book {

    String title;
    String author;

    //passing arguments into the Book constructor
    public Book(String author, String title){
        this.author = author;
        this.title = title;

    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
