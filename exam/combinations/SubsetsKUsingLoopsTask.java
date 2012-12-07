package exam.combinations;

import java.util.Random;
import java.util.Scanner;

public class SubsetsKUsingLoopsTask {

	/**
	 * Метод проверяет, является ли текущая комбинация лучше предыдущих 
	 */
	private static int checkCombination(int diff, int[] binaryArray, int[] weights, int[] result){
		int currentDiff = getDiff(binaryArray, weights);
		if (currentDiff < diff) {
			for (int j = 0; j < result.length; j++) {	
				result[j] = binaryArray[j];
			}				
			diff = currentDiff;			
		}
		return diff;
	}

	/**
	 * Метод для подсчета разницы весов мешков в первой и второй куче
	 */
	private static int getDiff(int[] binaryArray, int[] weights) {
		int diff = 0;
		for (int i = 0; i < weights.length; i++) {
			diff += Math.pow(-1,binaryArray[i]) * weights[i];  
		}
		return Math.abs(diff);
	}

	/**
	 * Печать массива
	 */
	public static void print(int[] binaryArray) {
		for (int i = 0; i < binaryArray.length; i++) {
			System.out.printf("%d ", binaryArray[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		System.out.println("Enter n:");
		int n = scan.nextInt();
		int[] binaryArray = new int[n];
		int[] result = new int[n];
		int[] weights = new int[n];
		int diff = Integer.MAX_VALUE;
		for (int i = 0; i < weights.length; i++) {
			weights[i] = rand.nextInt(10);
			System.out.print(weights[i] + " ");
		}
		System.out.println();
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
			diff = checkCombination(diff, binaryArray, weights, result);
			z = n - 1;
			do {
				m -= binaryArray[z--];
			} while (z > 0 && binaryArray[z] >= binaryArray[z + 1]);
		}
		System.out.println("--- RESULT ---");
		print(result);
		System.out.printf("diff = %d", diff);
	}
}
