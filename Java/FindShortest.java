import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindShortest {

	// variable declaration
	int currenthouse = -1;
	int currentstreet = -1;
	int nexthouse = -1;
	int nextstreet = -1;

	// variables to hold different distances for each part of the route from house
	// to house
	int distance = 0;
	int tempdistance = 0;
	int distance2 = 0;
	int tempdistance2 = 0;

	// variables to find where you are and where your going logically
	int housediff = 0;
	int streetdiff = 0;
	int floorcurrenthouse = 0;
	int floornexthouse = 0;

	// constructor method
	public FindShortest() {

	}
	
	
	
	

	public Queue<Integer> shortestpath(ArrayList<Integer> house, ArrayList<Integer> street) {
		// variable declaration
		int shortestaddress = 10000;
		int shortestaddressdistance = 10000;
		int count = 0;

		Queue<Integer> routedaddresses = new LinkedList<>();

		while (!house.isEmpty()) {
			
			//first time case
			if (count == 0) {

				currenthouse = house.get(0);
				routedaddresses.add(currenthouse);

				if (currenthouse % 2 != 0) {
					currenthouse = currenthouse / 2 + 1;
				} else {
					currenthouse = currenthouse / 2;
				}

				currentstreet = street.get(0);
				// adds first house to the queue
				routedaddresses.add(currenthouse);
				routedaddresses.add(currentstreet);
				// removes first house from the arraylists
				house.remove(0);
				street.remove(0);

			}
			count++;

			for (int i = 0; i < house.size(); i++) {
				nexthouse = house.get(i);
				nextstreet = street.get(i);
				if (nexthouse % 2 != 0) {
					nexthouse = nexthouse / 2 + 1;

				} else {
					nexthouse = nexthouse / 2;

				}

				streetdiff = Math.abs(currentstreet - nextstreet);
				housediff = Math.abs(currenthouse - nexthouse);

//MARK ->	
				if (streetdiff == 0) { // THE TWO HOUSES ARE ON THE SAME STREET
					distance = housediff;
				} // End of case where houses are on the same street

//MARK ->
				else { // housediff > 0

					// FloorStartHouse was created to find the block that the house is on
					// 35 / 10 = 3.5 in java it is rounded down to 3
					// so the 3rd block
					// if the remainder when divided by 10 is 0
					if (currenthouse % 10 == 0) {
						// -1 because house #10 is technically on block 0
						floorcurrenthouse = (currenthouse / 10) - 1;
					} else {
						floorcurrenthouse = currenthouse / 10;
					}

					if (nexthouse % 10 == 0) {
						floornexthouse = (nexthouse / 10) - 1;
					}

					else {
						floornexthouse = nexthouse / 10;
					}

					/*
					 * Four possible outcomes with different streets Up around and down, Down around
					 * and up, Up over and up, down over and down.
					 */
//MARK ->		//(THE TWO HOUSES ARE IN THE SAME BLOCK ON DIFFERENT STREETS)//
					if (floorcurrenthouse == floornexthouse) {
						distance = 0;
						distance2 = 0;
						tempdistance = 0;

						// checks south north distance
						if (currenthouse % 10 == 0) {
							distance -= 10;
						}
						// checks south north distance
						distance += 10 - (currenthouse % 10);
						distance2 += 10 - (nexthouse % 10);
						// checks north south distance
						if (nexthouse % 10 == 0) {
							tempdistance += 10;
						}
						tempdistance += (currenthouse % 10);
						tempdistance2 += (nexthouse % 10);

						// South -> east/west -> North
						if (distance + distance2 < tempdistance + tempdistance2) {

							distance += distance2;

						}
						// North -> east/west -> South
						else if (distance + distance2 >= tempdistance + tempdistance2) {

							distance = tempdistance;
							distance += tempdistance2;

						}
					} // end of if

//MARK ->	// (THE TWO HOUSES ARE NOT IN THE SAME BLOCK AND ON DIFFERENT STREETS)//	
					// South -> east/west -> South
					if (floorcurrenthouse < floornexthouse) {

						distance = 0;
						distance2 = 0;
						tempdistance = 0;

						// ex:
						// 10 - (2) % 10 = moved 8 units down
						// 10 - (10) % 10 = moved 0 units down
						// 10 is on the street corner so it will not need to move any units

						// if next house = 0 when % 10 occurs 10,20,30
						// since it is heading north just add zero to distance
						// because 10, 20 ,30 are at the end of a block
						if (currenthouse % 10 != 0) {
							distance += 10 - (currenthouse % 10);
						}

						tempdistance = floornexthouse - floorcurrenthouse;
						// gets the difference in blocks and multiples by 10
						if (tempdistance > 1 || currenthouse % 10 == 0 && nexthouse % 10 == 0) {

							distance2 += tempdistance * 10;

							if (nexthouse - currenthouse >= 30) {
								distance2 -= distance;
							}

						}
						if (nexthouse - distance2 == distance) {
							distance2 = nexthouse - (currenthouse + distance);
						}
						if (distance2 > nexthouse - currenthouse) {
							distance2 -= distance2 - (nexthouse - currenthouse);
							distance2 -= distance;
						}

						distance += distance2;
					} // end of if

//MARK ->	//North -> east/west -> North
					if (floorcurrenthouse > floornexthouse) {
						distance = 0;
						distance2 = 0;
						tempdistance = 0;

						distance += currenthouse % 10;

						tempdistance = floorcurrenthouse - floornexthouse;
						if (tempdistance > 1 || currenthouse % 10 == 0 && nexthouse % 10 == 0) {
							distance2 += tempdistance * 10;
						}
						if (distance2 > currenthouse - nexthouse) {
							distance2 -= distance2 - (currenthouse - nexthouse);
							distance2 -= distance;
						}

						if (nexthouse % 10 != 0) {
							distance2 += 10 - (nexthouse % 10);
						}

						// distance endhouse goes north
						distance += distance2;

					} // end of if

				} // END OF ELSE

				distance += streetdiff * 2;
				// if distance of this address on this iteration is smaller than the recorded
				// smallest
				// address than save that distance as the new shortest distance
				if (distance < shortestaddressdistance) {
					
					// save the index of the shortest distance found on this iteration
					shortestaddress = i;
					shortestaddressdistance = distance;

				}

				// reset all variables
				distance = 0;
				tempdistance = 0;
				distance2 = 0;
				tempdistance2 = 0;
				floorcurrenthouse = 0;
				floornexthouse = 0;
			
//			currenthouse = nexthouse;
//			currentstreet = nextstreet;

			} // end of for loop

			// reset shortestdistance
			shortestaddressdistance = 10000;

			// get the value at save shortestaddress index
			currenthouse = house.get(shortestaddress);
			currentstreet = street.get(shortestaddress);

			// add the shortest next address to the queue
			routedaddresses.add(currenthouse);

			// adjust the house number to fit accordingly to
			// the city.
			if (currenthouse % 2 != 0) {
				currenthouse = currenthouse / 2 + 1;

			} else {
				currenthouse = currenthouse / 2;

			}

			// add the adjusted house number to the queue
			routedaddresses.add(currenthouse);
			// add the street to the queue
			routedaddresses.add(currentstreet);

			// remove the shortest address from both arraylists so it is
			// not compared again in the next iteration
			street.remove(shortestaddress);
			house.remove(shortestaddress);

		} // end of while loop

		System.out.print(routedaddresses);
		System.out.print("\n\n\n + DIRECTIONS TO HOUSES");
		
		return routedaddresses;
	}// end of method

}// end of class
