package exam.combinations;

import java.util.Scanner;

public class SubsetsK {
	static int n;
	static int k;
	static int[] binaryArray;

	public static void subset(int i, int m) {
		if (i == n) {
			print(binaryArray);
		} else {
			if (k - m < n - i) {
				binaryArray[i] = 0;
				subset(i + 1, m);
			}
			if (m < k) {
				binaryArray[i] = 1;
				subset(i + 1, m + 1);
			}
		}
	}

	public static void print(int[] binaryArray) {
		for (int i = 0; i < binaryArray.length; i++) {
			System.out.printf("%d ", binaryArray[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter n:");
		n = scan.nextInt();
		k = n / 2;
		binaryArray = new int[n];
		subset(0, 0);
	}
}
