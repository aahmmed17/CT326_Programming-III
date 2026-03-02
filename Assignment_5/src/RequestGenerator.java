import java.util.*;

/**
 * Generates random book requests and submits them to the library.
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class RequestGenerator implements Runnable {
    private final Library library;
    private volatile boolean running;

    /**
     * Constructs a RequestGenerator with the specified library.
     *
     * @param library the library to submit requests to
     */
    public RequestGenerator(Library library) {
        this.library = library;
        this.running = true;
    }

    @Override
    public void run() {
        System.out.println("Request generator started.");
        Random random = new Random();
        Collection<Integer> bookIds = library.getBookIds();

        if (bookIds.isEmpty()) {
            System.out.println("No books available for generating requests.");
            return;
        }

        Integer[] bookIdArray = bookIds.toArray(new Integer[0]);

        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                // Pick a random book ID
                int bookId = bookIdArray[random.nextInt(bookIdArray.length)];

                // Randomly generate CHECKOUT or RETURN
                BookRequest.Type type = random.nextBoolean() ?
                        BookRequest.Type.CHECKOUT : BookRequest.Type.RETURN;

                // Random amount between 1-3
                int amount = random.nextInt(3) + 1;

                // Create and submit request
                BookRequest request = new BookRequest(bookId, type, amount);
                library.submitRequest(request);

                // Sleep for random time between 0-500ms
                Thread.sleep(random.nextInt(500));

            } catch (InterruptedException e) {
                System.out.println("Request generator interrupted.");
                break;
            }
        }

        // Insert poison pill before terminating
        library.submitRequest(BookRequest.POISON_PILL);
        System.out.println("Request generator terminated.");
    }

    /**
     * Stops the request generator.
     */
    public void stop() {
        running = false;
    }
}