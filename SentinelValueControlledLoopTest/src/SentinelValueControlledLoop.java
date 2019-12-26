import java.util.Scanner;
import java.util.*; 

public class SentinelValueControlledLoop {

	public static void main(String[] args) {
		
		double iScore = 0;
		double iScoreSum = 0;
		int iStudentCount = 0;
		double iStudentAverage = 0;
		List<Double> scoreList = new ArrayList<Double>();
		
		Scanner stdin = new Scanner(System.in);

		while (iScore != -1)
		{
			System.out.printf("Valid score values are from 0 to 100 \n");
			System.out.printf("Please Input Student Score #%d: \n", iStudentCount+1);
			iScore = stdin.nextDouble(); 
			if (iScore != -1 && 0 <= iScore && iScore <= 100)
			{
				scoreList.add(iScore);
				iStudentCount++;
			}
			else
			{
				System.out.println("Invalid score.");
			}
		}

		
		for (int i = 0; i < scoreList.size(); i++) {
			iScore = scoreList.get(i);
		    iScoreSum = iScoreSum + iScore;

		}		
		
		
		if (iStudentCount != 0){
			System.out.printf("Total Student Count: %d\n ", iStudentCount);
			System.out.println();
			iStudentAverage = iScoreSum / iStudentCount;
			System.out.printf("Average Student Score: %f\n ", iStudentAverage);
			System.out.println();
		}
		else{
			System.out.printf("Total Student Count: %d\n ", iStudentCount);
			System.out.println();
			System.out.print("No scores were entered.");
		}

	}

}
