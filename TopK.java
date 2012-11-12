import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

public class TopK {

	/**
	 * <h3>Поиск двух максимальных элементов массива.</h3>
	 * 
	 * @param array Массив, в котором осуществляется поиск максимальных элементов
	 * @return      Массив, содержащий 2 максимальных элемента исходного массива.
	 */
	public static int[] twoMaxElements(int[] array) {
		int[] result = { Integer.MIN_VALUE, Integer.MIN_VALUE };
		for (int i = 0; i < array.length; i++) {
			if (array[i] > result[0]) {
				result[1] = result[0];
				result[0] = array[i];
			} else if (array[i] > result[1]) {
				result[1] = array[i];
			}
		}
		return result;
	}

	/**
	 * <h3>Поиск k максимальных элементов массива вставкой.</h3>
	 * <p>Добавляем максимальные элементы в массив размера k по мере нахождения 
	 * (аналогично поиску двух максимальных элементов). </p>
	 * Сложность O(n*k)
	 * 
	 * @param array Массив, в котором осуществляется поиск максимальных элементов
	 * @param k     Количество максимальных элементов, которые требуется найти в
	 *              исходном массиве
	 * @return      Массив, содержащий k максимальных элементов исходного массива.
	 */
	public static int[] topKInsertion(int[] array, int k) {
		int[] result = new int[k];
		int j;
		for (int i = 0; i < k; i++) {
			result[i] = Integer.MIN_VALUE;
		}
		for (int i = 0; i < array.length; i++) {
			for (j = result.length - 2; (j >= 0) && (result[j] < array[i]); j--) {
				result[j + 1] = result[j];
			}
			if (j < result.length - 2) {
				result[j + 1] = array[i];

			} else if (result[result.length - 1] < array[i]) {
				result[result.length - 1] = array[i];
			}
		}
		return result;
	}

	/**
	 * <h3>Поиск k максимальных элементов массива вборкой</h3> 
	 * <p>Находим максимальный, удаляем его из исходного массива(заменяем на Integer.MIN_VALUE),
	 * повторяем k раз.</p>
	 * Сложность O(n*k)
	 * 
	 * @param array Массив, в котором осуществляется поиск максимальных элементов
	 * @param k     Количество максимальных элементов, которые требуется найти в
	 *              исходном массиве
	 * @return      Массив, содержащий k максимальных элементов исходного массива.
	 */
	public static int[] topKSelection(int[] array, int k) {
		int max;
		int max_index;
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			max = array[0];
			max_index = 0;
			for (int j = 1; j < array.length; j++) {
				if (array[j] > max) {
					max = array[j];
					max_index = j;
				}
			}
			array[max_index] = Integer.MIN_VALUE;
			result[i] = max;
		}
		return result;
	}

	/**
	 * <h3>Поиск k максимальных элементов массива cортировкой.</h3> 
	 * <p>Cортируем исходный массив, считываем первые k элементов. </p>
	 * Сложность O(n*log(n))
	 * 
	 * @param array Массив, в котором осуществляется поиск максимальных элементов
	 * @param k     Количество максимальных элементов, которые требуется найти в
	 *              исходном массиве
	 * @return      Массив, содержащий k максимальных элементов исходного массива.
	 */
	public static int[] topKSort(int[] array, int k) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 1; i < array.length; i++) {
				if (array[i - 1] < array[i]) {
					ArrayAlgorithms.swap(array, i, i - 1);
					sorted = false;
				}
			}
		}
		return ArrayAlgorithms.copyArray(array, 0, k - 1);
	}

	/**
	 * <h3>Алгоритм случайного поиска k максимальных элементов.</h3> 
	 * Сложность O(n)
	 * @see <a href="http://goo.gl/o0OUW">Описание алгоритма</a>
	 * @param array Массив, в котором осуществляется поиск максимальных элементов
	 * @param k   Количество максимальных элементов, которые требуется найти в
	 *            исходном массиве
	 * @return массив, содержащий k максимальных элементов исходного массива.
	 */
	public static int[] topKRandomized(int[] array, int k) {
		topKRandomized(array, 0, array.length - 1, k);
		return ArrayAlgorithms.copyArray(array, 0, k - 1);

	}
	/**
	 * Внутренний рекурсивный метод случайного поиска k максимальных элементов.
	 * @param array Массив, в котором осуществляется поиск максимальных элементов
	 * @param start Индекс начала области поиска
	 * @param end   Индекс конца области поиска
	 * @param k   	Количество максимальных элементов, которые требуется найти 
	 */
	private static void topKRandomized(int[] array, int start, int end, int k) {
		Random rand = new Random();
		// Генерируем случайный индекс элемента массива		
		int current = rand.nextInt(end - start + 1) + start;
		current = partitioning(array, current, start, end);		
		int elementsFound = current - start + 1;
		if ((k == elementsFound) || (k == elementsFound - 1)) {
			return;
		}
		if (k < elementsFound - 1) {
			topKRandomized(array, start, current - 1, k);
		} else {
			topKRandomized(array, current + 1, end, k - elementsFound);
		}
	}

	/**
	 * Фильтрация массива относительно заданного элемента
	 * 
	 * @see <a href="http://goo.gl/PELZV">Описание алгоритма</a>
	 * @param array Массив в котором осуществляется фильтрация относительно
	 *            случайного элемента
	 */
	private static int partitioning(int[] array, int current, int start, int end) {
		if ((array == null) || (array.length < 2)) {
			return current;
		}
		while (start < end) {
			// ищем элемент меньший элемент текущего в левой части массива
			while ((start < current) && (array[current] <= array[start])) {
				start++;
			}
			// ищем элемент больший элемент текущего в правой части массива
			while ((end > current) && (array[current] >= array[end])) {
				end--;
			}
			if (start < end) {
				// меняем местами i-ый и j-ый элементы
				ArrayAlgorithms.swap(array, start, end);
				// если текущий элемент участвовал в перестановке, обновляем его
				// индекс
				if (start == current) {
					current = end;
					start++;
				} else {
					if (current == end) {
						current = start;
						end--;
					}
				}
			}
		}
		return current;
	}

	public static void main(String[] args) {
		int[] array;
		int[] arrayCopy;
		//тестируем метод поиска 2х максимальных элементов
		for (int i = 21; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(21);
			arrayCopy = ArrayAlgorithms.copyArray(array);
			int[] result = TopK.twoMaxElements(array);
			int[] resultSort = TopK.topKSort(arrayCopy, 2);
			assertEquals(result.length, resultSort.length);
			if (result.length != resultSort.length) {
				System.err.printf("Wrong lenght: %d. Expected: %d.", 
						result.length, resultSort.length);
				ArrayAlgorithms.print(array);
			}
			if (!ArrayAlgorithms.equals(result, resultSort)) {
				System.err.printf("Wrong top k elements: %s. Expected: %s.", 
						Arrays.toString(result), Arrays.toString(resultSort));
			}			
		}
		
		//тестируем поиск k максимальных элементов методом вставки
		for (int i = 21; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(21);
			arrayCopy = ArrayAlgorithms.copyArray(array);
			int[] resultInsertion = TopK.topKInsertion(array, 20);
			int[] resultSort = TopK.topKSort(arrayCopy, 20);
			assertEquals(resultInsertion.length, resultSort.length);
			assertEquals(resultInsertion[0], resultSort[0]);
			assertTrue(ArrayAlgorithms.equals(resultInsertion, resultSort));
		}
		
		//тестируем поиск k максимальных элементов методом выборки
		for (int i = 21; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			arrayCopy = ArrayAlgorithms.copyArray(array);
			int[] resultInsertion = TopK.topKSelection(array, 20);
			int[] resultSort = TopK.topKSort(arrayCopy, 20);
			assertEquals(resultInsertion.length, resultSort.length);
			assertEquals(resultInsertion[0], resultSort[0]);
			assertTrue(ArrayAlgorithms.equals(resultInsertion, resultSort));
		}
		
		//тестируем поиск k максимальных элементов методом случайного поиска
		for (int i = 21; i < 100; i++) {
			array = ArrayAlgorithms.randomArray(i);
			arrayCopy = ArrayAlgorithms.copyArray(array);
			int[] resultInsertion = TopK.topKRandomized(array, 20);
			int[] resultSort = TopK.topKSort(arrayCopy, 20);
			assertEquals(resultInsertion.length, resultSort.length);
			assertTrue(Anagram.isAnagram(resultInsertion, resultSort));
		}

	}
}
