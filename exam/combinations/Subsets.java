package exam.combinations;
import java.util.Scanner;

public class Subsets {

	public static void subset(int i, int[] binaryArray) {
		if (i == binaryArray.length) {
			print(binaryArray);
		} else {
			binaryArray[i] = 0;
			subset(i + 1, binaryArray);
			binaryArray[i] = 1;
			subset(i + 1, binaryArray);
		}
	}

	public static void print(int[] binaryArray) {
		for (int i = 0; i < binaryArray.length; i++) {
			System.out.printf("%d ", binaryArray[i]);
		}
		System.out.printf("  {");
		int first = 1;
		for (int i = 0; i < binaryArray.length; i++) {
			if (binaryArray[i] == 1) {
				if (first == 0) {
					System.out.printf(",");
				}
				first = 0;
				System.out.printf("%d", i);
			}
		}
		System.out.printf("}\n");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter n:");
		int n = scan.nextInt();
		int[] binaryArray = new int[n];
		subset(0, binaryArray);
	}
}
