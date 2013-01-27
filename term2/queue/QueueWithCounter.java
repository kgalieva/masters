package term2.queue;

import java.util.NoSuchElementException;

/**
 * Реализация циклической очереди.
 */
public class QueueWithCounter implements Queue {

	// максимальный размер очереди
	private int maxSize;
	// массив для хранения элементов очереди
	private Object[] elements;
	// индекс первого элемента очереди
	// если не задать значение поля, то его значение будет равно нулю
	private int first;
	// индекс последнего элемента очереди
	private int last = -1;
	// количество элементов в очереди
	private int count;

	public QueueWithCounter(int maxSize) {
		this.maxSize = maxSize;
		elements = new Object[maxSize];
	}

	/**
	 * Метод offer() выполняет вставку элемента, только если очередь не заполнена.
	 * В противном случае он просто возвращает false.
	 * Если значение поля last уже достигло вершины массива elements, 
	 * то перед выполнением вставки оно должно вернуться к нижней границе. 
	 * Для этого полю last присваивается значение -1, чтобы при увеличении при вставке оно стало 
	 * равным 0(нижняя граница массива). 
	 * Во время вставки значение поля last сначала увеличивается на 1, а затем новый элемент записывается 
	 * в ячейку с индексом last.
	 * Количество элементов count увеличивается на 1.  
	 */
	@Override
	public boolean offer(Object e) {
		if(isFull()) {
			return false;
		}
		// циклический перенос
		if (last == maxSize - 1) {
			last = -1;
		}
		elements[++last] = e;
		count++;
		return true;
	}

	/**
	 * Метод poll() возвращает null, если очередь пуста. 
	 * Извлечение заключается в получении значения ячейки с индексом first и увеличении first.
	 * Если значение first при этом выходит за границу массива, оно возвращается  к 0.
	 * Значение количества элементов count уменьшается на 1. 
	 */
	@Override
	public Object poll() {
		if (isEmpty()) {
			return null;
		}
		Object value = elements[first];
		elements[first] = null;
		first = ++first%maxSize;
		count--;
		return value;
	}

	/**
	 * @return Возвращает значение ячейки с индексом first
	 */
	@Override
	public Object peek() {
		return elements[first];
	}

	/**
	 * @return Возвращает значение поля count
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Сравнивает count с 0
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Сравнивает count с maxSize
	 */
	@Override
	public boolean isFull() {
		return count == maxSize;
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
