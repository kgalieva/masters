package term2.queue;

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
	 * Метод poll() предполагает, что очередь не заполнена.
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
	 * Метод poll() предполагает, что очередь не пуста. 
	 * Извлечение заключается в получении значения ячейки с индексом first и увеличении first.
	 * Если значение first при этом выходит за границу массива, оно возвращается  к 0.
	 * Значение количества элементов count уменьшается на 1. 
	 */
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Возвращает значение ячейки с индексом first
	 */
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Возвращает значение поля count
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Сравнивает count с 0
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Сравнивает count с maxSize
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

}
