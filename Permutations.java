

import java.util.Scanner;

public class Permutations {

	public static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d ", array[i]);
		}
		System.out.println();
	}

	public static void permutation(int[] array) {
		print(array);

		int k = array.length - 2;
		while (k >= 0 && array[k] > array[k + 1]) {
			k--;
		}
		if (k < 0) {
			return;
		} else {
			int j = array.length - 1;
			while (array[k] > array[j]) {
				j--;
			}
			swap(j, k, array);
			int r = array.length - 1;
			int s = k + 1;
			while (r > s) {
				swap(r, s, array);
				r--;
				s++;
			}
		}
		permutation(array);
	}

	public static void swap(int i, int j, int[] array) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter n:");
		int n = scan.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}
		permutation(array);
	}
}
