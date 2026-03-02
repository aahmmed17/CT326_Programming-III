/*
Java class responsible for taking a reservation
Author: Aftab Ahmmed
Exam Number: 23344296
 */
package ie.nuigalway.ct326.testing;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class Reservation implements LibraryReservationWebservice {

    private String memberId;
    public Library library;
    public Book book;
    public LocalDateTime reserveDateTime;
    private String reserveId;

    // method for generating a unique reservation ID
    public String getReserveId() {
        //reserveId = String.valueOf(Math.random()*100000);
        reserveId = String.valueOf(UUID.randomUUID());
        return reserveId;
    }

    // constructor for taking in an edited ID and replacing the old ID
    public String getnewId(String newId){
        memberId = newId;
        return newId;
    }


    public String getMemberId() {
        return memberId;
    }

    public Library getlibDetails(){
        return library;
    }


    // implementing and overriding method from the "external API"
    @Override
    public LocalDateTime getReservationDateTime(Library library, Book book) {
        reserveDateTime = LocalDateTime.now().plusDays(2); // Gives a booking exactly 2 days in advance
        return reserveDateTime;
    }

    // toString method
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'at' HH:mm");
        return  "Reservation ID: " + reserveId +
                "\nMember ID: " + memberId +
                "\nLibrary: " + library.getLibName() + "\nAddress: " +library.getLibAddress() +
                "\nDate & Time: " + reserveDateTime.format(formatter); //On day of week dd MMMM yyyy> at ....
    }


    //Reservation constructors
    public Reservation(String memberId, Library library, Book book, LocalDateTime reserveDateTime)
         throws InvalidReservationDateException {
            if (reserveDateTime.isBefore(LocalDateTime.now())) { // Exception handling for a date or time in the past
                throw new InvalidReservationDateException("Sorry, reservation date cannot be in the past!");
            }
            this.memberId = memberId;
            this.library = library;
            this.book = book;
            this.reserveDateTime = reserveDateTime;
            this.reserveId = getReserveId();

    } //with specfified date


    public Reservation(String memberId, Library library, Book book, LibraryReservationWebservice service){
        this.memberId = memberId;
        this.library = library;
        this.book = book;
        this.reserveDateTime = service.getReservationDateTime(library, book);
        this.reserveId = getReserveId();

    } //with non-specfified date




}
