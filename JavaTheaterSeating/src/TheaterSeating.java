import java.util.Scanner;

public class TheaterSeating {
	public static final double PREMIUM_PRICE = 45.00;
	public static final double STANDARD_PRICE = 30.00;
	public static final double ECONOMY_PRICE = 21.00;
	public static final double TAX_RATE = 0.0825;
	public static final double SURCHARGE = 5.00;
	
	public static void main(String[] args) {
		int premiumSeats = -1;
		int standardSeats = -1;
		int economySeats = -1;
		double subTotal = 0;
		double salesTax = 0;
		double surcharge = 0;
		double total = 0;
		
		Scanner stdin = new Scanner(System.in);
		
		while (premiumSeats == -1)
		{
			System.out.print("\nEnter the number of Premium seats sold: ");
			
			if (stdin.hasNextInt()) 
			{
				int tmp = stdin.nextInt();
				if (tmp >= 0)
					premiumSeats = tmp;
				else
					System.out.print("Invalid input! Try again!!\n\n") ;
			}	
			else
			{
				String strInvalid = stdin.next();
				System.out.print("Invalid input! Try again! \n\n") ;				
				
			}
		}
		
		while (standardSeats == -1)
		{
			System.out.print("\nEnter the number of Standard seats sold: ");
			
			if (stdin.hasNextInt()) 
			{
				int tmp = stdin.nextInt();
				if (tmp >= 0)
					standardSeats = tmp;
				else
					System.out.print("Invalid input! Try again!\n\n") ;
			}	
			else
			{
				String strInvalid = stdin.next();
				System.out.print("Invalid input! Try again! \n\n") ;				
				
			}
		}
	
		while (economySeats == -1)
		{
			System.out.print("\nEnter the number of economy seats sold: ");
			
			if (stdin.hasNextInt()) 
			{
				int tmp = stdin.nextInt();
				if (tmp >= 0)
					economySeats = tmp;
				else
					System.out.print("Invalid input! Try again!\n\n") ;
			}	
			else
			{
				String strInvalid = stdin.next();
				System.out.print("Invalid input! Try again! \n\n") ;				
				
			}
		}
		
		subTotal = premiumSeats * PREMIUM_PRICE + standardSeats * STANDARD_PRICE + economySeats * ECONOMY_PRICE;
		System.out.printf("\nSubtotal: $%.2f\n", subTotal);
		
		salesTax = subTotal * TAX_RATE;
		System.out.printf("\nTax: $%.2f\n", salesTax);
		
		surcharge = SURCHARGE;
		System.out.printf("\nSurcharge: $%.2f\n", surcharge);
		
		total = subTotal + salesTax + surcharge;
		System.out.printf("\nTotal: $%.2f\n", total);

	}

}
