package term2.tree.tree234;

import java.util.Arrays;

public class Node<K, V> {
	/*Константа, хранящая количество потомков. 
	 * Деревья 2-3-4 могут содержать до четырех потомков.
	 */
	public static final int ORDER = 4;	
	//Ссылки на потомков узла(их максимальное количество равно четырем)
	@SuppressWarnings("unchecked")
	private Node[] childArray = new Node[ORDER];
	//Элементы DataItem хранящиеся в узле. В каждом узле по 3 ячейки.
	@SuppressWarnings("unchecked")
	private Entry[] itemArray = new Entry[ORDER - 1];
	//Текущее количество элементов данных в узле
	private int numItems;
	//Ссылка на родительский узел
	private Node<K, V> parent;
	
	static class Entry<K, V> {
		private Comparable<? super K> key;
		private V value;
		
		public Entry(Comparable<? super K> key, V value) {
			this.key = key;
			this.value = value;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Comparable<? super K> getKey() {
			return key;
		}

		@Override
		public String toString() {
			return "[" + value + "]";
		}			
		
	}

	/**
	 * Связывание узла с потомком
	 * @param childNum  Номер потомка 
	 * @param child     Узел-потомок
	 */
	public void connectChild(int childNum, Node<K, V> child) {
		childArray[childNum] = child;
		if(child != null) {
			child.parent = this;
		}
	}
	
	/**
	 * Метод отсоединяет потомка от узла и возвращает его 
	 * @param childNum 	Номер потомка
	 * @return 			Удаленный узел-потомок
	 */
	Node<K, V> disconnectChild(int childNum) {
		Node<K, V> tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}
	
	/**
	 * Метод проверяет, является ли данный узел листовым
	 * @return true, если узел является листовым,
	 * false - в противном случае
	 */
	boolean isLeaf() {
		return childArray[0] == null;
	}
	
	/**
	 * Получение данных из текущего узла по индексу
	 * @param index Индекс
	 * @return Данных из текущего узла по индексу
	 */
	Entry<K, V> getItem(int index) {
		return itemArray[index];
	}
	
	/**
	 * 
	 * @return
	 */
	boolean isFull() {
		return numItems == ORDER - 1;
	}
	
	/**
	 * Поиск элемента по значению в пределах узла
	 * @param key   Значение ключа искомого элемента
	 * @return 		Индекс найденного элемента. 
	 * Если элемент не найден, взвращается -1. 
	 */
	int findItem(Comparable<? super K> key) {
		for(int i = 0; i < numItems; i++) {
			if (itemArray[i].getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Добавление нового элемента. Предполагается, что узел не полный.
	 * @param newItem 	Новый элемент для вставки
	 * @return			Индекс добавленного элемента
	 */
	@SuppressWarnings("unchecked")
	int insertItem(Comparable<? super K> key, V value) {
		int i = numItems - 1;
		for (; i >= 0; i--) {
			if(key.compareTo((K)itemArray[i].getKey()) > 0) {
				break;				
			} 
			itemArray[i + 1] = itemArray[i];
		}
		itemArray[i + 1] = new Entry<>(key, value);
		numItems++;
		return i + 1;
	}
	
	/**
	 * Удаление наибольшего элемента
	 * @return Возвращает удаленный элемент
	 */
	Node.Entry<K, V> removeItem() {		
		Node.Entry<K, V> removed = itemArray[--numItems];
		itemArray[numItems] = null;
		return removed;
	}

	int getNumItems() {
		return numItems;
	}

	Node<K, V> getChild(int index) {		
		return childArray[index];
	}

	Node<K, V> getParent() {
		return parent;
	}	
	
	void setParent(Node<K, V> parent) {
		this.parent = parent;
	}	
	
	@Override
	public String toString() {
		return Arrays.toString(itemArray);
	}

}
