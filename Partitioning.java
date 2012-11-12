import java.util.Random;

/**
 * Фильтрация массива относительно заданного элемента
 * @see <a href="http://goo.gl/PELZV">Описание алгоритма</a>
 */
public class Partitioning {

	/**
	 * Фильтрация массива относительно заданного элемента
	 * @see <a href="http://goo.gl/PELZV">Описание алгоритма</a>
	 * @param array  Массив в котором осуществляется фильтрация относительно случайного элемента
	 */
	public static void partitioning(int[] array) {
		if ((array == null) || (array.length < 2)) {
			return;
		}
		int start = 0;
		int end = array.length - 1;
		Random rand = new Random();
		//Генерируем случайный индекс элемента массива
		int current = rand.nextInt(array.length);
		while (start < end) {
			//ищем элемент больший элемент текущего в левой части массива 
			while ((start < current) && (array[current] >= array[start])) {
				start++;
			}
			//ищем элемент меньший элемент текущего в правой части массива
			while ((end > current) && (array[current] <= array[end])) {
				end--;
			}
			if (start < end) {
				//меняем местами i-ый и j-ый элементы
				ArrayAlgorithms.swap(array, start, end);	
				//если текущий элемент участвовал в перестановке, обновляем его индекс
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
	}

}