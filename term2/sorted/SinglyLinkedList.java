package term2.sorted;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**  
 * Односвязный список
 */
public class SinglyLinkedList<T> implements List<T> {

	// первый элемент списка
	private Node<T> first;

	// количество элементов в списке
	private int size;

	/* количество модификаций списка. Необходимо для отслеживания модификаций
	 * списка во время перебора итератором.
	 */
	private int modCount;

	// Класс элемента списка
	private static class Node<T> {
		// ссылка на следующий элемент списка
		Node<T> next;
		// данные текущего элемента
		T data;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	/*
	 * Итератор для перебора элементов списка
	 */
	private class Itr implements Iterator<T> {
		
		/* Последний элемент, который вернул метод next. Если элемент был
		 * удален, то lastReturned = null
		 */
		private Node<T> lastReturned = null;
		// следующий элемент, который вернет метод next()
		private Node<T> next;
		// индекс следующего элемента, который вернет метод next()
		private int nextIndex;
		/* Запоминаем количество модификаций списка до начала перебора, 
		 * чтобы отслеживать конкурентные модификации
		 */
		private int expectedModCount = modCount;
		
		/*В качестве аргумента в конструктор передается индекс элемента, 
		с которого требуется начать перебор */
		Itr(int index) {			
			next = (index == size) ? null : getNode(index);
			nextIndex = index;
		}

		/**
		 * @return Возвращает true, если перебрал еще не все элементы
		 */
		public boolean hasNext() {
			return nextIndex < size;
		}

		/**
		 * @return Возвращает следующий элемент в направлении к концу списка
		 * @throws NoSuchElementException
		 *             если в текущей итерации перебраны все элементы
		 */
		public T next() {
			// проверяем, не было ли конкурентных модификаций
			checkForComodification();
			// если в текущей итерации перебраны все элементы, кидаем исключение
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			/*
			 * Возвращаем данные элемента next и сдвигаем ссылки next и lastReturned
			 */

			// lastReturned становится последним элементом, который мы вернули
			lastReturned = next;
			// меняем ссылку next на следующий элемент
			next = next.next;
			// увеличиваем индекс следующего элемента
			nextIndex++;
			// возвращаем данные элемента, на который ссылалось поле next
			return lastReturned.data;
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
			// проверяем, не было ли конкурентных модификаций
			checkForComodification();

			/*
			 * если метод next() еще не был вызван или если метод remove() уже
			 * был вызван после последнего вызова метода next()
			 */
			if (lastReturned == null) {
				throw new IllegalStateException();
			}
			//удаляем нужный элемент с помощью метода remove класса SinglyLinkedList
			SinglyLinkedList.this.remove(nextIndex - 1);
			/*
			 * Индекс элемента next уменьшается на единицу, так как перед ним
			 * был удален один элемент
			 */
			nextIndex--;

			/*
			 * lastReturned делаем равным null, чтобы метод remove() не сработал
			 * при повторном вызове
			 */
			lastReturned = null;
			/* увеличиваем счетчик подификаций списка, т.к. мы удалили из него
			 * элемент. Поле modCount увелисилось на единицу в методе remove класса SinglyLinkedList
			 */
			expectedModCount++;
		}

		/**
		 * Проверяем не производились ли внешние модификации списка во время перебора итератором
		 */
		final void checkForComodification() {
			/*
			 * Если во время перебора элементов итератором не производились внешние модификации списка, 
			 * то знаяения полей  modCount и expectedModCount будут совпадать
			 */
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

	/**
	 * Метод получения значения элемента по индексу
	 */
	public T get(int i) {
		return getNode(i).data;
	}

	/**
	 * Метод добавления элемента в начало списка
	 */
	public boolean addFirst(T o) {
		first = new Node<>(o, first);
		size++;
		modCount++;
		return true;
	}

	/**
	 * Метод добавления элемента на заданную позицию
	 */
	public boolean add(T o, int i) {
		if (i < 0) {
			throw new IndexOutOfBoundsException("index = " + i);
		}
		if (size == 0 || i == 0) {
			return addFirst(o);
		}
		Node<T> current = first;
		for (int j = 1; j < i && j < size; j++) {
			current = current.next;
		}
		return addAfter(current, o);		
	}
	
	/**
	 * Метод добавления элемента в конец списка
	 */
	public boolean addLast(T o) {
		if (isEmpty()) {
			first = new Node<>(o, null);
			size++;
			modCount++;
			return true;
		}
		Node<T> current = first;
		while (current.next != null){
			current = current.next;
		}
		current.next = new Node<>(o, null);
		size++;
		modCount++;
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
		Comparable<? super T> data = (Comparable<? super T>)o;
		if (size == 0 || data.compareTo(first.data) <= 0) {
			first = new Node<>(o, first);
			size++;
			modCount++;
			return true;
		}
		Node<T> current = first;
		while (current.next != null && data.compareTo(current.next.data) > 0) {
			current = current.next;
		}
		return addAfter(current, o);
	}
	
	/**
	 * Вспомогательный метод для добавления элемента после заданного
	 * @param current Элемент за которым нужно добавить новый элемент
	 * @param o Значение нового элемента
	 * @return true, если вставка прошла успешно
	 */
	private boolean addAfter(Node<T> current, T o) {
		Node<T> newNode = new Node<>(o, current.next);
		current.next = newNode;
		size++;
		modCount++;
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
	 * Метод удаления элемента по индексу
	 */
	public T remove(int i) {
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException("index = " + i);
		}
		if (i == 0) {
			Node<T> removed = first;
			T oldData = removed.data;
			first = first.next;
			removed.next = null;
			removed.data = null;
			size--;
			modCount++;
			return oldData;
		}
		Node<T> prev = getNode(i - 1);
		Node<T> removed = prev.next;
		T oldData = removed.data;
		prev = removed.next;
		removed.next = null;
		removed.data = null;
		size--;
		modCount++;
		return oldData;
	}
	
	/*Вспомогательный метод для получения элемента списка по индексу
	 * */
	private Node<T> getNode(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		Node<T> current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	/**
	 * @return Итератор для перебора элементов списка
	 */
	public Iterator<T> iterator() {
		return new Itr(0);
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}
}
