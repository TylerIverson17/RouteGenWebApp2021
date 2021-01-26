package MyClasses;
/*
Tyler's class for choosing an external file.
    getData method is what is called in order to read an exernal file from CreateRoute class
           Adds the data to a linked list with address objects
 */
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {

    public String filePath;
    public ArrayList<Integer> houselist = new ArrayList<>();
    public ArrayList<Integer> streetlist = new ArrayList<>();

    public ReadFile() throws Exception {
        this.getData();
    }

    public void getData() throws Exception{

        // Tyler's file selector - Java default package
        File file;
        Scanner fileIn;
        int response;
        JFileChooser chooser = new JFileChooser("."); // . for project folder

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response = chooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
            try{
                fileIn = new Scanner(file);
                if(file.isFile()){
                    while(fileIn.hasNextLine()){
                        String data = fileIn.nextLine();
                        StringTokenizer tokenizer = new StringTokenizer(data);
                        int houseNumToken = Integer.parseInt(tokenizer.nextToken());
                        int streetNameToken = Integer.parseInt(tokenizer.nextToken());
                        houselist.add(houseNumToken);
                        streetlist.add(streetNameToken);
                    }
                }else{
                    System.out.println("that was not a file!!!");
                }
                fileIn.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("An error has occurred with choosing a file!");
        }

    }

}
