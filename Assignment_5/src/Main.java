/**
 * Main class to demonstrate the library system with concurrent processing.
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        // Add three books with different starting stocks
        library.addBook(new Book(101, 67));  // Book 101 with 5 copies
        library.addBook(new Book(102, 6));  // Book 102 with 3 copies
        library.addBook(new Book(103, 7));  // Book 103 with 7 copies


        RequestGenerator generator = new RequestGenerator(library);
        Thread generatorThread = new Thread(generator);
        generatorThread.start();

        // Opens library (start worker threads)
        library.openLibrary();


        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        generatorThread.interrupt();
        try {
            generatorThread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        library.closeLibrary();


        library.printBookDetails();
    }
}