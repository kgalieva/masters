import java.util.Random;
import java.util.Scanner;


public class Matrix {
	/**
	 * Генерация прямоугольной матрицы заполненой псевдослучайными значениями	
	 * @param n Количество строк
	 * @param m Количество столбцов
	 * @return Матрица заполненая псевдослучайными значениями
	 */
	public static int[][] randomMatrix(int n, int m) {
		int[][] matrix = new int[n][m];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {				
				matrix[i][j] = rand.nextInt(100);
			}
		}
		return matrix;
	}

	/**
	 * Печать матрицы
	 * @param matrix Матрица, которую требуется вывести на экран
	 */
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {			
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of rows:");
		int n = scanner.nextInt();
		System.out.println("Enter the number of columns:");
		int m = scanner.nextInt();
		int[][] matrix = randomMatrix(n, m);
		printMatrix(matrix);
	}

}
