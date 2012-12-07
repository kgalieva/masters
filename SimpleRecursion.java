

import java.util.Scanner;

public class SimpleRecursion {
	
	public static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}
	
	public static void modify(int i, int[] array) {
		print(array);
		if (i < array.length) {
			array[i] = 1;
			modify(i + 1, array);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter n:");
		int n = scan.nextInt();
		int[] array = new int[n];
		/*for (int i = 0; i < array.length; i++) {
			array[i] = 1;
			print(array);
		}
*/		modify(0, array);
	}
}
