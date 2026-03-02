/*
Java class used representing a Library and it's details (name and address)
Author: Aftab Ahmmed
Exam Number: 23344296
 */
package ie.nuigalway.ct326.testing;

public class Library {

    String libName;
    String libAddress;

    public Library(String libName, String libAddress){
        this.libAddress = libAddress;
        this.libName = libName;
    }

    public String getLibAddress() {
        return libAddress;
    }

    public String getLibName() {
        return libName;
    }
}
