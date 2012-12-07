package exam.combinations;

import java.util.Random;
import java.util.Scanner;

public class SubsetsTask {

	static int[] weights; 
	static int diff = Integer.MAX_VALUE;
	static int[] result;

	/**
	 * Рекурсивный метод генерации комбинаций
	 * */
	public static void subset(int i, int[] binaryArray) {
		if (i == binaryArray.length) {
			checkCombination(binaryArray);
		} else {
			binaryArray[i] = 0;
			subset(i + 1, binaryArray);
			binaryArray[i] = 1;
			subset(i + 1, binaryArray);
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
		int n = scan.nextInt();
		int[] binaryArray = new int[n];
		result = new int[n];
		weights = new int[n];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = rand.nextInt(10);
			System.out.print(weights[i] + " ");
		}
		System.out.println();
		subset(0, binaryArray);

		System.out.println("--- RESULT ---");
		print(result);
		System.out.printf("diff = %d", diff);
	}
}
