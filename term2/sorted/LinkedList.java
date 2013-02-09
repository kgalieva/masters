package term2.sorted;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* TODO добавьте реализацию интерфейса Iterable, чтобы список можно было перебирать 
 * с помощью цикла  foreach
 */
public class LinkedList implements List {

	// первый элемент списка
	private Node first;
	
	// количество элементов в списке
	private int size;
	/*
	 * количество модификаций списка. Необходимо для отслеживания модификаций
	 * списка во время перебора итератором.
	 */
	private int modCount;

	// Класс элемента списка
	private static class Node {
		// ссылка на следующий элемент списка
		Node next;
		// данные текущего элемента
		Object data;

		public Node(Object data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	//Итератор для перебора элементов списка
	private class Itr implements Iterator {

		/* Индекс элемента, который будет возвращен при вызове метода next() */
		int cursor = 0;

		/*
		 * Индекс элемента, который был возвращен в результате последнего вызова
		 * метода next() или previous(). Сбрасывается до -1, если элемент был
		 * удален с помощью метода remove()
		 */
		int lastRet = -1;

		/*
		 * запоминаем количество модификаций списка, если значение поля
		 * expectedModCount не будет совпадать с modCount, то итератор обнаружит
		 * модификации списка во врема его перебора
		 */
		int expectedModCount = modCount;

		public boolean hasNext() {
			return cursor != size();
		}

		public Object next() {
			checkForComodification();
			try {
				int i = cursor;
				Object next = get(i);
				lastRet = i;
				cursor = i + 1;
				return next;
			} catch (IndexOutOfBoundsException e) {
				checkForComodification();
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			checkForComodification();

			try {
				// вызов метода внешнего класса
				LinkedList.this.remove(lastRet);
				// если удаляем предыдущий элемент, надо сместить курсор на 1
				// позицию назад
				if (lastRet < cursor) {
					cursor--;
				}
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException();
			}
		}

		final void checkForComodification() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
	}
	/*Итератор, позволяющий перебирать элементы списка в обоих направлениях
	 * http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html
	 * */
	private class ListItr extends Itr implements ListIterator {
		ListItr(int index) {
			cursor = index;
		}

		public boolean hasPrevious() {
			// TODO
			return false;
		}

		public Object previous() {
			// TODO
			return null;
		}

		public int nextIndex() {
			// TODO
			return -1;
		}

		public int previousIndex() {
			// TODO
			return -1;
		}

		public void set(Object e) {
			// TODO
		}

		public void add(Object e) {
			// TODO
		}

	}

	/**
	 * Метод получения значения элемента по индексу
	 */
	public Object get(int i) {
		return getNode(i).data;
	}

	/**
	 * Метод добавления элемента в начало списка
	 */
	public boolean add(Object o) {
		first = new Node(o, first);
		size++;
		modCount++;
		return true;
	}

	/**
	 * Метод добавления элемента на заданную позицию
	 */
	public boolean add(Object o, int i) {
		// TODO контрольная
		return false;
	}

	/**
	 * Метод добавления элемента в естественном порядке(Natural Order). Все
	 * элементы списка должны реализовывать интерфейс Comparable, так чтобы их
	 * можно было сравнить друг с другом, используя метод compareTo(). В
	 * противном случае будет брошено исключение ClassCastException.
	 */
	public boolean addSort(Object o) {
		Comparable data = (Comparable) o;
		if (size == 0 || data.compareTo(first.data) <= 0) {
			first = new Node(o, first);
			size++;
			modCount++;
			return true;
		}
		Node current = first;
		while (current.next != null && data.compareTo(current.next.data) > 0) {
			current = current.next;
		}
		Node newNode = new Node(o, current.next);
		current.next = newNode;
		size++;
		modCount++;
		return true;
	}

	/**
	 * Метод добавления элемента в порядке заданном в Comparator. Все элементы
	 * списка должны быть сравнимы друг с другом с помощью Comparator.
	 */
	public boolean addSort(Object o, Comparator c) {
		// TODO ДЗ 26
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object remove(int i) {
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException("index = " + i);
		}
		if (i == 0) {
			Node removed = first;
			Object oldData = removed.data;
			first = first.next;
			removed.next = null;
			removed.data = null;
			size--;
			modCount++;
			return oldData;
		}
		Node prev = getNode(i - 1);
		Node removed = prev.next;
		Object oldData = removed.data;
		prev = removed.next;
		removed.next = null;
		removed.data = null;
		size--;
		modCount++;
		return oldData;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		Node current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public Iterator iterator() {
		return new Itr();
	}

}
