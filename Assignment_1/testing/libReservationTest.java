package ie.nuigalway.ct326.testing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/*
Java class used for Test Driven Development purposes
Author: Aftab Ahmmed
Exam Number: 23344296
 */

public class libReservationTest {

    @Test //Test of creating a reservation without a specified date
    public void testreserveDatelessCreate(){

        var lib = new Library("Westside Library", "Seamus Quirke Road");
        var book = new Book("Eric Carle", "The Very Hungry Caterpillar");
        var resDate = LocalDateTime.now().plusDays(2);
        var res = new Reservation("WS170406",lib,book,resDate);

        assertEquals("WS170406", res.getMemberId());
        assertEquals(lib, res.getlibDetails());
        assertNotNull(res.getReserveId());
        assertNotNull(resDate);
    }




    @Test //Test of creating a reservation with an invalid date i.e - Date in the past
    public void testreservePastDateCreate(){

        var lib = new Library("Westside Library", "Seamus Quirke Road");
        var book = new Book("Eric Carle", "The Very Hungry Caterpillar");
        var resDate = LocalDateTime.of(2024, 1, 15, 14, 30);
        Assertions.assertThrows(InvalidReservationDateException.class, () -> {
            new Reservation("WS170406", lib, book, resDate);
        });

    }

    @Test //Test of editing Member ID
    public void testMemIdEdit(){

        var lib = new Library("Westside Library", "Seamus Quirke Road");
        var book = new Book("Eric Carle", "The Very Hungry Caterpillar");
        var resDate = LocalDateTime.of(2026, 1, 15, 14, 30);
        var res = new Reservation("WS170406",lib,book,resDate);

        res.getnewId("WS222222");
        assertEquals("WS222222", res.getMemberId());

    }

    @Test//Test of proper printing format of the toString() method
    public void testtoStringFormat(){
        var lib = new Library("Westside Library", "Seamus Quirke Road");
        var book = new Book("Eric Carle", "The Very Hungry Caterpillar");
        var resDate = LocalDateTime.of(2026, 1, 15, 14, 30);
        var res = new Reservation("WS170406",lib,book,resDate);

        String output  = res.toString();

        assertTrue(output.contains("Reservation ID:"));
        assertTrue(output.contains("Member ID:"));
        assertTrue(output.contains("Library:"));
        assertTrue(output.contains("Date & Time:"));




    }

}
