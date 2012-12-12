package exam.combinations;

public class CombinationsKTask {

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
		int[] weights = {33, 2, 12, 44, 56, 6};
		int n = weights.length;
		int k = n/2;
		int sum = 0;
		int d;
		for (int i = 0; i < weights.length; i++) {
			sum = sum + weights[i];
		}
		int diff = Integer.MAX_VALUE;
		int[] result = new int[k];
		int[] indices = new int[k];
		for (int i = 0; i < k; i++) {
			indices[i] = i;
		}
		while (indices != null) {
			/* проверяем, является ли текущая комбинация лучше предыдущих */
			d = 0;
			for (int i: indices) {
				d += weights[i];  
			}
			int currentDiff = Math.abs(sum - 2*d);
			if (currentDiff < diff) {
				for (int j = 0; j < result.length; j++) {	
					result[j] = indices[j];
				}				
				diff = currentDiff;			
			}			
			indices = subset(indices, n);
		}
		
		System.out.println("--- RESULT ---");
		print(result);
		System.out.printf("diff = %d", diff);
	}
}