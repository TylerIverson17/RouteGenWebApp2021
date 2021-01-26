package MyClasses;
/*
My class - Tyler Iverson
 */

import java.util.*;

public class CreateRoute {

    //Data
    public String filePath = "..\\Addresses.txt";
    public LinkedList<String> organizedData;
    public ArrayList<Integer> houselist;
    public ArrayList<Integer> streetlist;


    //take in the linked list from ReadFile and sort it in a meaningful way
    public CreateRoute() throws Exception {
        ReadFile fileReader = new ReadFile(filePath);
        this.houselist = fileReader.houselist;
        this.streetlist = fileReader.streetlist;

        GuidenceSystem Gs = new GuidenceSystem();
        ShortestPath shortest = new ShortestPath();

        Gs.createDirections(shortest.Findshortest(houselist, streetlist));

        System.out.println("HELLO" + Gs.getDirectionStringOutput());

        organizedData = Gs.getDirectionStringOutput();



    }


}
