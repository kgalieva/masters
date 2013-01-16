package exam.combinations;

public class CombinationsK {

	/**
	 * Donald Knuth. The Art of Computer Programming. Алгоритм
	 * L(Лексикографический алгоритм) страница 4
	 * http://cs.utsa.edu/~wagner/knuth/fasc3a.pdf
	 * 
	 * @param indices текущее сочетание индексов
	 * @param n       количество элементов из которых составлятся сочетания
	 * @return        метод возвращает следующее сочетание либо null, если перебраны
	 *         все сочетания
	 */
	public static int[] subset(int[] indices, int n) {

		int k = indices.length;
		
		int i = 0;
		/*
		 * идем с конца массива и ищем индекс элемента, который еще не принял
		 * максимальное для своей позиции значение
		 */
		for (i = k - 1; i >= 0; i--) {
			if (indices[i] != (i + n - k)) {
				break;
			}
		}
		/*
		 * если у всех элементов максимальные для своих позиций значения, то
		 * перебор закончен
		 */
		if (i == -1) {
			return null;
		}
		/*
		 * В противном случае увеличиваем элемент на котором остановились в
		 * первом цикле
		 */
		indices[i] += 1;

		/* все следующие индексы меняем на минимальные возможные для них значения */
		for (int j = i + 1; j < k; j++) {
			indices[j] = indices[j - 1] + 1;
		}
		return indices;
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
		int n = 6;
		int k = 2;
		int[] indices = new int[k];
		for (int i = 0; i < k; i++) {
			indices[i] = i;
		}
		while (indices != null) {
			print(indices);
			indices = subset(indices, n);
		}
	}
}