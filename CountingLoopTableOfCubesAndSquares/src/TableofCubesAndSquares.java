

public class TableofCubesAndSquares {

	public static void main(String[] args) {
		System.out.printf(" Val  Square  Cube\n");
	
		for (int value=1; value<=20; value++ ) {
			int square = value * value;
			int cube = value * value * value;
			System.out.printf("%3d  %5d   %5d\n", value, square, cube);

		}	
			
	}

}
