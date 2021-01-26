package MyClasses;
/*
My class - Tyler Iverson
 */

import java.util.*;

public class CreateRoute {

    //Data
    public LinkedList<String> organizedData;
    public ArrayList<Integer> houselist;
    public ArrayList<Integer> streetlist;


    public CreateRoute() throws Exception {
        ReadFile fileReader = new ReadFile();
        this.houselist = fileReader.houselist;
        this.streetlist = fileReader.streetlist;

        GuidenceSystem Gs = new GuidenceSystem();
        ShortestPath shortest = new ShortestPath();

        Gs.createDirections(shortest.Findshortest(houselist, streetlist));

        organizedData = Gs.getDirectionStringOutput();



    }


}
