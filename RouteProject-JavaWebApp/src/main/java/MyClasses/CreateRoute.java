package MyClasses;
/*
My class - Tyler Iverson
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CreateRoute {

    //Data
    public String filePath = "..\\Addresses.txt";
    public LinkedList<String> organizedData = new LinkedList<String>();
    public ArrayList<Integer> houselist = new ArrayList<Integer>();
    public ArrayList<Integer> streetlist = new ArrayList<Integer>();
    public String tryMe;
    GuidenceSystem Gs = new GuidenceSystem();
    ShortestPath shortest = new ShortestPath();


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

    public void getOrganizedString(){
        LinkedList<String> data = new LinkedList<>();
        for(String s : Gs.getDirectionStringOutput()){
            System.out.println("HELP!!!!");
            data.add(s);
        }
        organizedData = data;
        //return data;
    }


}
