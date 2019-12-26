/* JavaLengthofLine.java
   10/20/2019
   Version 0.1
   Alan Wong 
*/

import java.util.Scanner;
import java.util.InputMismatchException;

public class JavaLengthOfLine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x1, y1, x2, y2;
		
		Scanner stdin = new Scanner(System.in);
		
		try {
			System.out.print("Enter x1, y1, x2, y2 separated by spaces: ");
			x1 = stdin.nextDouble();
			y1 = stdin.nextDouble();
			x2 = stdin.nextDouble();
			y2 = stdin.nextDouble();
		}
		catch (InputMismatchException e) {
			System.out.print("Values for hours and pay rate must be numeric ");
			return;
		}

		double length = lengthOfLine(x1, y1, x2, y2);
		System.out.printf("The length is %.4f\n", length);
	}

	private static double lengthOfLine (double x1, double y1, double x2, double y2) {
		 // code for the lengthOfLine function
		double x, y, length;
		x = x1 - x2;
		y = y1 - y2;
		length = Math.sqrt(x*x + y*y);
		return length;
		
	} // end of lengthOfLine
	
}
