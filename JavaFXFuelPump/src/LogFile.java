//package javafx_fuelpumpproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Dan
 */
public class LogFile extends JavaFX_FuelPumpProject {
    public static String logFilename;
    
    LogFile() { 
        // build the name for the log file
        // identify the folder where the images and log file are located
        // The user's name is in the environment variable USER on MacOS and Linux
        //   but it is in the environment variable USERNAME on Windows
        String ID;
        ID = System.getenv("USER");
        if (ID == null) ID = System.getenv("USERNAME");
        loginID = ID;
        fuelPumpFolder = "/Users/" + loginID + "/Documents/FuelPump/";               
        logFilename = fuelPumpFolder + "LogFile.txt";          
    }  // default constructor
    
    public boolean write(String text) {
        try {
            // build a log record that includes UserID, timestamp and text from parameter
            String logRecord = loginID + " ";

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            logRecord += dateFormat.format(date) + " " + text;
            // Specify the file name and path
            File diskLogFile = new File(logFilename);
            if (!diskLogFile.exists()){
                diskLogFile.createNewFile();
            }
            // open file for appending (add to end) instead of overwritting previous contents
            FileWriter filewriter = new FileWriter(diskLogFile, true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            // add log record to the end of the log file
            bufferedwriter.write(logRecord);
            bufferedwriter.newLine();
            bufferedwriter.close();             // close the file
        }
        catch (IOException ioe) {
            System.out.println("Exception occured writing log file");
            ioe.printStackTrace();
            return true;    // error occurred
        }
        return false;       // no error occurred
    }
    
    public String search (String searchString) {
        String result = "Not found";
        String lineFromFile = "";
        int i;

        // open file for reading
        File diskLogFile = new File(logFilename);
        try {
            Scanner scannerLogFile = new Scanner(new File(logFilename) );
            if (!diskLogFile.exists()){
                diskLogFile.createNewFile();
            }
            TextArea textArea = new TextArea();
            Font fontCourierNew = Font.font("Courier New", FontWeight.NORMAL, 14);
            textArea.setFont(fontCourierNew);

            // return the last occurance of the searchString from within the file
            for (int count=0; scannerLogFile.hasNextLine(); count++) {
                lineFromFile = scannerLogFile.nextLine();
                if (lineFromFile.contains(searchString))
                    result = lineFromFile;
            }
        }       
        catch (IOException ioe) {
            System.out.println("Exception occured reading log file");
            ioe.printStackTrace();
            return null;    // error occurred
        }
        return result;       // no error occurred
    }
    
    public boolean displayLogFile() {
        try {
            // open file for reading
            File diskLogFile = new File(logFilename);
            Scanner scannerLogFile = new Scanner(new File(logFilename) );
            if (!diskLogFile.exists()){
                diskLogFile.createNewFile();
            }
            TextArea textArea = new TextArea();         
            Font fontCourierNew = Font.font("Courier New", FontWeight.NORMAL, 14);
            textArea.setFont(fontCourierNew);
             
            // read the entire file into the textArea
            for (int count=0; scannerLogFile.hasNextLine(); count++) {
               textArea.setText( textArea.getText() + scannerLogFile.nextLine() + "\n");
            }
            scannerLogFile.close();
           
            // use a dialog box to display the contents of the log file
            BorderPane logPane = new BorderPane();
            logPane.setCenter(textArea);
            DialogBox logFileDialog = new DialogBox(logPane, "Log file located at "+logFilename, "Print", "Close", 1200, 800);         
            if (logFileDialog.getClickedButton().equals("Print")) {
                Print viewPrint = new Print();
                viewPrint.printLogFile();
            }
       }       
        catch (IOException ioe) {
            System.out.println("Exception occured reading log file");
            ioe.printStackTrace();
            return true;    // error occurred
        }
        return false;       // no error occurred
    }
    
} // e+nd of class LogFile()