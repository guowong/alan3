//package javafxeventregistry;

import java.io.*;
import java.util.Scanner;

public class ReadTextFile {  
    private final int MAX_LINES = 200;
    private String[] fileContents = new String[MAX_LINES]; // to hold file contents
    private int count;

    // Declare the variables before the try statement, or else they will be local
    //  to the try block and won’t be able to be used later in the program.
    Scanner infile; // declare reference here, create object in the try block
    boolean fileError = false;
   
   // constructor
    ReadTextFile(String filename) {       
       try {
           infile = new Scanner( new File(filename) ); // create the stream
           // read the entire file into the fileContents array
           for (count=0; infile.hasNextLine() && count<MAX_LINES; count++) {
              fileContents[count] = infile.nextLine();
           }
           infile.close();
       }       
       catch (FileNotFoundException e) {
           fileError = true;
       } 
   } // end of constructor
   
   // getters
   public int getLineCount() { return count; }
   public String[] getFileContents() { return fileContents; }
   public boolean isFileError() { return fileError; }

} // end of class ReadTextFile