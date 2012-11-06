import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class ArraySorting {

	/**
	 * Сортировка пузырьком
	 * Простейший алгоритм сортировки
	 * Сложность O(n*n) 
	 * Идем по массиву, сравниваем текущий элемент с соседним слева, если текущий элемент меньше, то меняем их местами. 
	 * Продолжаем эту операцию до тех пор, пока при очередном проходе по массиву не будет сделано ни одной перестановки.
	 * @param array Сортируемый массив
	 */
	public static void bubbleSort(int[] array) {
		if ((array == null) || (array.length < 2)){
			return;
		}
		/* Флаг, указывающий отсортирован массив или нет.
		 * true - массив не отсортирован 
		 * false - массив отсортирован
		 */	
		boolean unsorted = true;		
		while (unsorted) {
			//меняем значение флага, если не сделаем ни одной перестановки, то выйдем из цикла while
			unsorted = false;
			for (int i=0; i < array.length - 1; i++){ 
				if (array[i] > array[i + 1]) {    
					//меняем местами i-ый и i+1-ый элементы
					ArrayAlgorithms.swap(array, i, i + 1);
					/* если находим хотя бы один элемент, который стоит не на отсортированный позиции,
					 * меняем флаг на true, означающий, что требуется еще как минимум один проход по массиву,
					 * чтобы убедиться, что он отсортирован.   
					 */
					unsorted = true;
				} 
			}
		} 
	}

	/**
	 * Быстрая сортировка(Quick Sort) 
	 * Cреднее время выполнения - O(n log n)
	 * Алгоритм:
	 * Генерируем случайный индекс элемента массива. 
	 * Элементы, которые находятся слева и больше по значению чем первоначальный, меняются местами с элементами, 
	 * которые находятся справа и меньше первоначального. 
	 * Аналогичная операция рекурсивно выполняется для наборов данных слева и справа от начального.
	 * @see Partitioning#partitioning(int[])
	 * @param array Сортируемый массив
	 */
	public static void quickSort(int[] array, int start, int end) {
		if ((array == null) || (array.length < 2) || (start >= end)) {
			return;
		}
		int i = start;
		int j = end;		
		Random rand = new Random();
		/*Генерируем случайный индекс элемента массива
		 * такой, что start <= current <= end
		 */
		int current = rand.nextInt(end - start + 1) + start;
		while (i < j) {
			//ищем элемент больший элемент текущего в левой части массива 
			while ((array[current] >= array[i]) && (i < current)) {
				i++;
			}
			//ищем элемент меньший элемент текущего в правой части массива
			while ((array[current] <= array[j]) && (j > current)) {
				j--;
			}
			if (i < j) {
				//меняем местами i-ый и j-ый элементы				
				ArrayAlgorithms.swap(array, i, j);
				//если текущий элемент участвовал в перестановке, обновляем его индекс
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
		//аналогичным алгоритмом сортируем правую и левую части массива.
		quickSort(array, start, current);
		quickSort(array, current + 1, end);

	}	

	/**
	 * TODO опубликую, когда выйдет срок сдачи домашних работ
	 * @param array
	 */
	public static void mergeSort(int[] array){

	}

	/**
	 * TODO  опубликую, когда выйдет срок сдачи домашних работ
	 * @param array
	 */
	public static void insertionSort(int[] array){

	}	


	public static void main(String[] args) {	
		int [] array;
		//тестируем метод пузырьковой сортировки
		for (int i = 0; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			ArraySorting.bubbleSort(array);
			//в цикле осуществляем проверку, осортированы ли элементы массива по возрастанию
			for (int j = 1; j < array.length; j++) {
				//Если находим элемент, стоящий не на отсортированной позиции, выводи сообщение об ошибке.
				if (array[j - 1] > array[j]) {					
					System.err.println("Not sorted!");
					ArrayAlgorithms.print(array);
					break;
				}
			}
		}
		//тестируем метод быстрой сортировки
		for (int i = 0; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			ArraySorting.quickSort(array, 0, array.length - 1);
			/*осуществляем проверку, осортированы ли элементы массива по возрастанию 
			 * c помощью метода isSorted реализованного в классе ArrayAlgorithms
			 */
			if(!ArrayAlgorithms.isSorted(array)) {
				System.err.println("Not sorted!");
				ArrayAlgorithms.print(array);
			}
		}
	}


}
