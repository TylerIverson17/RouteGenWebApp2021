package MyClasses;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadFile {

    public LinkedList<Address> dataList = new LinkedList<Address>();
    public String filePath;
    public int count;

    public ReadFile(String filePath) throws Exception {
        this.getData(filePath);
    }

    public void getData(String filePath) throws Exception{

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
                        //Create address obj from strings coming from the file
                        StringTokenizer tokenizer = new StringTokenizer(data);
                        int houseNumToken = Integer.parseInt(tokenizer.nextToken());
                        String streetNameToken = tokenizer.nextToken();
                        String streetTypeToken = tokenizer.nextToken();
                        Address addressData = new Address(houseNumToken,streetNameToken,streetTypeToken);
                        this.dataList.add(addressData);
                        count++;
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

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }



    public static void main(String[] args) throws Exception{

    }
}
