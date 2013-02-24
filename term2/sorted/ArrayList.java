package term2.sorted;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Список на основе массива
 */
public class ArrayList<T> implements List<T> {
	/**
	 * Массив, в котором хранятся значения элементов списка
	 */
	private Object[] elements;

	// количество элементов в списке(не размер массива)
	private int size;

	/*
	 * количество модификаций списка. Необходимо для отслеживания модификаций
	 * списка во время перебора итератором.
	 */
	private int modCount;

	/*
	 * Итератор, позволяющий перебирать элементы списка в обоих направлениях
	 * http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html
	 */
	private class ListItr implements ListIterator<T> {
		
		//индекс следующего элемента, который вернет метод next()
		int cursor; 
		//индекс последнего элемента, который вернул итератор. Если элемент был удален, то lastRet = -1
		int lastRet = -1; 
		/* Запоминаем количество модификаций списка до начала перебора, 
		 * чтобы отслеживать конкурентные модификации
		 */
		int expectedModCount = modCount;
		
		/*В качестве аргумента в конструктор передается индекс элемента, 
		с которого требуется начать перебор */
		ListItr(int index) {
			cursor = index;
		}

		/**
		 * @return Возвращает true, если перебраны еще не все элементы 
		 * в направлении к концу списка
		 */
		public boolean hasNext() {
			return cursor != size;
		}
		
		/**
		 * @return Возвращает следующий элемент в направлении к концу списка
		 * @throws NoSuchElementException
		 *             если в текущей итерации перебраны все элементы
		 */
		@SuppressWarnings("unchecked")
		public T next() {
			// проверяем, не было ли конкурентных модификаций
			checkForComodification();
			int i = cursor;
			// если в текущей итерации перебраны все элементы, кидаем исключение
			if (i >= size) {
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elements;
			cursor = i + 1;
			return (T)elementData[lastRet = i];
		}

		/**
		 * Удаляет из списка последний элемент, который вернул итератор. Этот
		 * метод можно вызывать только 1 раз для каждого вызова next(). Если
		 * список модифицировался в процессе перебора итератором любым другим
		 * методом, кроме этого, то будет брошено исключение
		 * ConcurrentModificationException.
		 * 
		 * @throws IllegalStateException
		 *             если метод next() еще не был вызван или если метод
		 *             remove() уже был вызван после последнего вызова метода
		 *             next()
		 */
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			// проверяем, не было ли конкурентных модификаций
			checkForComodification();

			try {
				//удаляем нужный элемент с помощью метода remove класса ArrayList
				ArrayList.this.remove(lastRet);
				/* Если индекс удаляемого элемента меньше cursor, то 
				 * cursor уменьшается на 1
				 */
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}
		
		/**
		 * Проверяем не производились ли внешние модификации списка во время перебора итератором
		 */
		final void checkForComodification() {
			/*
			 * Если во время перебора элементов итератором не производились внешние модификации списка, 
			 * то знаяения полей  modCount и expectedModCount будут совпадать
			 */
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}	

		/**
		 * @return Возвращает true, если перебраны еще не все элементы 
		 * в направлении к началу списка
		 */
		public boolean hasPrevious() {
			return cursor != 0;
		}

		/**
		 * @return Возвращает true, если перебраны еще не все элементы 
		 * в направлении к концу списка
		 */
		public int nextIndex() {
			return cursor;
		}

		/**
		 * @return Возвращает индекс элемента, который вернет метод previous()
		 */
		public int previousIndex() {
			return cursor - 1;
		}
		
		/**
		 * Возвращает предыдущий элемент списка и перемещает курсор на одну позицию назад.
		 *
		 * @throws NoSuchElementException
		 *             если в текущей итерации перебраны все элементы в направлении к началу списка
		 */
		@SuppressWarnings("unchecked")
		public T previous() {
			checkForComodification();
			int i = cursor - 1;
			if (i < 0) {
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elements;
			cursor = i;
			return (T)elementData[lastRet = i];
		}

		/**
		 * Меняет значение последнего элемента, который вернул итератор.
		 * который вернул итератор. Этот метод нельзя вызывать после вызовов методов 
		 * add() и remove() итератора.
		 */ 
		public void set(Object e) {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			checkForComodification();
			try {
				ArrayList.this.elements[lastRet] = e;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		/**
		 * Добавляет новый элемент перед элементом, который вернет метод next() 
		 * при следующем вызове, если такой элемент существует. 
		 */
		public void add(Object e) {
			checkForComodification();
			try {
				int i = cursor;
				ArrayList.this.add(e, i);
				cursor = i + 1;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}
	}

	/**
	 * Конструктор без параметров или конструктор по умолчанию. По умолчанию
	 * создаем массив размера 5.
	 */
	public ArrayList() {
		this(5);
	}

	/**
	 * Конструктор с параметром
	 * 
	 * @param size
	 *            начальный размер массива elements
	 */
	public ArrayList(int size) {
		// создаем массив заданного размера
		elements = new Object[size];
	}

	/**
	 * Метод добавления элемента в конец списка
	 */
	public boolean addLast(Object o) {
		ensureCapacity(size + 1);
		elements[size++] = o;
		modCount++;
		return true;
	}
	
	/**
	 * Метод добавления элемента в начало списка
	 */
	public boolean addFirst(Object o) {
		return add(o, 0);
	}

	/**
	 * Метод добавления элемента на заданную позицию
	 */
	public boolean add(Object o, int i) {
		ensureCapacity(size + 1);
		// сдвигаем элемены на 1 позицию назад
		for (int j = size; j > i; j--) {
			elements[j] = elements[j - 1];
		}
		// заносим нужное значение в ячейку i
		elements[i] = o;
		modCount++;
		size++;
		return true;
	}

	/**
	 * Метод добавления элемента в естественном порядке(Natural Order). Все
	 * элементы списка должны реализовывать интерфейс Comparable, так чтобы их
	 * можно было сравнить друг с другом, используя метод compareTo(). В
	 * противном случае будет брошено исключение ClassCastException.
	 */
	@SuppressWarnings("unchecked")
	public boolean addSort(T o) {
		ensureCapacity(size + 1);
		Comparable<? super T> value = (Comparable<? super T>) o;
		int j = size - 1;
		// сдвигаем элемены на 1 позицию назад пока не дойдем до начала массива,
		// либо до меньшего элемента
		for (; j >= 0 && value.compareTo((T)elements[j]) < 0; j--) {
			elements[j + 1] = elements[j];
		}
		// заносим нужное значение в ячейку j+1
		elements[j + 1] = o;
		modCount++;
		size++;
		return true;
	}

	/**
	 * Метод добавления элемента в порядке заданном в Comparator. Все элементы
	 * списка должны быть сравнимы друг с другом с помощью Comparator.
	 */
	public boolean addSort(T o, Comparator<? super T> c) {
		// TODO ДЗ 26
		return false;
	}

	/**
	 * Метод получения значения элемента по индексу
	 */
	@SuppressWarnings("unchecked")
	public T get(int i) {
		if (i >= size) {
			return null;
		}
		return (T)elements[i];
	}

	/**
	 * Метод удаления элемента по индексу
	 */
	@SuppressWarnings("unchecked")
	public T remove(int i) {
		if (i >= size) {
			return null;
		}
		T oldValue = (T)elements[i];
		// сдвигаем элемены на 1 позицию к началу массива
		for (int j = i + 1; j < size; j++) {
			elements[j - 1] = elements[j];
		}
		// обнуляем последний элемент и уменьшаем размер списка
		elements[--size] = null;
		modCount++;
		return oldValue;
	}

	/**
	 * Метод проверяет поместится ли в массив elements size элементов При
	 * необходимости увеличивает массив elements
	 * 
	 * @param size
	 *            количество элементов, которое должно входить в массив elements
	 */
	private void ensureCapacity(int size) {
		if (size <= elements.length) {
			return;
		}
		/*
		 * создаем новый массив размера в 2 раза больше, чем количество
		 * элементов, которое мы хотим там разместить
		 */
		Object[] newElements = new Object[2 * size];
		// копируем в него элементы из старого массива
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = elements[i];
		}
		// присваеваем новый массив полю elements
		elements = newElements;
	}

	/**
	 * @return Итератор для перебора элементов списка
	 */
	public Iterator<T> iterator() {
		return new ListItr(0);
	}
	
	/**
	 * @return ListIterator - итератор, который позволяет перебирать элементы в обе стороны
	 */
	public ListIterator<T> listIterator(int index) {		
		return new ListItr(index);
	}	

	public int size() {
		return size;
	}	

	public boolean isEmpty() {
		return size == 0;
	}
}
