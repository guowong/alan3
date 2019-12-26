/*
 * JavaAverageTemperature 
 *      1) Input several temperatures ended with 999.
 *      2) Compute and display the average temperature
 */
import  java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        double temperature;
        double total = 0.0;
        double average;
        int cityCount = 1;
        
        // create the Scanner object. Name it stdin
        Scanner stdin = new Scanner(System.in);
        
        // title at the top of the output
        System.out.println ("Find the average temperature of several cities");
        System.out.println ("Enter 999 when done");
        
        //   read the temperature for the first city
        System.out.printf ("Enter the temperature for city #%d: ", cityCount);
        temperature = stdin.nextDouble();       
        
        while (temperature != 999.0) {
          total += temperature;

          // input the next temperature
          System.out.printf ("Enter the temperature for city #%d: ", cityCount);
          temperature = stdin.nextDouble();         
          cityCount++;    // increment the loop counter
        } // end of for loop
        
        average = total / cityCount;
        System.out.printf ("\nThe average temperature for %d cities is %8.2f\n",
                              cityCount, average);        
    } // end of main    
} // end of class definition