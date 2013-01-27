package term2.queue;

import java.util.NoSuchElementException;

/**
 * Релизация очереди на основе односвязного списка аналогично классу LinkedStack
 */
public class LinkedQueue implements Queue {
	/**
	 * В классе LinkedQueue содержатся 3 поля:
	 * ссылка на первый элемент, ссылка на последний элемент и количество элементов в списке
	 */
	private Node first;
	private Node last;
	private int size;
	
	 /** А также внутренний класс Node, описывающий элементы списка.
	 * В классе Node 2 поля: значение текущего элемента и ссылка на следующий элемент.
	 * 
	 * Такой список называют односвязным.
	 * Сравните с классом LinkedList, в нашей реализации у его элементов были ссылки на следующий и на предыдущий элементы,
	 * такой список назвают двусвязным.
	 */
	private class Node {
		Node next;
		Object value;
		
		public Node(Object value, Node next) {
			this.next = next;
			this.value = value;
		}
	}
	
	/**
	 * Метод offer создает новый элемент списка со значением, 
	 * переданным ему в качестве аргумента и ссылкой на следующий элемент равной null, 
	 * поскольку вставка происходит в конец списка. 
	 * Если список был пустым, то новый добавленный элемент будет являться не только последним, но и первым в списке.
	 * Если список не пустой, то элемент, который был последним, теперь станет предпоследним, 
	 * и его ссылка next теперь должна указывать на новый добавленный элемент.  
	 * Меняем ссылку на последний элемент, чтобы она указывала на только что добавленный элемент.
	 * И увеличиваем счетчик количества элементов в массиве.
	 * Данный метод всегда возвращает true
	 */
	@Override
	public boolean offer(Object e) {
		Node node = new Node(e, null);
		if (isEmpty()) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		size++;
		return true;
	}

	/**
	 * Метод poll() возвращает null, если очередь пуста. 
	 * Извлечение заключается в получении значения первого элемента и его удалении из списка.
	 * Ссылка first при этом переходит на второй элемент очереди. 
	 * Значение количества элементов уменьшается на 1.
	 * Если из очереди был удален последний элемент, 
	 * то ссылка на последний элемент last теперь должна ссылаться на null.
	 */
	@Override
	public Object poll() {
		if(isEmpty()) {
			return null;
		}
		Object value = first.value;
		Node temp = first;
		first = first.next;
		temp.next = null;
		temp.value = null;
		if (--size == 0){
			last = null;
		}		
		return value;
	}

	@Override
	public Object peek() {		
		return first.value;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean add(Object e) {
		if(offer(e)) {
			return true;			
		}
		throw new IllegalStateException();
	}

	@Override
	public Object remove() {
		Object result;
		if((result = poll()) != null){
			return result;
		}
		throw new NoSuchElementException();
	}

}
