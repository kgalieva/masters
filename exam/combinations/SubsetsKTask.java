package exam.combinations;

import java.util.Random;
import java.util.Scanner;

public class SubsetsKTask {
	static int n;
	static int k;
	static int[] binaryArray;
	static int[] weights; 
	static int diff = Integer.MAX_VALUE;
	static int[] result;

	public static void subset(int i, int m) {		
		if (i == n) {			
			checkCombination(binaryArray);			
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

	/**
	 * Метод проверяет, является ли текущая комбинация лучше предыдущих 
	 */
	private static void checkCombination(int[] binaryArray){
		int currentDiff = getDiff(binaryArray);
		if (currentDiff < diff) {
			for (int j = 0; j < result.length; j++) {	
				result[j] = binaryArray[j];
			}				
			diff = currentDiff;			
		}
	}

	/**
	 * Метод для подсчета разницы весов мешков в первой и второй куче
	 */
	private static int getDiff(int[] binaryArray) {
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
		n = scan.nextInt();
		k = n / 2;
		binaryArray = new int[n];
		result = new int[n];
		weights = new int[n];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = rand.nextInt(10);
			System.out.print(weights[i] + " ");
		}
		System.out.println();
		subset(0, 0);		

		System.out.println("--- RESULT ---");
		print(result);
		System.out.printf("diff = %d", diff);
	}
}
