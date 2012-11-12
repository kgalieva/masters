import java.util.Random;

/**
 * Алгоритмы работы с массивами
 * Пары 20.10.2012 и 27.10.2012
 */
public class ArrayAlgorithms {
	
	/**
	 * Метод возвращает первый элемент массива. 
	 * Индексация элементов массивов всегда начинается с 0.
	 * @param a Массив.
	 * @return Первый элемент массива.
	 */
	public static int getFirst(int[] array) {
		return array[0];
	}
	
	/**
	 * Метод возвращает последний элемент массива.
	 * Последний элемент имеет индекс на единицу меньший длины массива, 
	 * поскольку индексация элементов массивов начинается с 0.
	 * @param a Массив.
	 * @return Последний элемент массива.
	 */
	public static int getLast(int[] array) {
		return array[array.length - 1];
	}
	
	/**
	 * Метод возвращает длину массива. 
	 * @param a Массив.
	 * @return Длина массива.
	 */
	public static int getLength(int[] array) {
		return array.length;
	}

	/**
	 * Печать всех элементов массива в одну строку.
	 * @param a Печатаемый массив.
	 */
	public static void print(int[] array) {
		for (int e : array) {
			System.out.print(e + " ");
		}
		System.out.println();
	}

	/**
	 * Печать элементов массива в обратном порядке
	 * @param a Печатаемый массив.
	 */
	public static void printReverced(int[] array) {
		for (int i = array.length - 1; i >= 0; i--) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Генерация случайного целочисленного массива заданной длины.
	 * @param len Длина.
	 * @return Сгенерированный массив.
	 */
	public static int[] randomArray(int len) {
		Random random = new Random();
		int[] a = new int[len];
		// Заполняем массив случайными значениями из интервала 0..(5*len - 1)
		for (int i = 0; i < len; i++) {
			a[i] = random.nextInt(5 * len);
		}
		return a;
	}


	/**
	 * Поиск максимального элемента в массиве.
	 * @param a Массив, в котором ищем максимум.
	 * @return Максимальный элемент (непустого) массива.
	 */
	public static int getMax(int[] array) {
		int max = Integer.MIN_VALUE;
		for (int e : array) {
			if (e > max){
				max = e;
			}
		}
		return max;
	}

	/**
	 * Поэлементное сравнение массивов.
	 * @param a1 Первый массив.
	 * @param a2 Второй массив.
	 * @return Результат сравнения.
	 */
	public static boolean equals(int[] a1, int[] a2) {
		if ((a1 == null) || (a2 == null) || (a1.length != a2.length)) {
			return false;
		}
		for (int i = 0; i < a1.length; i++) {
			if (a1[i] != a2[i]){
				return false;
			}
		}
		return true;
	}


	/**
	 * Простой (линейный) поиск в массиве.
	 * @param a Массив, в котором производится поиск.
	 * @param key Ключ поиска.
	 * @return Индекс найденного элемента или -1,
	 *     если элемент не найден.
	 */
	public static int indexOf(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}  

	/**
	 * Вычисление длины максимальной возрастающей подпоследовательности в массиве.
	 * @param array Исходный массив.
	 * @return Длина максимальной возрастающей  подпоследовательности.
	 */
	public static int subsequence(int[] array) {
		if(array.length == 0){
			return 0;
		}
		int lenMax = 1;		
		int lenCurrent = 1;
		int current = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] >= current) {
				if (++lenCurrent > lenMax) {
					lenMax = lenCurrent;
				}
			} else {				
				lenCurrent = 1;
			}
			current = array[i];
		}
		return lenMax;
	}
	
	/**
	 * Перестановка i-го и j-го элементов массива местами
	 * @param array Массив, в котором осуществляется перестановка
	 * @param i     Индекс первого элемента участвующего в перестановке 
	 * @param j     Индекс второго элемента участвующего в перестановке 
	 */
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/**
	 * Метод проверяет, отсортирован ли массив по возрастанию
	 * @param array массив
	 * @return true, если массив отсортирован по возрастанию
	 * false - в противном случае
	 */
	public static boolean isSorted(int[] array){
		for (int i = 1; i < array.length; i++){
			if(array[i - 1] > array[i]){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Создание копии массива
	 * @param array исходный массив
	 * @return новый массив с теми же данными, что и в исходном массиве
	 */
	public static int[] copyArray(int[] array) {		
		return copyArray(array, 0, array.length -1);
	}
	
	/**
	 * Создание копии массива с индекса start до индекса end включительно
	 * @param array исходный массив
	 * @return новый массив размера end - start с данными, 
	 * стоящими в исходном массиве с индекса start до индекса end включительно
	 */
	public static int[] copyArray(int[] array, int start, int end) {
		if ((array == null) || (start > end)) {
			return null;
		}		
		int[] result = new int[end - start + 1];
		for (int i = start; i <= end; i++) {
			result[i - start] = array[i]; 
		}
		return result;
	}

	public static void main(String[] args) {
		// Генерируем массив длины 15
		int[] array = randomArray(15);		

		System.out.println("Исходный массив:");
		print(array);
		
		System.out.printf("Первый элемент массива: %d\n", getFirst(array));
		
		System.out.printf("Последний элемент массива: %d\n", getLast(array));
		
		System.out.printf("Длина массива: %d\n", getLength(array));

		System.out.println("Исходный массив распечатанный в обратном порядке:");
		printReverced(array);

		System.out.printf("Максимальный элемент в этом массиве: %d\n", getMax(array));

		// Создаем второй массив длины 15 и сразу заполняем его данными 
		int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		System.out.println("Второй массив:");
		print(array2);		

		System.out.printf(
				"Сравниваем исходный массив со вторым массивом. Результат сравнения: %b\n", 
				equals(array, array2));
		System.out.printf(
				"Сравниваем исходный массив с самим собой. Результат сравнения: %b\n", 
				equals(array, array));		

		System.out.printf("Ищем элемент %d. Результат поиска: %d\n", array[12], indexOf(array, array[12]));
		System.out.printf("Ищем элемент %d. Результат поиска: %d\n", 50, indexOf(array, 50));

		// В полученном массиве ищем максимальную возрастающую подпоследовательность.
		System.out.printf("Длина максимальной возрастающей подпоследовательности: %d\n",
				subsequence(array));
	}

}
