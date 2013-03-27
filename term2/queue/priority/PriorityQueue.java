package term2.queue.priority;

public interface PriorityQueue<T> {
	
	/**
	 * Добавление элемента в очередь
	 * @param e - добавляемый элемент
	 */
   void offer(T e);
  
   /**
     * Возвращение минимального элемента очереди
     * @return минимальный элемент
     */
    T poll();
    
    /**
     * Возращает первый элемент очереди
     * @return первый элемент
     */
    T peek();
    
    /**
     * @return Размер очереди
     */
    int size();
    
    /**
     * @return true, если очередь пуста, false - в противном случае
     */
    boolean isEmpty();
   
    /**
     * @return true, если количество элементов в очереди равно максимальному,
     * false - в противном случае
     */
    boolean isFull();

    /**
     * Вывод очереди на экран
     */
	void printQueue();


}
