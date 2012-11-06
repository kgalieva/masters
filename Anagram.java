
public class Anagram {
	/**
	 * */	
	static boolean isAnagram(char[] firstArray, char[] secondArray) {
		if ((firstArray == null) || (secondArray == null) || (firstArray.length != secondArray.length)) {
			return false;
		}
		/* Флаг, указывающий отсортирован массив или нет.
		 * true - массив не отсортирован 
		 * false - массив отсортирован
		 */		
		boolean unsorted = true;
		//Временная переменная, необходимая для того, чтобы менять 2 значения местами.		
		char temp;
		/* Сортируем первый массив методом пузырьковой сортировки. 
		 * Вместо пузырьковой сортировки можно использовать любой другой алгоритм сортировки.
		 * Подробнее о сортировках смотрите класс ArraySorting
		 */
		while (unsorted) {
			unsorted = false;
			for(int i = 1; i < firstArray.length; i++) {
				if (firstArray[i - 1] > firstArray[i]) {
					temp = firstArray[i];
					firstArray[i] = firstArray[i - 1];
					firstArray[i - 1] = temp;
					unsorted = true;
				}
				if(secondArray[i - 1] > secondArray[i]){
					temp = secondArray[i];
					secondArray[i] = secondArray[i - 1];
					secondArray[i - 1] = temp;
					unsorted = true;
				}
			}
		}
		/*
		 * Сравниваем отсортированные массивы. 
		 * Если находим несовпадение, делаем вывод что массивы не являются анаграммами.
		 */
		for (int i = 0; i < firstArray.length; ++i) {
			if (firstArray[i] != secondArray[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Пример того, как можно использовать реализованные ранее методы,
	 * для решения задачи с анаграммами.
	 * @see ArrayAlgorithms#equals(int[], int[])
	 * @see ArraySorting#sort(int[])
	 * */	
	static boolean isAnagram(int[] firstArray, int[] secondArray) {
		if ((firstArray == null) || (secondArray == null) || (firstArray.length != secondArray.length)) {
			return false;
		}
		ArraySorting.quickSort(firstArray, 0, firstArray.length - 1);
		ArraySorting.quickSort(secondArray, 0, secondArray.length -1);
		return ArrayAlgorithms.equals(firstArray, secondArray);
	}

	public static void main(String[] args) {		
		int[] firstArray = {1,1,2};
		int[] secondArray = {2,1,2};
		ArrayAlgorithms.print(firstArray);
		ArrayAlgorithms.print(secondArray);		
		System.out.printf("is anagrams: %b %n", isAnagram(firstArray, secondArray));	
		secondArray = new int[]{2,1,1};
		ArrayAlgorithms.print(firstArray);
		ArrayAlgorithms.print(secondArray);		
		System.out.printf("is anagrams: %b %n", isAnagram(firstArray, secondArray));
	}

}
