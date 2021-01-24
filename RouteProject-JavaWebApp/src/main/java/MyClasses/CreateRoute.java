package MyClasses;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CreateRoute {

    //Data
    public String filePath = "..\\Addresses.txt";
    public LinkedList<Address> organizedData = new LinkedList<Address>();



    //take in the linked list from ReadFile and sort it in a meaningful way
    public CreateRoute() throws Exception {

        boolean foundStreetName = false;
        boolean foundSmallestNumber = false;

        ReadFile fileReader = new ReadFile(filePath);

        // Starting the process of inserting and sorting with custom compare method override
        for(Address fileAddress : fileReader.dataList){

            organizedData.add(fileAddress);

            //compare fileAddress against organizedData to find insertion point
            Collections.sort(organizedData, new Comparator<Address>() {
                @Override
                public int compare(Address o1, Address o2) {

                    String streetName1 = o1.streetName + o1.streetType;
                    String streetName2 = o2.streetName + o2.streetType;
                    int streetNameCmp = streetName1.compareTo(streetName2);
                    if (streetNameCmp != 0){
                        return streetNameCmp;
                    }
                    Integer houseNum1 = o1.houseNumber;
                    Integer houseNum2 = o2.houseNumber;
                    int houseNumCmp = houseNum1.compareTo(houseNum2);
                    if (houseNumCmp != 0){
                        return houseNumCmp;
                    }
                    return 0;
                }
            });
        }

    }

    public String getOrganizedString(){
        String data = "";
        for(Address r : organizedData){
            data += r.toString() + "|";
        }
        return data;
    }


}
