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

    //CreateRoute Object will instantiate the Java program
    /*
           Look into CSS Animations - option from friend
           Friend says you can make cars animate lol  - move location of elements and make visible like sprites
     */
    public CreateRoute() throws Exception {

        // Opens a file chooser from the base Java API
        ReadFile fileReader = new ReadFile();

        // Taking the house numbers and street numbers and making an instance object CreateRoute Objects will access
        this.houselist = fileReader.houselist;
        this.streetlist = fileReader.streetlist;

        // Making Spencer's GuidenceSystem and ShortestPath Objs
        GuidenceSystem Gs = new GuidenceSystem();
        ShortestPath shortest = new ShortestPath();

        // Creates the directions based on the chosen file's contents
        Gs.createDirections(shortest.Findshortest(houselist, streetlist));

        // Getting the directions in a linkedList<String> and putting it into the CreateRoute() Obj
        organizedData = Gs.getDirectionStringOutput();

    }


}
