import java.util.Scanner;

public class RockPaperScissor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char player1, player2;
		char YesOrNo = 'A';
		
		// create the stdin object (to use the keyboard)
		Scanner stdin = new Scanner(System.in);			
		
		do {
			System.out.print ("Player 1 (R P S): ");
			player1 = stdin.next().toUpperCase().charAt(0); // return 1st char
			
			System.out.print ("Player 2 (R P S): ");
			player2 = stdin.next().toUpperCase().charAt(0); // return 1st char		
			
			if (player1 == 'Q' || player2 == 'Q')
			{
				do
				{
					System.out.print ("Do you want to play again? \n\n");
					YesOrNo = stdin.next().toUpperCase().charAt(0);
					if (YesOrNo == 'Y')
					{
						player1 = 'R';
						player2 = 'R';
					}
					else if (YesOrNo == 'N')
						break;
					else
						System.out.print ("Invalid response, try again: \n\n");
				} while (YesOrNo != 'Y' && YesOrNo != 'N');
			
			} 
			else 
			{			
				if (player1 == player2)
				{
					System.out.print ("player1 and player2 are TIE.\n\n");
				}
				else if (player1 == 'R' && player2 == 'P') 
				{
					System.out.print ("player2 WON!.\n\n");
				}
				else if (player1 == 'P' && player2 == 'S') 
				{
					System.out.print ("player2 WON!.\n\n");
				}
				else if (player1 == 'S' && player2 == 'R') 
				{
					System.out.print ("player2 WON!.\n\n");			
				}
				else if (player1 == 'P' && player2 == 'R') 
				{
					System.out.print ("player1 WON!.\n\n");			
				}		
				else if (player1 == 'S' && player2 == 'P') 
				{
					System.out.print ("player1 WON!.\n\n");			
				}		
				else if (player1 == 'R' && player2 == 'S') 
				{
					System.out.print ("player1 WON!.\n\n");			
				}
				else
				{
					System.out.printf("Invalid Input. player1 = %c  player2 = %c \n\n", player1, player2);		
				}
			}
		} while (player1 != 'Q' && player2 != 'Q');
		
		System.out.print("DONE!");
	}

}
