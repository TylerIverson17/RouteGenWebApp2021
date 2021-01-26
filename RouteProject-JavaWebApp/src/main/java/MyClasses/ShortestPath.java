package MyClasses;
/*
Spencer's file--------------------
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {
	
	
	int startinghouse = -1;
	int startingstreet = -1;
	int endhouse = -1;
	int endstreet = -1;
	int distance = 0;
	int distance2 =0;
	int tempdistance = 0;
	int tempdistance2 = 0;
	int housediff;
	int streetdiff;
	int FloorStartHouse;
	int FloorEndHouse;
	
	
	public ShortestPath() {
		
	}
	/*
	 * ShortestPath.java
	 * Spencer Williams-Waldemar
	 * 410 Software Engineering
	 * 
	 * Finds the next closest address from the two arraylists given.
	 * 
	 * The arraylists describe the addresses given
	 * house -> house numbers 
	 * street -> street numbers
	 * house at index i corresponds to street at index i
	 * 
	 * finds the distance between the addresses then places the closest address in distance into a
	 * a queue that is returned as a result.
	 * 
	 * classifies the city as unified blocks of 10 houses of both sides of a street (20 houses in a block) 
	 * with an unlimited amount of streets and blocks.
	 * avenues are after every 10th house connecting streets 
	 * house (1-20) 10 on the left 10 on the right per city block
	 * Odds on the left 
	 * Evens on the right
	 */
	
	
	public Queue<Integer> Findshortest(ArrayList<Integer> house, ArrayList<Integer> street) {
		
		//variable declaration
		int shortestaddress = 10000;
		int shortestaddressdistance = 10000;
		int count = 0;
		Queue<Integer> routedaddresses = new LinkedList<>();
		
		
//MARK -> // While house is not empty 
		while (!house.isEmpty()) {
			
			/*
			 * while it is the first time through
			 * set the starting house 
			 * adjust the house number to fit accordingly to 
			 * the city. 
			 */
			if (count == 0) {
				
				startinghouse = house.get(0);
				routedaddresses.add(startinghouse);

				if (startinghouse % 2 != 0) {
					startinghouse = startinghouse / 2 + 1;
				} else {
					startinghouse = startinghouse / 2;
				}
			
				startingstreet = street.get(0);
				//adds first house to the queue
				routedaddresses.add(startinghouse);
				routedaddresses.add(startingstreet);
				//removes first house from the arraylists
				house.remove(0);
				street.remove(0);

			}
			count++;

			
//MARK ->	
			/*
			 * inner iteration to check each value in the arrays against 
			 * the house you are currently at and find the next address
			 * with the shortest path
			 */
			for (int i = 0; i < house.size(); i++) {
				
				
				
				//adjust the house number to fit accordingly to 
				// the city.
				endhouse = house.get(i);
				endstreet = street.get(i);
				if (endhouse % 2 != 0) {
					endhouse = endhouse / 2 + 1;

				} else {
					endhouse = endhouse / 2;

				}
				
				
				
				//finds the absolute value of the difference in house and street
				streetdiff = Math.abs(startingstreet - endstreet);
				housediff = Math.abs(startinghouse - endhouse);

				
				
				
				
				//Rounds down the Modulus of the house number to find what 
				//block it is on EXAMPLE --> 1-10 = 0th block || 31-40-3rd block  etc....
				if (startinghouse % 10 == 0) {
					FloorStartHouse = (startinghouse / 10);
				}else {
					FloorStartHouse = startinghouse / 10;
				}
				
				if (endhouse % 10 == 0) {
					FloorEndHouse = (endhouse / 10);
				}else {
					FloorEndHouse = endhouse / 10;
				}
				
				
				
				
//MARK ->		// START OF COMPUTATION	
				// if there is no difference in streets
				if (streetdiff == 0) {
					distance += housediff;

				}
				
				// if there is a difference in streets
				if (streetdiff > 0) {

					/*
					 * 	Four possible outcomes with different streets
					 *  Up around and down,
					 *  Down around and up,
					 *  Up over and up, 
					 *  down over and down.
					 */
					
					
					// if in the same block.
					// SOLVES =
					// Up around and down,
					// Down around and up,
					if (FloorStartHouse == FloorEndHouse) {
						// checks south north distance
						
//up and around down ->
						//doesnt work with multiples of 10
						distance += startinghouse % 10;
						distance2 += endhouse % 10;
						
//down and around up ->
						//doesnt work with multiples of 10
						tempdistance += 10 - startinghouse % 10;
						tempdistance2 += 10 - endhouse % 10;
					
						//checks if we have a house number perfectly
						//divisible by 10 and adjusts values. 
						if(startinghouse % 10 == 0) {
							
							tempdistance -= 10;
							
						}
						if(endhouse % 10 == 0) {
							
							tempdistance2-= 10;
						}
						
						
						//if UP AROUND AND DOWN was shorter than down around and up
						if (distance + distance2 < tempdistance + tempdistance2) {
							distance += distance2;
							System.out.println("distance " + distance);
						}
						//if up around and down was shorter than DOWN AROUND AND UP
						else {
							distance = tempdistance + tempdistance2;
							System.out.println("TEMPdistance " + distance);
						}
						
					}

// down over down ->														     v
					//if the current house is SMALLER than the desired house  v<--->v
					if (FloorStartHouse < FloorEndHouse) {
						distance = 0;
						tempdistance = 0;
						
						if(endhouse % 10 == 0) {
							FloorEndHouse--;
							
						}
						
						tempdistance = (FloorEndHouse) - (FloorStartHouse);
						distance += 10 - (startinghouse % 10);
						distance += tempdistance * 10;
						distance += endhouse % 10;

					
					}

// up over up ->														   ^ <---> ^
					//if the current house is LARGER than the desired house    ^
					if (FloorStartHouse > FloorEndHouse) {
						distance = 0;
						tempdistance = 0;
						
						if(startinghouse % 10 == 0) {
							FloorStartHouse--;
						}
						
						//multiplies the difference in blocks
						tempdistance = (FloorStartHouse) - (FloorEndHouse);
						distance += tempdistance * 10;
						
						distance += startinghouse % 10;
						distance += 10 - (endhouse % 10);
						
					}
					
					//adds two to the distance every time a avenue is crossed 
					distance += streetdiff * 2;
				}

				// adds the 2 houses for every avenue taken
				distance += streetdiff * 2;

				// if distance of this address on this iteration is smaller than the recorded smallest
				// address than save that distance as the new shortest distance 
				if (distance < shortestaddressdistance) {
					
					//save the index of the shortest distance found on this iteration
					shortestaddress = i;
					shortestaddressdistance = distance;
					
				}
				
				//reset all variables for next iteration
				distance = 0;
				distance2 = 0;
				tempdistance = 0;
				tempdistance = 0;
				FloorStartHouse = 0;
				FloorEndHouse = 0;
				

			}
			//print out the shortest address from the current address 
			System.out.println("shortest next address from: "+ startinghouse * 2 + " " + startingstreet 
					+ " is: " + house.get(shortestaddress) + street.get(shortestaddress) + " ");
			
			
			
			//reset shortestdistance
			shortestaddressdistance = 10000;

			//get the value at save shortestaddress index 
			startinghouse = house.get(shortestaddress);
			startingstreet = street.get(shortestaddress);
			
			//add the shortest next address to the queue
			routedaddresses.add(startinghouse);
			
			//adjust the house number to fit accordingly to 
			// the city.
			if (startinghouse % 2 != 0) {
				startinghouse = startinghouse / 2 + 1;

			} else {
				startinghouse = startinghouse / 2;

			}
			
			//add the adjusted house number to the queue
			routedaddresses.add(startinghouse);
			//add the street to the queue
			routedaddresses.add(startingstreet);
			
			//remove the shortest address from both arraylists so it is
			//not compared again in the next iteration
			street.remove(shortestaddress);
			house.remove(shortestaddress);

		}
		
		// when the arraylists are empty print out the queue 
		System.out.print(routedaddresses);
		System.out.print("\n\n\n + DIRECTIONS TO HOUSES");
		return routedaddresses;
	}
}
