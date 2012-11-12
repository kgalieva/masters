import java.util.Random;

/**
 * Реализация и описание четырех различных алгоритмов сортировки массивов. 
 */
public class ArraySorting {

	/**
	 * <h3>Сортировка пузырьком</h3>
	 * Простейший алгоритм сортировки <br/>
	 * Сложность O(n*n)
	 * <h3> Алгоритм </h3>
	 * <p> Идем по массиву, сравниваем текущий элемент с соседним слева, если текущий
	 * элемент меньше, то меняем их местами. Продолжаем эту операцию до тех пор,
	 * пока при очередном проходе по массиву не будет сделано ни одной
	 * перестановки.</p>
	 * 
	 * @param array Сортируемый массив
	 */
	public static void bubbleSort(int[] array) {
		if ((array == null) || (array.length < 2)) {
			return;
		}
		/*
		 * Флаг, указывающий отсортирован массив или нет. true - массив не
		 * отсортирован false - массив отсортирован
		 */
		boolean unsorted = true;
		while (unsorted) {
			// меняем значение флага, если не сделаем ни одной перестановки, то
			// выйдем из цикла while
			unsorted = false;
			for (int i = 0; i < array.length - 1; i++) {
				if (array[i] > array[i + 1]) {
					// меняем местами i-ый и i+1-ый элементы
					ArrayAlgorithms.swap(array, i, i + 1);
					/*
					 * если находим хотя бы один элемент, который стоит не на
					 * отсортированный позиции, меняем флаг на true, означающий,
					 * что требуется еще как минимум один проход по массиву,
					 * чтобы убедиться, что он отсортирован.
					 */
					unsorted = true;
				}
			}
		}
	}

	/**
	 * <h3>Быстрая сортировка(Quick Sort)</h3> 
	 * Cреднее время выполнения - O(n log n)<br/>
	 * <h3>Алгоритм:</h3>
	 * <p>Генерируем случайный индекс элемента массива. Элементы, которые
	 * находятся слева и больше по значению чем первоначальный, меняются местами
	 * с элементами, которые находятся справа и меньше первоначального.
	 * Аналогичная операция рекурсивно выполняется для наборов данных слева и
	 * справа от начального.</p>
	 * 
	 * @see Partitioning#partitioning(int[])
	 * @param array Сортируемый массив
	 * @param start Начальный индекс сортируемой области
	 * @param end   Конечный индекс сортируемый области
	 */	
	public static void quickSort(int[] array, int start, int end) {
		if ((array == null) || (array.length < 2) || (start >= end)) {
			return;
		}
		int i = start;
		int j = end;
		Random rand = new Random();
		/*
		 * Генерируем случайный индекс элемента массива такой, что start <=
		 * current <= end
		 */
		int current = rand.nextInt(end - start + 1) + start;
		while (i < j) {
			// ищем элемент больший элемент текущего в левой части массива
			while ((array[current] >= array[i]) && (i < current)) {
				i++;
			}
			// ищем элемент меньший элемент текущего в правой части массива
			while ((array[current] <= array[j]) && (j > current)) {
				j--;
			}
			if (i < j) {
				// меняем местами i-ый и j-ый элементы
				ArrayAlgorithms.swap(array, i, j);
				// если текущий элемент участвовал в перестановке, обновляем его
				// индекс
				if (current == i) {
					current = j;
					i++;
				} else {
					if (current == j) {
						current = i;
						j--;
					}
				}
			}
		}
		// аналогичным алгоритмом сортируем правую и левую части массива.
		quickSort(array, start, current);
		quickSort(array, current + 1, end);

	}

	/**
	 * <h3>Сортировка слиянием (Mergesort)</h3>
	 * Сложность O(n*log(n)) <br/>
	 * Недостаток: требуется дополнительная память размера n
	 * 
	 * <h3>Алгоритм сортировки слиянием</h3>
	 * <ol>
	 * 	<li>	Сортируемый массив разбивается на две части примерно одинакового размера. 
	 * 			В случае, когда длина массива равна единице, разбиение не происходит, 
	 * 			массив уже упорядочен (любой массив длины 1 можно считать упорядоченным).</li>
     * 	<li>	Две упорядоченные части массива соединяются слиянием.</li>
     * </ol>
	 * 
	 * @param a Сортируемый массив
	 */
	public static void mergeSort(int[] a) {
		int[] tmpArray = new int[a.length];
		mergeSort(a, tmpArray, 0, a.length - 1);
	}

	/**
	 * Внутренний рекурсивный метод сортировки слиянием
	 *  
	 * @param a Сортируемый массив
	 * @param tmpArray Вспомогательный массив для объединения двух отсортированных массивов в один
	 * @param left  Начальный индекс сортируемой области            
	 * @param right Конечный индекс сортируемый области
	 *            
	 */
	private static void mergeSort(int[] a, int[] tempArray, int left, int right) {
		/*В случае, когда длина массива равна единице, разбиение не происходит, 
		 * массив уже упорядочен (любой массив длины 1 можно считать упорядоченным).
		 */
		if (left < right) {
			//Сортируемый массив разбивается на две части примерно одинакового размера. 
			int center = (left + right) / 2;
			mergeSort(a, tempArray, left, center);
			mergeSort(a, tempArray, center + 1, right);
			//Две упорядоченные части массива соединяются слиянием.
			merge(a, tempArray, left, center + 1, right);
		}
	}

	/**
	 * Слияние двух отсортированных частей массива
	 * 
	 * @param a        Сортируемый массив
	 * @param tmpArray Вспомогательный массив
	 * @param leftPos  Начальный индекс левой части
	 * @param rightPos Начальный индекс правой части
	 * @param rightEnd Конечный индекс правой части
	 */
	private static void merge(int[] a, int[] tmpArray, int leftPos,	int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		
		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (a[leftPos] <= a[rightPos]) {
				tmpArray[tmpPos++] = a[leftPos++];
			} else {
				tmpArray[tmpPos++] = a[rightPos++];
			}
		}

		//копируем остаток левой части
		while (leftPos <= leftEnd) { 
			tmpArray[tmpPos++] = a[leftPos++];
		}

		//копируем остаток правой части
		while (rightPos <= rightEnd) {
			tmpArray[tmpPos++] = a[rightPos++];
		}

		//копируем результат слияния из временного массива
		for (int i = 0; i < numElements; i++, rightEnd--) {
			a[rightEnd] = tmpArray[rightEnd];
		}
	}
	
	/**
	 * <h3>Сортировка простыми вставками</h3>
	 * <ul>
	 * 	<li>эффективен на небольших наборах данных, на наборах данных до десятков элементов может оказаться лучшим;</li>
	 * 	<li>эффективен на наборах данных, которые уже частично отсортированы;</li>
	 * 	<li>это устойчивый алгоритм сортировки (не меняет порядок элементов, которые уже отсортированы);</li>
	 * 	<li>может сортировать список по мере его получения;</li>
	 * 	<li>использует O(1) временной памяти</li>
	 * </ul>
	 * 
	 * <p>Минусом является высокая сложность алгоритма: O(n²).</p>
	 * 
	 * <h3>Алгоритм сортировки простыми вставками.</h3>
	 * <p> В цикле для каждого элемента массива начиная со 2ого выполняются следующие действия:
	 * Перебирая элементы от текущего индекса до начала массива пока не встретится элемент меньше текущего, 
	 * находится позиция, на которой должен стоять текущий элемент. Все элементы большие текущего,
	 * стоящие на позициях с индексами меньшими исходного индекса текущего элемента сдвигаются вправо. 
	 * Текущий элемент переставляется на найденную позицию.</p>
	 *
	 * @param array Сортируемый массив
	 * @param start Начальный индекс сортируемой области
	 * @param end   Конечный индекс сортируемый области
	 */
	public static void insertionSort(int[] array, int start, int end) {
		int j;
		for (int i = start + 1; i <= end; i++) {
			int c = array[i];
			for (j = i - 1; j >= start && array[j] > c; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = c;
		}
	}

	public static void main(String[] args) {
		int[] array;
		// тестируем метод пузырьковой сортировки
		for (int i = 0; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			ArraySorting.bubbleSort(array);
			// в цикле осуществляем проверку, осортированы ли элементы массива
			// по возрастанию
			for (int j = 1; j < array.length; j++) {
				// Если находим элемент, стоящий не на отсортированной позиции,
				// выводи сообщение об ошибке.
				if (array[j - 1] > array[j]) {
					System.err.println("Not sorted!");
					ArrayAlgorithms.print(array);
					break;
				}
			}
		}
		// тестируем метод быстрой сортировки
		for (int i = 0; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			ArraySorting.quickSort(array, 0, array.length - 1);
			/*
			 * осуществляем проверку, осортированы ли элементы массива по
			 * возрастанию c помощью метода isSorted реализованного в классе
			 * ArrayAlgorithms
			 */
			if (!ArrayAlgorithms.isSorted(array)) {
				System.err.println("Not sorted!");
				ArrayAlgorithms.print(array);
			}
		}

		// тестируем метод сортировки слиянием
		for (int i = 0; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			ArraySorting.mergeSort(array);		
			if (!ArrayAlgorithms.isSorted(array)) {
				System.err.println("Not sorted!");
				ArrayAlgorithms.print(array);
			}
		}

		// тестируем метод сортировки вставками
		for (int i = 0; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			ArraySorting.insertionSort(array, 0, array.length - 1);		
			if (!ArrayAlgorithms.isSorted(array)) {
				System.err.println("Not sorted!");
				ArrayAlgorithms.print(array);
			}
		}
	}

}
