import java.util.Scanner;

public class ElectricBill {
	// define constant
	public static final double Base_kWh = 0.27; 
	public static final double Extra_kWh = 0.55;
	public static final double Base_limit = 500;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// define program specific variable
		double consumed_in_a_month;
		double Base_Bill;
		double Extra_Bill;
		double Electric_Bill;
		
		// create the stdin object (to use the keyboard)
		Scanner stdin = new Scanner(System.in);		
		
		System.out.print("\n");
		System.out.print("\nType in, number of kWh consumed in a month: "); 
		System.out.print("\n");
		consumed_in_a_month = stdin.nextDouble();
		
		if (consumed_in_a_month < Base_limit)
		{
			Base_Bill = consumed_in_a_month * Base_kWh;
			Extra_Bill = 0;		
		} 
		else
		{
			Base_Bill = Base_limit * Base_kWh;
			Extra_Bill = (consumed_in_a_month - Base_limit)	* Extra_kWh;		
		}
		
		Electric_Bill = Base_Bill + Extra_Bill;
		
		System.out.printf ("\nYour Total Electrical Bill is $%.2f Dollars \n", Electric_Bill); 
	}

}
