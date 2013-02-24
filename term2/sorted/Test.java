package term2.sorted;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.ListIterator;

public class Test {

	public static Employee[] employees = {
			new Employee("Jacob", new BigDecimal(123)),
			new Employee("Sophia", new BigDecimal(345)),
			new Employee("Daniel", new BigDecimal(134)),
			new Employee("William", new BigDecimal(565)),
			new Employee("Emily", new BigDecimal(133)) };

	public static void checkList(List<Employee> list) {

		System.out.println("  -----  " + list.getClass().getSimpleName()
				+ "  -----  ");
		// заполняем список
		for (Employee e : employees) {
			list.addSort(e);
		}
		// создаем итератор с помощью метода iterator()
		Iterator<Employee> iter = list.iterator();
		// перебираем элементы с помощью итератора
		while (iter.hasNext()) {
			System.out.println(iter.next());
			/*
			 * вызов list.remove(1); вызовет исключение
			 * ConcurrentModificationException при повторном вызове метода
			 * next(). Единственный возможный способ модификации списка во время
			 * перебора - использование методов самого итератора. Например,
			 * iter.remove();
			 */
			iter.remove();
		}
		System.out.println("is empty: " + list.isEmpty());

		// заполняем список
		for (Employee e : employees) {
			list.addSort(e);
		}

		/*
		 * Перебор списка в цикле foreach оступен благодаря тому, что List
		 * реализует интерфейс Iterable
		 */
		for (Object e : list) {
			System.out.println(e);
		}

		try {
			/*
			 * Пример использования ListIterator
			 */
			ListIterator<Employee> listIter = list.listIterator(0);
			System.out.println(" --- forward --- ");
			// перебор элементов списка в прямом порядке
			while (listIter.hasNext()) {
				System.out.println(listIter.next());
				/*
				 * Во время перебора итератором в список можно добавлять новые
				 * элементы, но только используя метод add() самого
				 * ListIterator.
				 */
				listIter.add(new Employee("Tom", new BigDecimal(456)));
			}
			System.out.println(" --- backwards --- ");
			// перебор элементов списка в обратном порядке
			while (listIter.hasPrevious()) {
				System.out.println(listIter.previous());
				//можно менять значение последнего элемента, который вернул итератор с помощью метода set класса ListIterator
				listIter.set(new Employee("Alex", new BigDecimal(261)));
			}
			System.out.println("is empty: " + list.isEmpty());
		} catch (UnsupportedOperationException e) {
			System.out.println(list.getClass().getSimpleName()
					+ "does not support ListIterators");
		}

	}

	public static void main(String[] args) {
		List<Employee> list = new SinglyLinkedList<>();
		checkList(list);

		list = new DoublyLinkedList<>();
		checkList(list);

		list = new ArrayList<>();
		checkList(list);
	}

}
