package MyClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * GuidenceSystem.java
 * Spencer Williams-Waldemar
 * 410 Software Engineering
 * 1/19/21
 * 
 * Routing system that guides the user with directional dialogue
 * 
 * createdirections()--
 * takes the queue from the FindShortest method in ShortestPath
 * finds the distance and direction to travel to get to the next 
 * address
 * prints out dialogue for directions to your next closest location
 * 
 * routedeclaration()--
 * houseOddOrEven()--
 * northnorth()--
 * southsouth()--
 * northsouth()--
 * southnorth()--
 * all used for dialogue redundency and readibility
 * 
 * street #1 		street #2		street#3															     street #1 		   street #2		street#3
 *   ---^---<------->--^---------->> continued														         ---V---<------->-----V---------->> continued
 * 	| 1 | 2 |       | 1 | 2 |                           												| OOD | 1  | EVEN| |ODD|  1	 | 2 |             
 * 	| 3 | 4 |		| 3 | 4 |			|											 					| ODD | 2  | EVEN| | O |  2  | 4 |			|
 *  | 5 | 6 | 		| 5 | 6 |			|															   	| ODD | 3  | EVEN| | O |  3	 | 6 |			|
 *  | 7 | 8 |		| 7 | 8 |							 												| ODD | 4  | EVEN| | O |  4	 | 8 |
 *  | 9 | 10|		| 9 | 10|																			| ODD | 5  | EVEN| | O |  5	 |10 |
 *  |11 | 12|		|11 | 12|			|																| OOD | 6  | EVEN| | O |  6	 |12 |			|
 * 	|13 | 14|		|13 | 14|			|																| ODD | 7  | EVEN| | O |  7	 |12 |			|
 *  |15 | 16| 		|15 | 16|																		    | ODD | 8  | EVEN| | O |  8	 |16 |
 *  |17 | 18|		|17 | 18|																			| ODD | 9  | EVEN| | O |  9	 |18 |
 *  |19 | 20|		|19 | 20|																			| ODD | 10 | EVEN| | O | 10	 |20 |	
 *   -------<------->-------------->>          FOR PROJECTS SAKE THINK OF THE CITY MORE LIKE THIS	 		 ----^-------<->------^---------->>     
 *  street #1		street #2					SO THAT THE NUMBERING IN THE CODE IS OUT OF 				street #1		  street #2
 *  |21 | 22|       |21 | 22|    				10 NOT OUT OF 20. MAKES TAKING MODULUS A LOT		   		 |21 | 22|      |21 | 22|    
 * 	|23 | 24|		|23 | 24|								       CLEANER.							  		 |23 | 24|		|23 | 24|
 *  |25 | 26| 		|25 | 26|																		    	 |25 | 26| 		|25 | 26|
 *  |27 | 28|		|27 | 28|			|															         |27 | 28|		|27 | 28|			|
 *  |29 | 30|		|29 | 30|			|				THE NUMBER SYSTEM FOR THE HOUSES				     |29 | 30|		|29 | 30|			|
 *  |31 | 32|		|31 | 32|					     ARE ACTUALLY FOR THE PART OF THE STREET			     |31 | 32|		|31 | 32|
 * 	|33 | 34|		|33 | 34|						     YOU ARE ON. NOT THE HOUSE.						     |33 | 34|		|33 | 34|
 *  |35 | 36| 		|35 | 36|			|														             |35 | 36| 		|35 | 36|			|
 *  |37 | 38|		|37 | 38|																			     |37 | 38|		|37 | 38|
 *  |39 | 40|		|39 | 40|																			     |39 | 40|		|39 | 40|
 * 	 -------<------->-------------->>																		 ----^--<------->---^---------->>
 * 
 * 
 * EXAMPLE :
 * 
 * by saying we are at:
 * 
 * 		house #35 on 1st street 
 * 
 * going to:
 * 
 *  	house #5 on 2nd street 
 * 
 * we can reasonably say we can travel:
 * 		
 * 		***NORTH 8 UNITS***  
 * 
 * (((( NOT 15 UNITS )))) THE DISTANCE IS HALVED BECAUSE THERE IS A HOUSE ON EACH SIDE OF THE STREET
 * 		YOU ARE REALLY ONLY TRAVELLING 8 SPACES/UNITS
 *      
 *      ***EAST 2 UNITS***  
 *      
 *      E.I. 2 HOUSES BORDER AN AVENUE SO I SAID YOU TRAVELED 2 UNITS WHEN TRAVELING FROM STREET 1 TO STREET 2
 *  
 *  	***THEN FINALLY ANOTHER 8 UNITS NORTH***
 * 
 * 
 */
public class GuidenceSystem {

	//variable declaration
	int realhousenumber = 0;
	int nextrealhousenumber = 0;
	int currenthouse = -1;
	int currentstreet = -1;
	int nexthouse = -1;
	int nextstreet = -1;
	
	//variables to hold different distances for each part of the route from house to house
	int distance = 0;
	int tempdistance = 0;
	int distance2 = 0;
	int tempdistance2 = 0;
	
	//variables to find where you are and where your going logically
	int housediff = 0;
	int streetdiff = 0;
	int floorcurrenthouse = 0;
	int floornexthouse = 0;
	boolean travelWest = true;

	public LinkedList<String> directionStringOutput = new LinkedList<>();
	private String tempDirections;
	public ShortestPath shortest = new ShortestPath();

	public GuidenceSystem() {

	}

//Mark ->
	public void createDirections(Queue<Integer> routes) {
		
		
		//takes first three values from the queue
		realhousenumber = routes.poll();
		currenthouse = routes.poll();
		currentstreet = routes.poll();

		// run this loop till the queue is empty and there are no more addresses to be
		// routed
		while (!routes.isEmpty()) {

			
			
			// takes next three values from the queue
			nextrealhousenumber = routes.poll();
			nexthouse = routes.poll();
			nextstreet = routes.poll();

			
			
			//dialogue
			routeDeclaration();

			
			
			
			//finds the absolute value of the difference in house and street
			//This is used to say the difference without negative numbers
			streetdiff = Math.abs(currentstreet - nextstreet);
			housediff = Math.abs(currenthouse - nexthouse);

			
			
			
			
			
//MARK ->	
			if (streetdiff == 0) { // THE TWO HOUSES ARE ON THE SAME STREET

				
				// HEAD SOUTH (STARTING HOUSE NUMBER IS A SMALLER NUMBER (MORE NORTH) THAN THE
				// END HOUSE NUMBER)
				if (currenthouse - nexthouse < 0) {
					System.out.println("travel SOUTH past " + housediff + " houses");

					houseOddOrEven(false);
				}
				// head north
				// HEAD NORTH (STARTING HOUSE NUMBER IS A SMALLER NUMBER (MORE NORTH) THAN THE
				// END HOUSE NUMBER)
				
				if (currenthouse - nexthouse > 0) {
					System.out.println("travel NORTH past " + housediff + " houses");

					houseOddOrEven(true);
				}

				//-------------------------------------------------------------------------------------------------
				directionStringOutput.add(tempDirections);

			} // End of case where houses are on the same street

			
			
			
			
			
//MARK ->	// case where houses are NOT on the same street //
			else {// if street - nextstreet is not equal to 0 //
				
				
				
				
				
				
				if (currentstreet - nextstreet < 0) {// HEAD EAST TO TRAVEL TO LOCATION WITH STREET THAT IS LARGER THAN THE STARTING
					travelWest = false;
				} 
				else {// HEAD WEST TO TRAVEL TO LOCATION WITH STREET THAT IS SMALLER THAN THE STARTING
					travelWest = true;
				}

				
				
				
				
				
//MARK ->				
				//FloorStartHouse was created to find the block that the house is on 
				// 35 / 10 = 3.5 in java it is rounded down to 3
				// so the 3rd block
				//if the remainder when divided by 10 is 0
				if (currenthouse % 10 == 0) {
				//-1 because house #10 is technically on block 0 
				floorcurrenthouse = (currenthouse / 10) - 1;
				}	
				else {
				floorcurrenthouse = currenthouse / 10;
				}
				
				
				if (nexthouse % 10 == 0) {
				floornexthouse = (nexthouse / 10) - 1;
				}
				
				else {
				floornexthouse = nexthouse / 10;
				}

			// 35 = 3.5  = 3      
			// 20 = 1  
			// 50 = 4
			// 62 = 6.2 = 6
			
			
			
			
			
			
			
//MARK ->		//(THE TWO HOUSES ARE IN THE SAME BLOCK ON DIFFERENT STREETS)//
			if (floorcurrenthouse == floornexthouse) {

				// checks south north distance
				if(currenthouse % 10 == 0) {
					distance -= 10;
				}
				//checks south north distance
				distance += 10 - (currenthouse % 10);
				distance2 += 10 - (nexthouse % 10);
				// checks north south distance
				if(nexthouse % 10 == 0) {
					tempdistance += 10;
				}
				tempdistance += (currenthouse % 10);
				tempdistance2 += (nexthouse % 10);

			// South -> east/west -> North
			if (distance + distance2 < tempdistance + tempdistance2) {
				southNorth();
				distance += distance2;
				distance = 0;
				tempdistance = 0;

			}
			// North -> east/west -> South
			if (distance + distance2 >= tempdistance + tempdistance2) {
				northSouth();
				distance = tempdistance;
				distance += tempdistance2;
				distance = 0;
				tempdistance = 0;
				
				}
			} // END OF THE TWO HOUSES ARE IN THE SAME BLOCK ON DIFFERENT STREETS //

			
			
			
			
			
			
//MARK ->	// (THE TWO HOUSES ARE NOT IN THE SAME BLOCK AND ON DIFFERENT STREETS)//	
			// South -> east/west -> South
			if (floorcurrenthouse < floornexthouse) {
				
				distance = 0;
				distance2 = 0;
				tempdistance =0;
				
				//ex: 
				//10 - (2) % 10 = moved 8 units down 
				//10 - (10) % 10 = moved 0 units down
				//10 is on the street corner so it will not need to move any units
				
				//if next house = 0 when % 10 occurs 10,20,30
				//since it is heading north just add zero to distance
				//because 10, 20 ,30 are at the end of a block
				if(currenthouse % 10 != 0) {
				distance += 10 - (currenthouse % 10);
				}
				
				tempdistance = floornexthouse - floorcurrenthouse;
				//gets the difference in blocks and multiples by 10
				if(tempdistance > 1 || currenthouse % 10 == 0 && nexthouse %10 == 0) {
				
					distance2 += tempdistance * 10;
					
					if(nexthouse - currenthouse >= 30) {
						distance2 -= distance;
					}
					
				}
				if(nexthouse - distance2 == distance) {
					distance2 = nexthouse - (currenthouse + distance);
				}
				if(distance2 > nexthouse - currenthouse) {
					distance2 -= distance2 - (nexthouse - currenthouse);
					distance2 -= distance;
				}
				
			
	
				
				//print dialogue
				//
				southSouth();
			}

			
			
			
//MARK ->	//North -> east/west -> North
			if (floorcurrenthouse > floornexthouse) {
				distance = 0;
				distance2 = 0;
				tempdistance =0;
				
				
				distance += currenthouse % 10;
				
				tempdistance = floorcurrenthouse - floornexthouse;
				if(tempdistance > 1 || currenthouse % 10 == 0 && nexthouse %10 == 0) {
					distance2 += tempdistance * 10;
				}
				if(distance2 > currenthouse - nexthouse) {
					distance2 -= distance2 - (currenthouse - nexthouse);
					distance2 -= distance;
				}
				
				if(nexthouse % 10 != 0) {
					distance2 += 10 - (nexthouse % 10);
				}
				
				
				
				//distance endhouse goes north
				

				
				
				
				//print dialoque
				northNorth();

			}
			//----------------------------------------------------------------------------------------------------------
				directionStringOutput.add(tempDirections);
		}//end of else = different streets
			
			//reset all variables
			distance = 0;
			tempdistance = 0;
			distance2 = 0;
			tempdistance2 = 0;
			floorcurrenthouse = 0;
			floornexthouse = 0;
			realhousenumber = nextrealhousenumber;
			currenthouse = nexthouse;
			currentstreet = nextstreet;

		} // end of while loop
	}

//MARK -> DIALOGUE FOR DIRECTIONS -----------------------------------------------------------Where Object is going to be created for export-------------
	public void routeDeclaration() {
		System.out.print("\nRoute starts at: " + realhousenumber + " " + currentstreet);
		tempDirections = "\nRoute starts at: " + realhousenumber + " " + currentstreet;
		if (currentstreet % 10 == 1) {
			System.out.print("st street\n");
			tempDirections += "st street\n";
		}
		if (currentstreet % 10 == 2) {
			System.out.print("nd street\n");
			tempDirections += "nd street\n";
		}
		if (currentstreet % 10 == 3) {
			System.out.print("rd street\n");
			tempDirections += "rd street\n";
		}
		if (currentstreet % 10 > 3) {
			System.out.print("th street\n");
			tempDirections += "th street\n";
		}

		System.out.print("Travel to: " + nextrealhousenumber + " " + nextstreet);
		tempDirections += "Travel to: " + nextrealhousenumber + " " + nextstreet;
		if (nextstreet % 10 == 1) {
			System.out.print("st street\n");
			tempDirections += "st street\n";
		}
		if (nextstreet % 10 == 2) {
			System.out.print("nd street\n");
			tempDirections += "nd street\n";
		}
		if (nextstreet % 10 == 3) {
			System.out.print("rd street\n");
			tempDirections += "rd street\n";
		}
		if (nextstreet % 10 > 3) {
			System.out.print("th street\n");
			tempDirections += "th street\n";
		}
	}
	
	
//MARK ->
	//determines what side house is on
	public void houseOddOrEven(boolean isNorth) {
		
		// if heading NORTH and house is ODD numbered then the house is on your LEFT
		if (housediff % 2 == 1 && isNorth) {
			System.out.println("House " + nextrealhousenumber + " will be on your LEFT");
			tempDirections += "House " + nextrealhousenumber + " will be on your LEFT";
		}

		// if heading NORTH and house is EVEM numbered then the house is on your RIGHT
		if (housediff % 2 == 0 && isNorth){
			System.out.println("House " + nextrealhousenumber + " will be on your RIGHT");
			tempDirections += "House " + nextrealhousenumber + " will be on your RIGHT";
		}
		
		// if heading SOUTH and house is ODD numbered then the house is on your RIGHT
		if (housediff % 2 == 1 && !isNorth) {
			System.out.println("House " + nextrealhousenumber + " will be on your RIGHT");
			tempDirections +=  "House " + nextrealhousenumber + " will be on your RIGHT";
		}

		// if heading SOUTH and house is EVEN numbered then the house is on your LEFT
		if (housediff % 2 == 0 && !isNorth) {
			System.out.println("House " + nextrealhousenumber + " will be on your LEFT");
			tempDirections +=  "House " + nextrealhousenumber + " will be on your LEFT";
		}
	}
	
	
//MARK ->
	/*
	 *  THE DIALOGUES BELOW NEEED 3 THINGS TO RUN
	 *  distance: is the units between the house your at to the next avenue
	 *  
	 *  streetdiff: to print change in streets 
	 *  
	 *  distance2: gives you closest distance from the house your at to the next avenue
	 * 	****if the distance between currenthouse/10 and nexthouse /10 is greater than 2
	 * 		i.e. the houses are 2 blocks away 
	 * 	****then multiply the difference in blocks by 10 
	 *

	 * 
	 */
	public void northNorth() {
		
		if (travelWest) {
			System.out.println("Travel NORTH past " + distance + " house(s)");
			tempDirections += "Travel NORTH past " + distance + " house(s)";
			System.out.println("Then go LEFT " + streetdiff + " street(s)");
			tempDirections += "Then go LEFT " + streetdiff + " street(s)";
			System.out.println("Then travel NORTH past " + distance2 + " house(s)");
			tempDirections += "Then travel NORTH past " + distance2 + " house(s)";
			houseOddOrEven(true);

		}
		// IF TRAVELWEST IS FALSE -- TRAVELING EAST
		else {
			System.out.println("Travel NORTH past " + distance + " house(s)");
			tempDirections += "Travel NORTH past " + distance + " house(s)";
			System.out.println("Then go RIGHT " + streetdiff + " street(s)");
			tempDirections += "Then go RIGHT " + streetdiff + " street(s)";
			System.out.println("Then travel NORTH past " + distance2 + " houses(s)");
			tempDirections += "Then travel NORTH past " + distance2 + " houses(s)";
			houseOddOrEven(true);

		}
	}
//MARK ->
	public void southSouth() {
		
		if (travelWest) {
			System.out.println("Travel SOUTH past " + distance + " house(s)");
			tempDirections += "Travel SOUTH past " + distance + " house(s)";
			System.out.println("Then go LEFT " + streetdiff + " street(s)");
			tempDirections += "Then go LEFT " + streetdiff + " street(s)";
			System.out.println("Then travel SOUTH past " + distance2 + " house(s)");
			tempDirections += "Then travel SOUTH past " + distance2 + " house(s)";
			houseOddOrEven(false);
		}
		// IF TRAVELWEST IS FALSE -- TRAVELING EAST
		else {
			System.out.println("Travel SOUTH past " + distance + " house(s)");
			tempDirections += "Travel SOUTH past " + distance + " house(s)";
			System.out.println("Then go RIGHT " + streetdiff + " street(s)");
			tempDirections += "Then go RIGHT " + streetdiff + " street(s)";
			System.out.println("Then travel SOUTH past " + distance2 + " houses(s)");
			tempDirections += "Then travel SOUTH past " + distance2 + " houses(s)";
			houseOddOrEven(false);

		}
	}
//MARK ->
	public void northSouth() {
		
		if (travelWest) {
			System.out.println("Travel NORTH past " + distance + " house(s)");
			tempDirections += "Travel NORTH past " + distance + " house(s)";
			System.out.println("Then go LEFT " + streetdiff + " street(s)");
			tempDirections += "Then go LEFT " + streetdiff + " street(s)";
			System.out.println("Then travel SOUTH past " + distance2 + " house(s)");
			tempDirections += "Then travel SOUTH past " + distance2 + " house(s)";
			houseOddOrEven(false);
		}
		// IF TRAVELWEST IS FALSE -- TRAVELING EAST
		else {
			System.out.println("Travel NORTH past " + distance + " house(s)");
			tempDirections += "Travel NORTH past " + distance + " house(s)";
			System.out.println("Then go RIGHT " + streetdiff + " street(s)");
			tempDirections += "Then go RIGHT " + streetdiff + " street(s)";
			System.out.println("Then travel SOUTH past " + distance2 + " houses(s)");
			tempDirections += "Then travel SOUTH past " + distance2 + " houses(s)";
			houseOddOrEven(false);

		}

	}
//MARK ->
	public void southNorth() {
		
		if (travelWest) {
			System.out.println("Travel SOUTH past " + distance + " house(s)");
			tempDirections += "Travel SOUTH past " + distance + " house(s)";
			System.out.println("Then go LEFT " + streetdiff + " street(s)");
			tempDirections += "Then go LEFT " + streetdiff + " street(s)";
			System.out.println("Then travel NORTH past " + distance2 + " house(s)");
			tempDirections += "Then travel NORTH past " + distance2 + " house(s)";
			houseOddOrEven(true);
		}
		// IF TRAVELWEST IS FALSE -- TRAVELING EAST
		else {
			System.out.println("Travel SOUTH past " + distance + " house(s)");
			tempDirections += "Travel SOUTH past " + distance + " house(s)";
			System.out.println("Then go RIGHT " + streetdiff + " street(s)");
			tempDirections += "Then go RIGHT " + streetdiff + " street(s)";
			System.out.println("Then travel NORTH past " + distance2 + " houses(s)");
			tempDirections += "Then travel NORTH past " + distance2 + " houses(s)";
			houseOddOrEven(true);

		}

	}

	public LinkedList<String> getDirectionStringOutput(){
		return directionStringOutput;
	}


}
