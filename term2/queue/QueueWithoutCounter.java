package term2.queue;


/**
 * Реализация очереди без счетчика элементов.
 * 
 * <p>Поле count в классе QueueWithCounter слегка снижает эффективность методов offer() и poll(), 
 * которым приходится соответственно увеличивать и уменьшать эту переменную. На первый взгляд потери не велики, 
 * но при очень большом количестве операций вставки и удаления они могут повлиять на производительность.</p>
 * <p>По этой причине некоторые реализации очередей обходятся без счетчика элементов, 
 * а для проверки заполненности/отсутствия элементов и количества элементов в очереди используются значения полей first и last.</p>
 * <p>При таком подходе может возникнуть одна проблема: при заполненной очереди индексы first и last могут иметь те же значения,
 * что и при пустой очереди. Таким образом становится невозможным отличить заполненную очередь от пустой. Для решения этой проблемы
 * создается массив с количеством элементов, на единицу большим максимального количества элементов, которые в нем могут размещаться.    
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
		//массив на одну ячейку больше требуемого размера
		this.maxSize = maxSize + 1;
		elements = new Object[maxSize];
	}

	@Override
	public boolean offer(Object e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Object poll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object peek() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int size() {
		if (last >= first) {
			//непрерывная последовательность
			return last - first + 1;
		} else {
			//несвязная последовательность
			return (maxSize - first) + (last + 1);
		}
			
	}
	
	@Override
	public boolean isEmpty() {		
		return (last + 1 == first || last == first + maxSize - 1);
	}
	
	/**
	 * Рассмотреть 2 случая: для непрерывной и для несвязной последовательности
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

}
