import java.util.*;
import java.util.concurrent.*;

/**
 * Manages a library system with books and processes book requests concurrently.
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class Library {
    private final Map<Integer, Book> books;
    private final LinkedBlockingQueue<BookRequest> requestQueue;
    private final ExecutorService executor;
    private final List<LibraryWorker> workers;
    private volatile boolean isOpen;

    /**
     * Constructs a new Library with empty book collection and request queue.
     */
    public Library() {
        this.books = new ConcurrentHashMap<>();
        this.requestQueue = new LinkedBlockingQueue<>();
        this.executor = Executors.newFixedThreadPool(2);
        this.workers = new ArrayList<>();
        this.isOpen = false;
    }

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     */
    public void addBook(Book book) {books.put(book.getId(), book);}

    /**
     * Retrieves a book by its ID.
     *
     * @param id the book ID
     * @return the Book object, or null if not found
     */
    public Book getBook(int id) {return books.get(id);}

    /**
     * Submits a request to the processing queue.
     *
     * @param request the book request to submit
     */
    public void submitRequest(BookRequest request) {
        if (isOpen || request == BookRequest.POISON_PILL) {
            requestQueue.offer(request);
        }
    }

    /**
     * Prints details of all books in the library.
     */
    public void printBookDetails() {
        System.out.println("\nFinal Book Details:");
        for (Book book : books.values()) {
            System.out.println("Book " + book.getId() + " has stock: " + book.getStock());
        }
    }

    /**
     * Returns a collection of all book IDs in the library.
     *
     * @return collection of book IDs
     */
    public Collection<Integer> getBookIds() {return books.keySet();}

    /**
     * Opens the library and starts worker threads.
     */
    public void openLibrary() {
        isOpen = true;
        // Create and start two worker threads
        for (int i = 1; i <= 2; i++) {
            LibraryWorker worker = new LibraryWorker("Worker-" + i);
            workers.add(worker);
            executor.execute(worker);
        }
        System.out.println("Library opened with 2 workers.");
    }

    /**
     * Closes the library and shuts down worker threads.
     */
    public void closeLibrary() {
        isOpen = false;

        // Add poison pills for each worker
        for (int i = 0; i < workers.size(); i++) {
            submitRequest(BookRequest.POISON_PILL);
        }

        // Shutdown executor service
        executor.shutdown();
        try {
            // Wait for threads to finish with timeout
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Library closed.");
    }

    /**
     * Worker class that processes book requests from the queue.
     */
    private class LibraryWorker implements Runnable {
        private final String name;
        private int checkoutsProcessed;
        private int returnsProcessed;

        /**
         * Constructs a LibraryWorker with the specified name.
         *
         * @param name the worker name
         */
        public LibraryWorker(String name) {
            this.name = name;
            this.checkoutsProcessed = 0;
            this.returnsProcessed = 0;
        }

        @Override
        public void run() {
            System.out.println(name + " started.");

            while (isOpen || !requestQueue.isEmpty()) {
                try {
                    // Wait for request with timeout
                    BookRequest request = requestQueue.poll(3, TimeUnit.SECONDS);

                    if (request == null) {
                        // Timeout occurred - no requests received
                        System.out.println(name + " waited too long. Stopping.");
                        break;
                    }

                    if (request == BookRequest.POISON_PILL) {
                        System.out.println(name + " received poison pill.");
                        break;
                    }

                    // Process the request
                    processRequest(request);

                    // Sleep for random time between 0-500ms
                    Thread.sleep((int)(Math.random() * 500));

                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted. Stopping.");
                    break;
                }
            }

            // Print summary
            System.out.println(name + " finished: " + checkoutsProcessed +
                    " checkouts, " + returnsProcessed + " returns.");
        }

        /**
         * Processes a single book request.
         *
         * @param request the request to process
         */
        private void processRequest(BookRequest request) {
            Book book = getBook(request.getBookId());
            if (book == null) {
                System.out.println(name + " could not process request - Book " +
                        request.getBookId() + " not found.");
                return;
            }

            if (request.getType() == BookRequest.Type.CHECKOUT) {
                System.out.print(name + " processing CHECKOUT of " + request.getAmount() +
                        " from Book " + request.getBookId());
                if (book.takeStock(request.getAmount())) {
                    System.out.println(" - Success");
                    checkoutsProcessed++;
                } else {
                    System.out.println(" - FAILED (insufficient stock)");
                }
            } else { // RETURN
                System.out.println(name + " processing RETURN of " + request.getAmount() +
                        " to Book " + request.getBookId());
                book.addStock(request.getAmount());
                returnsProcessed++;
            }
        }
    }
}