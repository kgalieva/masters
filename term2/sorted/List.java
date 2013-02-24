package term2.sorted;

import java.util.Comparator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T>{
	/**
	 * Метод получения значения элемента по индексу
	 */
	T get(int i);

	/**
	 * Метод добавления элемента в начало списка
	 */
	boolean addFirst(T o);
	
	/**
	 * Метод добавления элемента в конец списка
	 */
	boolean addLast(T o);
	
	/**
	 * Метод добавления элемента на заданную позицию
	 */
	boolean add(T o, int i);
	
	/**
	 * Метод добавления элемента в естественном порядке(Natural Order).
	 * Все элементы списка должны реализовывать интерфейс Comparable, 
	 * так чтобы их можно было сравнить друг с другом, используя метод compareTo().
	 * В противном случае будет брошено исключение ClassCastException. 
	 */
	boolean addSort(T o);
	
	/**
	 * Метод добавления элемента в порядке заданном в Comparator.
	 * Все элементы списка должны быть сравнимы друг с другом с помощью Comparator.
	 */
	boolean addSort(T o, Comparator<? super T> c);

	/**
	 * Метод удаления элемента по индексу
	 */
	T remove(int i);
	
	/**
	 * Метод получения размера списка
	 */
	int size();
	
	/**
	 * Метод проверки, является ли список пустым
	 * 
	 * @return true, если список пустой false - в противном случае
	 */
	boolean isEmpty();
	
	/**
	 * @return ListIterator - итератор, который позволяет перебирать элементы в обе стороны
	 */
	public ListIterator<T> listIterator(int index);
}
