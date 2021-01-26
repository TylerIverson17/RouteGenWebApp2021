package MyClasses;
/*
My class - Tyler Iverson
 */
import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class ChooseFile {

    public static void main(String[] args){
        //Test test = new Test();
        File file;
        Scanner fileIn;
        int response;
        JFileChooser chooser = new JFileChooser("."); // . for project folder

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        response = chooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
        }else{
            System.out.println("An error has occurred with choosing a file!");
        }
    }

}
