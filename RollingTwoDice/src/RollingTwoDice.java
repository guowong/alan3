import java.util.*; 

public class RollingTwoDice {
	
	
	public static void main(String[] args) {
		
		List<Integer> resultCountList = new ArrayList<Integer>();
		for (int i = 0; i < 11; i++)
			resultCountList.add(0);
		
		for (int i=0; i<1000; i++)
		{
			int die1 = (int)(Math.random( )*6) +1; // roll the first die 
			int die2 = (int)(Math.random( )*6) +1; // roll the second die 
			int roll2dice = die1 + die2; // the total of rolling 2 dice		
			
			int prevValue = 0;
			switch(roll2dice)
			{
				case 2:
					prevValue = resultCountList.get(0);
					resultCountList.set(0, prevValue + 1);
					break;
					
				case 3:
					prevValue = resultCountList.get(1);
					resultCountList.set(1, prevValue + 1);					
					break;
					
				case 4:
					prevValue = resultCountList.get(2);
					resultCountList.set(2, prevValue + 1);					
					break;
					
				case 5:
					prevValue = resultCountList.get(3);
					resultCountList.set(3, prevValue + 1);							
					break;
					
				case 6:
					prevValue = resultCountList.get(4);
					resultCountList.set(4, prevValue + 1);							
					break;					
					
				case 7:
					prevValue = resultCountList.get(5);
					resultCountList.set(5, prevValue + 1);							
					break;
					
				case 8:
					prevValue = resultCountList.get(6);
					resultCountList.set(6, prevValue + 1);							
					break;
					
				case 9:
					prevValue = resultCountList.get(7);
					resultCountList.set(7, prevValue + 1);							
					break;
					
				case 10:
					prevValue = resultCountList.get(8);
					resultCountList.set(8, prevValue + 1);							
					break;
					
				case 11:
					prevValue = resultCountList.get(9);
					resultCountList.set(9, prevValue + 1);							
					break;						
					
				case 12:
					prevValue = resultCountList.get(10);
					resultCountList.set(10, prevValue + 1);							
					break;						
					
				default:
					
			}
			
			
			
		}
		
		
		for (int i = 0; i < resultCountList.size(); i++)
		{
			System.out.printf("resultCountList %d  =  %d \n", i+2, resultCountList.get(i)) ;		
		}		
		
		
	}

}
