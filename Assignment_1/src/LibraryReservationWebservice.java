/*
Java Interface taking the responsibilty of an "external API"
Author: Aftab Ahmmed
Exam Number: 23344296
 */
package ie.nuigalway.ct326.testing;

import java.time.LocalDateTime;

public interface LibraryReservationWebservice {
    public LocalDateTime getReservationDateTime(Library library, Book book);

}
