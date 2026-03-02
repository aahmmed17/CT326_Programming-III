/*
Main Java class for compiling and executing main methods
Author: Aftab Ahmmed
Exam Number: 23344296
 */
package ie.nuigalway.ct326.testing;
import java.time.LocalDateTime;

public class LibMain {




    public static void main(String args[]) {

        //Making a reservation with no specified date
        Library lib1 = new Library("Westside Library", "Seamus Quirke Road");
        Book book1 = new Book("Eric Carle", "The Very Hungry Caterpillar");

        Library lib2 = new Library("Galway City Library", "St. Augustine Street Galway");


        //Api taking responsibility for assigning a reservation time
        LibraryReservationWebservice service = new LibraryReservationWebservice() {
            @Override
            public LocalDateTime getReservationDateTime(Library library, Book book) {
                return LocalDateTime.now().plusDays(2); // booking for 2 days in advance
            }
        };

        Reservation res1 = new Reservation("WS170406", lib1, book1, service);
        System.out.println(res1.toString());
        System.out.println("\n");

        LocalDateTime resDate = LocalDateTime.of(2026, 1, 15, 14, 30);

        //Making a reservation with a specified date
        Reservation res2 = new Reservation("GC100219", lib2, book1, resDate);
        System.out.println(res2.toString());

     }


    }

