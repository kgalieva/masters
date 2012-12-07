package exam.combinations;

import java.util.Scanner;

public class SubsetsKUsingLoops {

	public static void print(int[] binaryArray) {
		for (int i = 0; i < binaryArray.length; i++) {
			System.out.printf("%d ", binaryArray[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter n:");
		int n = scan.nextInt();
		int[] binaryArray = new int[n];
		int k = n / 2;
		int z = n - k;
		int m = 0;
		while (m != k && binaryArray[z] == 0) {
			binaryArray[z++] = 1;
			m++;
			for (int i = z; i < n; i++) {
				if (k - m < n - i) {
					binaryArray[i] = 0;
				} else {
					binaryArray[i] = 1;
					m++;
				}
			}
			print(binaryArray);
			z = n - 1;
			do {
				m -= binaryArray[z--];
			} while (z > 0 && binaryArray[z] >= binaryArray[z + 1]);
		}
	}
}
