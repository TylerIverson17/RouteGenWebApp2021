package MyClasses;

public class Driver {



    public static void main(String[] args) throws Exception {
        //Data
        // String filePath = "src\main\java\Addresses.txt";

        //Read from file and create a meaningful route
        CreateRoute newRoute = new CreateRoute();

        // Test the linked list
        for(Address r : newRoute.organizedData) {
            System.out.println(r.toString());
        }
    }
}


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

    public String toString(){
        String houseNum = String.valueOf(this.houseNumber);
        return houseNum + " " + this.streetName + " " + this.streetType;
    }

}
