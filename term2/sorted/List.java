package term2.sorted;

import java.util.Comparator;
import java.util.Iterator;

public interface List {
	/**
	 * Метод получения значения элемента по индексу
	 */
	Object get(int i);

	/**
	 * Метод добавления элемента в начало списка
	 */
	boolean add(Object o);
	
	/**
	 * Метод добавления элемента на заданную позицию
	 */
	boolean add(Object o, int i);
	
	/**
	 * Метод добавления элемента в естественном порядке(Natural Order).
	 * Все элементы списка должны реализовывать интерфейс Comparable, 
	 * так чтобы их можно было сравнить друг с другом, используя метод compareTo().
	 * В противном случае будет брошено исключение ClassCastException. 
	 */
	boolean addSort(Object o);
	
	/**
	 * Метод добавления элемента в порядке заданном в Comparator.
	 * Все элементы списка должны быть сравнимы друг с другом с помощью Comparator.
	 */
	boolean addSort(Object o, Comparator c);

	/**
	 * Метод получения размера списка
	 */
	int size();

	/**
	 * Метод удаления элемента по индексу
	 */
	Object remove(int i);
	
	/**
	 * Метод проверки, является ли список пустым
	 * 
	 * @return true, если список пустой false - в противном случае
	 */
	boolean isEmpty();
	
	/**
	 * Возвращает итератор для перебора элементов списка
	 * @return
	 */
	Iterator iterator();
}
