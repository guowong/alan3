import java.util.Scanner;

public class SumAvgRange {
	public static final int NUMBER_COUNT = 10;
	
	public static void main(String[] args) {
		double sumOfOddNumbers = 0;
		double sumOfEvenNumbers = 0;
		double sumOfAllNumbers = 0;
		int number;
		double minValue = 0;
		double maxValue = 0;
		double inputCounter = 1;
		double average = 0;
		String strInvalid = "";
		
		Scanner stdin = new Scanner(System.in);
		
		while (inputCounter <= NUMBER_COUNT) {
			System.out.print("Enter a number: ") ;
			if (stdin.hasNextInt()) { 
				number = stdin.nextInt();

				if (number < 0){
					System.out.print("Invalid input! Try again!\n\n") ;
					continue;
				}
				if (number % 2 == 0) 
					sumOfEvenNumbers += number;
				else
					sumOfOddNumbers += number;

				sumOfAllNumbers += number;
				
				if (inputCounter == 1) {
					minValue = number;
					maxValue = number;
				}
				else {
					if (number < minValue)
						minValue = number;
					if (number > maxValue)
						maxValue = number;
				}
				
				inputCounter++;					
			
			} else {

				strInvalid = stdin.next();
				System.out.print("Invalid input! Try again! \n\n") ;
			}
		}
		average = sumOfAllNumbers/10;
		
		System.out.println() ;
		System.out.printf("Sum of Odd Numbers: %.2f\n", sumOfOddNumbers) ;
		System.out.printf("Sum of Even Numbers: %.2f\n", sumOfEvenNumbers) ;
		System.out.printf("Sum of All Numbers: %.2f\n", sumOfAllNumbers) ;
		System.out.printf("The lowest value is: %.2f\n",  minValue) ;
		System.out.printf("The highest value is: %.2f\n", maxValue) ;
		System.out.printf("The average is: %.2f\n", average) ;
		
		System.out.println() ;
	}

}
