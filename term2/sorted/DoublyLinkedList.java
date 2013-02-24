package term2.sorted;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Двусвязный список, где каждый элемент содержит ссылки на предыдущий и следующий элементы. 
 * Реализация java.util.LinkedList в openjdk 7
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/7-b147/java/util/LinkedList.java?av=f
 */
public class DoublyLinkedList<T> implements List<T> {
	
	//первый элемент списка
	private Node<T> first;
	 
	//последний элемент списка
	private Node<T> last;
	
	/* количество модификаций списка. Необходимо для отслеживания модификаций
	 * списка во время перебора итератором.*/
	private int modCount;
	
	// количество элементов в списке
	private int size;

	/**
	 * Класс элемента списка
	 */
	private static class Node<T> {
		//ссылка на предыдущий элемент списка
		Node<T> prev;
		//ссылка на следующий элемент списка
		Node<T> next;
		//данные текущего элемента
		T data;
		
		//конструктор класса Node<T> с тремя параметрами
		public Node(Node<T> prev, T data,Node<T> next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/*
	 * Итератор, позволяющий перебирать элементы списка в обоих направлениях
	 * http://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html
	 */
	private class ListItr implements ListIterator<T> {
		/* Последний элемент, который вернул итератор
		 * Если элемент был удален, то lastReturned = null
		 */
		private Node<T> lastReturned = null;
		//следующий элемент, который вернет метод next()
		private Node<T> next;
		//индекс следующего элемента, который вернет метод next()
		private int nextIndex;
		/* Запоминаем количество модификаций списка до начала перебора, 
		 * чтобы отслеживать конкурентные модификации
		 */
		private int expectedModCount = modCount;
		
		/*В качестве аргумента в конструктор передается индекс элемента, 
		с которого требуется начать перебор */
		ListItr(int index) {
			next = (index == size) ? null : getNode(index);
			nextIndex = index;
		}
		
		/**
		 * @return Возвращает true, если перебраны еще не все элементы 
		 * в направлении к концу списка
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
		 * @return Возвращает true, если перебраны еще не все элементы 
		 * в направлении к началу списка
		 */
		public boolean hasPrevious() {
			return nextIndex > 0;
		}

		/**
		 * Возвращает предыдущий элемент списка и перемещает курсор на одну
		 * позицию назад.
		 *
		 * @throws NoSuchElementException
		 *             если в текущей итерации перебраны все элементы в направлении к началу списка
		 */
		public T previous() {
			// проверяем, не было ли конкурентных модификаций
			checkForComodification();
			// если в текущей итерации перебраны все элементы в направлении к началу списка, кидаем исключение
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			//перемещаем ссылку next на одну позицию назад
			lastReturned = next = (next == null) ? last : next.prev;
			//и уменьшаем индекс следующего элемента, который должен вернуть метод next()
			nextIndex--;
			//возвращаем  данные элемента, который стоял перед next
			return lastReturned.data;
		}
		
		/**
		 * @return Возвращает индекс следующего элемента, который вернет метод next()
		 */
		public int nextIndex() {
			return nextIndex;
		}
		
		/**
		 * @return Возвращает индекс элемента, который вернет метод previous()
		 */
		public int previousIndex() {
			return nextIndex - 1;
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
		
			Node<T> lastNext = lastReturned.next;
			//удаляем нужный элемент с помощью метода remove класса DoublyLinkedList
			DoublyLinkedList.this.remove(lastReturned);
			
			if (next == lastReturned) {
				/*
				 * Если элементы next и lastReturned совпадали, 
				 * то смещает ссылку next на следующую позицию.
				 * Такое возможно, если перед удалением был вызван метод previous().
				 * Индекс nextIndex при этом останется неизменным, 
				 * т.к. количество элементов стоящих перед next останется неизменным.
				 */
				next = lastNext;
			} else {
				/*
				 * Индекс элемента next уменьшается на единицу, так как перед ним
				 * был удален один элемент
				 */
				nextIndex--;
			}
			lastReturned = null;
			/*
			 * lastReturned делаем равным null, чтобы метод remove() не сработал
			 * при повторном вызове
			 */
			lastReturned = null;
			/* увеличиваем счетчик подификаций списка, т.к. мы удалили из него
			 * элемент. Поле modCount увелисилось на единицу в методе remove класса DoublyLinkedList
			 */
			expectedModCount++;			
		}
				
		/**
		 * Меняет значение последнего элемента, который вернул итератор.
		 * который вернул итератор. Этот метод нельзя вызывать после вызовов методов 
		 * add() и remove() итератора.
		 */ 
		public void set(T e) {
			if (lastReturned == null)
				throw new IllegalStateException();
			checkForComodification();
			lastReturned.data = e;
		}
		
		/**
		 * Добавляет новый элемент перед элементом, который вернет метод next() 
		 * при следующем вызове, если такой элемент существует. 
		 */
		public void add(T e) {
			checkForComodification();
			lastReturned = null;
			DoublyLinkedList.this.add(e, nextIndex);
			nextIndex++;
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
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}
	}

	/**
	 * Метод получения значения элемента по индексу
	 */
	public T get(int i) {
		Node<T> x = getNode(i);
		return x == null ? null : x.data;
	}

	/**
	 * Метод поиска элемента списка с заданным индексом.
	 * @param i Индекс искомого элемента
	 * @return Найденый элемент списка, либо null, если элемент не был найден
	 * 
	 * Модификатор доступа private указывает на то, что данный метод доступен только в текущем классе.
	 * Это вспомогательный метод, мы не хотим, чтобы пользователям он был виден.
	 */
	private Node<T> getNode(int i) {
		if (i >= size) {
			return null;
		}
		//если индекс искомого элемента находится в первой половине массива
		if (i < (size >> 1)) {
			//ведем перебор с начала списка
			Node<T> x = first;
			for (int j = 0; j < i; j++) {
				//сдвигаемся на следующий элемент списка
				x = x.next;
			}
			return x;
		} else {
			//в противном случае ведем перебор с конца списка
			Node<T> x = last;
			for (int j = size - 1; j > i; j--) {
				//сдвигаемся на предыдущий элемент списка
				x = x.prev;
			}
			return x;
		}
	}
	
	/**
	 * Метод добавления элемента в начало списка
	 */
	public boolean addFirst(T o) {
		Node<T> newNode = new Node<>(null, o, first);
		if (first != null) {
			first.prev = newNode;
		} else {
			last = newNode;
		}
		first = newNode;
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
	 * Вспомогательный метод для добавления элемента после заданного
	 * @param current Элемент за которым нужно добавить новый элемент
	 * @param o Значение нового элемента
	 * @return true, если вставка прошла успешно
	 */
	private boolean addAfter(Node<T> current, T o) {
		Node<T> newNode = new Node<>(current, o, current.next);
		if (current.next == null) {
			last = newNode;
		}else {
			current.next.prev = newNode;
		}
		current.next = newNode;		
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
		Comparable<? super T> data = (Comparable<? super T>) o;
		if (size == 0 || data.compareTo(first.data) <= 0) {
			return addFirst(o);
		}
		Node<T> current = first;
		while (current.next != null && data.compareTo(current.next.data) > 0) {
			current = current.next;
		}
		return addAfter(current, o);
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
	 * Метод добавления элемента в конец списка
	 */
	public boolean addLast(T o) {
		Node<T> l = last;
		//создаем новый элемент
		Node<T> newNode = new Node<T>(l, o, null);
		//добавленный элемент будет последним в списке
		last = newNode;
		//проверяем, не был ли список пустым
		if (l == null) {
			//если список был пустой, то добавленный элемент будет первым
			first = newNode;
		} else {
			/*если список был не пустой, то тот элемент, 
			который был последним теперь должен содержать ссылку на добавленный элемент */ 
			l.next = newNode;
		}
		size++;
		modCount++;
		return true;
	}

	/**
	 * Метод удаления элемента по индексу
	 */
	public T remove(int i) {
		Node<T> x = getNode(i);
		if (x == null) {
			return null;
		}
		return remove(x);
	}
	
	/**
	 * Вспомогательный метод для удаления заданного элемента
	 */
	private T remove(Node<T> x) {
		T oldData = x.data;
		Node<T> prev = x.prev;
		Node<T> next = x.next;
		
		if (prev == null) {
			//если удаляемый элемент первый в списке, то first теперь должен ссылаться на второй элемент
			first = x.next;
		} else {
			//в противном случае тот элемент, который шел до x теперь должен ссылаться на следующий за x-ом элемент 
			prev.next = next;
			x.prev = null;
		}
		if (next == null) {
			//если удаляемый элемент последний в списке, то last теперь должен ссылаться на предпоследний элемент
			last = x.prev;
		} else {
			//в противном случае тот элемент, который шел после x теперь должен ссылаться на элемент идущий перед x
			next.prev = prev;
			x.next = null;
		}
		x.data = null;
		size--;
		modCount++;
		return oldData;
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
