/* CountingChange.java
   9/15/2019
   Version 0.1
   Alan Wong
  */
package paycheck;
import java.util.Scanner;

public class CountChanges {
	public static final double QUARTER = 0.25; 
	public static final double DIME = 0.1; 
	public static final double NICKEL = 0.05; 
	public static final double PENNY = 0.01; 

	public static void main(String[] args) {
		int quarters, dimes, nickels, pennies;
		double total;
		
		// create the stdin object (to use the keyboard)
		Scanner stdin = new Scanner(System.in);		
		
		System.out.printf ("\n");
		System.out.print ("\nAlan Wong's Coin counting program: "); 
		System.out.printf ("\n");
		System.out.print ("\nEnter the number of quarters: "); 
		quarters = stdin.nextInt();
		System.out.print ("\nEnter the number of dimes: "); 
		dimes = stdin.nextInt();
		System.out.print ("\nEnter the number of nickels: "); 
		nickels = stdin.nextInt();
		System.out.print ("\nEnter the number of pennies: "); 
		pennies = stdin.nextInt();
		
		total = quarters * QUARTER + dimes * DIME + nickels * NICKEL + pennies * PENNY;
		System.out.printf ("\nYour Total is $%.2f Dollars \n", total); 
		//System.out.print (total); 
		
		//System.out.printf ("Your gross pay is $%.2f Dollars1\n", grossPay);
		
	}

}
