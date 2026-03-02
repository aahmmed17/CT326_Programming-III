/**
 * Java class to represent an individual book with specific ID and stock count
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */

public class Book {
    private final int id;
    private int stock;

    /**
     *
      * @param id integer for unique book identification
     * @param initialStock integer for the stock of the book before the library is opened
     */
    public Book(int id, int initialStock) {
        this.id = id;
        this.stock = initialStock;
    }

    public int getId() {
        return id;
    }

    public synchronized void addStock(int amount) {
        stock += amount;
    }

    public synchronized boolean takeStock(int amount) {
        if (stock >= amount) {
            stock -= amount;
            return true;
        }
        return false;
    }

    public synchronized int getStock() {
        return stock;
    }
}
