package MyClasses;
/*
Merging of drivers - Both
 */
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {

    public String filePath;
    public int count;


    public static void main(String[] args) throws Exception {
        // Start merge happens here------------------------------------

        CreateRoute newRoute = new CreateRoute();


    }
}

// Unused as of merge----------------------------------------
//simpleton class to house the data as objects
class Address{
    int houseNumber;
    String streetName;
    String streetType;

    public Address(int houseNumber,
                   String streetName,
                   String streetType){
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.streetType = streetType;
    }

    public String toStringAddress(){
        String houseNum = String.valueOf(this.houseNumber);
        return houseNum + " " + this.streetName + " " + this.streetType;
    }

}

class Directions{
    String startingSteet;
    String directions;
    String houseLocation;

    public Directions(String startingSteet,
                      String directions,
                      String houseLocation){
        this.startingSteet = startingSteet;
        this.directions = directions;
        this.houseLocation = houseLocation;
    }


    public String toStringDirections(){
        return startingSteet + " " + directions + " " + houseLocation;
    }

}
