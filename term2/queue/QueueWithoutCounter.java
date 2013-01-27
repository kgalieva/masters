package term2.queue;

import java.util.NoSuchElementException;

/**
 * Реализация очереди без счетчика элементов.
 * 
 * <p>
 * Поле count в классе QueueWithCounter слегка снижает эффективность методов
 * offer() и poll(), которым приходится соответственно увеличивать и уменьшать
 * эту переменную. На первый взгляд потери не велики, но при очень большом
 * количестве операций вставки и удаления они могут повлиять на
 * производительность.
 * </p>
 * <p>
 * По этой причине некоторые реализации очередей обходятся без счетчика
 * элементов, а для проверки заполненности/отсутствия элементов и количества
 * элементов в очереди используются значения полей first и last.
 * </p>
 * <p>
 * При таком подходе может возникнуть одна проблема: при заполненной очереди
 * индексы first и last могут иметь те же значения, что и при пустой очереди.
 * Таким образом становится невозможным отличить заполненную очередь от пустой.
 * Для решения этой проблемы создается массив с количеством элементов, на
 * единицу большим максимального количества элементов, которые в нем могут
 * размещаться.
 */
public class QueueWithoutCounter implements Queue {

	// максимальный размер очереди
	private int maxSize;
	// массив для хранения элементов очереди
	private Object[] elements;
	// индекс первого элемента очереди
	// если не задать значение поля, то его значение будет равно нулю
	private int first;
	// индекс последнего элемента очереди
	private int last = -1;

	public QueueWithoutCounter(int maxSize) {
		// массив на одну ячейку больше требуемого размера
		this.maxSize = ++maxSize;
		elements = new Object[maxSize];
	}

	/**
	 * Метод offer() выполняет вставку элемента, только если очередь не
	 * заполнена. В противном случае он просто возвращает false. Если значение
	 * поля last уже достигло вершины массива elements, то перед выполнением
	 * вставки оно должно вернуться к нижней границе. Для этого полю last
	 * присваивается значение -1, чтобы при увеличении при вставке оно стало
	 * равным 0(нижняя граница массива). Во время вставки значение поля last
	 * сначала увеличивается на 1, а затем новый элемент записывается в ячейку с
	 * индексом last.
	 */
	@Override
	public boolean offer(Object e) {
		if (isFull()) {
			return false;
		}
		// циклический перенос
		if (last == maxSize - 1) {
			last = -1;
		}
		elements[++last] = e;
		return true;
	}

	/**
	 * Метод poll() возвращает null, если очередь пуста. Извлечение заключается
	 * в получении значения ячейки с индексом first и увеличении first. Если
	 * значение first при этом выходит за границу массива, оно возвращается к 0.
	 */
	@Override
	public Object poll() {
		if (isEmpty()) {
			return null;
		}
		Object value = elements[first];
		elements[first] = null;
		first = ++first % maxSize;
		return value;
	}

	/**
	 * @return Возвращает значение ячейки с индексом first
	 */
	@Override
	public Object peek() {
		return elements[first];
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		if (last >= first) {
			// непрерывная последовательность
			return last - first + 1;
		} else {
			// несвязная последовательность
			return (maxSize - first) + (last + 1);
		}
	}

	@Override
	public boolean isEmpty() {
		return (last + 1 == first) || (first + maxSize - 1 == last) ;
	}

	/**
	 * Рассмотреть 2 случая: для непрерывной и для несвязной последовательности
	 */
	@Override
	public boolean isFull() {
		return (last + 2 == first) || (first + maxSize - 2 == last);
	}

	@Override
	public boolean add(Object e) {
		if (offer(e)) {
			return true;
		}
		throw new IllegalStateException();
	}

	@Override
	public Object remove() {
		Object result;
		if ((result = poll()) != null) {
			return result;
		}
		throw new NoSuchElementException();
	}

}
