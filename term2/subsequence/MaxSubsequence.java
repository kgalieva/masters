package term2.subsequence;

public class MaxSubsequence {

	/**
	 * Сканирующий алгоритм.
	 * Сложность O(n)
	 * 
	 * @param x последовательность
	 * @return возвращает сумму максимальной подпоследовательности
	 */
	public static Integer scan(Integer[] x) {
		int maxSoFar = 0;
		int maxEnding = 0;
		for (int i = 0; i < x.length; i++) {
			maxEnding = Math.max(maxEnding + x[i], 0);
			maxSoFar = Math.max(maxSoFar, maxEnding);
		}
		return maxSoFar;
	}

	/**
	 * Алгоритм "Разделяй и властвуй".
	 * Сложность O(n*log(n))
	 * 
	 * @param x последовательность
	 * @return возвращает сумму максимальной подпоследовательности
	 */
	public static Integer devideAndConquer(Integer[] x) {
		return devideAndConquer(0, x.length - 1, x);
	}

	private static Integer devideAndConquer(int start, int end, Integer[] x) {
		// пустой массив
		if (start > end) {
			return 0;
		}
		// один элемент
		if (start == end) {
			return Math.max(0, x[start]);
		}
		// находим середину для деления задачи на подзадачи
		int mid = (start + end) >>> 1;
		int sum = 0;
		int leftMax = 0;
		/*
		 * рассматирваем случай, когда две подпоследовательности имеют общую
		 * границу(mid), а следовательно их максимальные суммы можно сложить и
		 * получить максимальную сумму последовательности от start до end
		 */
		// идем от середины(от пересечения подпоследовательностей)
		for (int i = mid; i >= start; i--) {
			sum += x[i];
			// сохраняем максимальную положительную сумму, которую удалось найти
			// на текущий момент
			leftMax = Math.max(sum, leftMax);
		}
		// аналогично вычисляем маскимальную сумму для правой половины
		// подпоследовательности
		sum = 0;
		int rightMax = 0;
		for (int i = mid + 1; i <= end; i++) {
			sum += x[i];
			rightMax = Math.max(sum, rightMax);
		}
		/*
		 * рекурсивно находим подпоследовательности с максимальной суммой в
		 * правой и левой части
		 */
		int leftPartMax = devideAndConquer(start, mid, x);
		int rightPartMax = devideAndConquer(mid + 1, end, x);
		/* сравниваем найденные суммы */
		return Math.max(rightMax + leftMax, Math.max(leftPartMax, rightPartMax));
	}

	/**
	 * Простой алгоритм, использующий полный перебор. 
	 * Сложность O(n^3)
	 * 
	 * @param x последовательность
	 * @return возвращает сумму максимальной подпоследовательности
	 */
	public static Integer simple(Integer[] x) {
		int sum;
		int maxSum = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = i; j < x.length; j++) {
				sum = 0;
				for (int k = i; k <= j; k++) {
					sum += x[k];
				}
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
	}

	/**
	 * Модифицированный алгоритм полного перебора. 
	 * Сложность O(n^2)
	 * 
	 * @param x последовательность
	 * @return возвращает сумму максимальной подпоследовательности
	 */
	public static Integer quadratic(Integer[] x) {
		int sum;
		int maxSum = 0;
		for (int i = 0; i < x.length; i++) {
			sum = 0;
			for (int j = i; j < x.length; j++) {
				/* сумму элементов от i до j легко вычислить зная сумму элементов от i до j-1 */
				sum += x[j];
				maxSum = Math.max(maxSum, sum);
			}
		}
		return maxSum;
	}

}
