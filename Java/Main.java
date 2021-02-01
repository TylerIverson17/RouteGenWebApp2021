import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * GuidenceSystem.java
 * Spencer Williams-Waldemar
 * 410 Software Engineering
 * 1/19/21
 * 
 * Main class to read in from a file and place integer values into two arraylists
 * Then creates instances of other classes and runs their methods for the routing
 * system
 * 
 */
public class Main {

	public static void main(String[] args) {
		

		ArrayList<Integer> houselist = new ArrayList<Integer>();
		ArrayList<Integer> streetlist = new ArrayList<Integer>();
		
		//read txt file of ints
		File file = new File("Addresses.txt");

		try {
			Scanner input = new Scanner(file);

			while (input.hasNextInt()) {
				
				houselist.add(input.nextInt());
				streetlist.add(input.nextInt());
			}

			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//constructors
		GuidenceSystem Gs = new GuidenceSystem();
		FindShortest shortest = new FindShortest();
		
		Gs.createDirections(shortest.shortestpath(houselist, streetlist));
		
		
	}
}

