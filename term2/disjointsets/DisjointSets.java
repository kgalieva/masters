package term2.disjointsets;

import java.util.Arrays;

/**
 * СД Непересекающиеся множества(disjoint sets) или Объединить-Найти(find-union).
 * Взвешенное объединение со сжатием пути.
 * Реализация на основе массива. Элементы могут представляются типа int начиная с 0
 *
 *  @author Mark Allen Weiss
 **/

public class DisjointSets {

	/**
	 * В каждой ячейке хранится значение родителя узла. В ячейках корневых
	 * элементов хранится высота дерева со знаком минус.
	 */
	private int[] array;

  /**
   *  @param numElements количество элементов. 
   *  Изначально каждый элемент лежит в отдельном множестве и является корневым элементом.
   *  Поэтому каждая ячека содержит значение -1, равное высоте дерева со знаком минус.
   **/
	public DisjointSets(int numElements) {
		array = new int[numElements];
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
	}

  /**
   *  Объединяет два множества в одно. Производится взвешенное объединение, т.е.
   *  дерево с меньшей высотой становится поддеревом дерева с большей высотой.
   *
   *  @param root1 корень первого множества
   *  @param root2 корень второго множества
   **/
	public void union(int root1, int root2) {
		if (array[root2] < array[root1]) {
			// меньшее дерево становится поддеревом большего
			array[root1] = root2;
		} else {
			if (array[root1] == array[root2]) {
				/*
				 * Если оба дерева имели одну и ту же высоту, то высота нового
				 * дерева будет на единицу больше. Не забываем, что веса
				 * хранятся со знаком минус.
				 */
				array[root1]--;
			}
			// меньшее дерево становится поддеревом большего
			array[root2] = root1;
		}
	}

  /**
   *  Возвращает предствителя(корень) множества, содержащего заданный элемент x.
   *  Во время поиска производится сжатие пути (родителем текущего элемента и 
   *  его предков становится корневой элемент)
   *
   *  @param x искомый элемент
   *  @return корень множества, содержащего элемент x
   **/
	public int find(int x) {
		if (array[x] < 0) {
			// если в ячейке лежит отрицательное значение, то это корень дерева
			return x;
		} else {
			/*
			 * Рекурсивно ищем корень дерева и одновременно сжимаем дерево.
			 * Родителем текущего элемента и его предков становится корневой
			 * элемент
			 */
			array[x] = find(array[x]);
			return array[x];
		}
	}
	
	@Override
	public String toString() {
		return "DisjointSets [array=" + Arrays.toString(array) + "]";
	}

	public static void main(String[] args) {
		int NumElements = 12;
		int NumInSameSet = 3;

		DisjointSets s = new DisjointSets(NumElements);
		int set1, set2;

		for (int k = 1; k < NumInSameSet; k *= 2) {
			for (int j = 0; j + k < NumElements; j += 2 * k) {
				set1 = s.find(j);
				set2 = s.find(j + k);
				s.union(set1, set2);
			}
		}

		for (int i = 0; i < NumElements; i++) {
			System.out.print(s.find(i) + "*");
			if (i % NumInSameSet == NumInSameSet - 1) {
				System.out.println();
			}
		}
		System.out.println();
	}
}