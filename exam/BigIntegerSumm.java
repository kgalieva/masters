package exam;

import java.util.Scanner;

public class BigIntegerSumm {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Введите первое число");
		String s1 = scan.next();
		System.out.println("Введите второе число");
		String s2 = scan.next();
		int k1 = s1.length();
		int k2 = s2.length();
		//Заводим два массива для хранения чисел
		int[] a1 = new int[100];
		int[] a2 = new int[100];
		//Заводим третий массив для хранения суммы
		int[] a3 = new int[100];
		//в первой ячейке массива будем хранить количество знаков в числе
		a1[0] = k1;
		a2[0] = k2;
		//заносим по одной цифре в каждую ячейку массива в обратном порядке
		for (int i = 1; i <= k1; i++) {
			a1[i] = (int) s1.charAt(k1 - i) - 48;
		}
		for (int i = 1; i <= k2; i++) {
			a2[i] = (int) s2.charAt(k2 - i) - 48;
		}
		//инициализируем длину массива с суммой максимальной из длин двух слагаемых
		int max = k1 > k2 ? k1 : k2;
		a3[0] = max;
		
		for (int i = 1; i <= max; i++) {
			a3[i + 1] = (a1[i] + a2[i] + a3[i]) / 10;
			//в текущую 
			a3[i] = (a3[i] + a2[i] + a1[i]) % 10;
		}
		if (a3[a3[0] + 1] != 0) {
			a3[0]++;
		}
		for (int i = a3[0]; i > 0; i--) {
			System.out.print(a3[i]);
		}
	}
}
