package term2.hashtable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> {
	/**
	 * Элменты Map представляют собой пары ключ-значение
	 * @param <K>
	 * @param <V>
	 */
	static class Entry<K, V> {
		final K key;
		V value;
		Entry<K, V> next;
		final int hash;

		Entry(int h, K k, V v, Entry<K, V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
	}

	static final float LOAD_FACTOR = 0.75f;
	private int size;	
	private Entry[] table = new Entry[16];
	private int modCount;
	private Iterable<V> values;
	private Iterable<Entry<K, V>> entrySet;
	

	public V get(Object key) {
		int hash = (key == null) ? 0 : key.hashCode();
		// перебираем элементы списка из заданной ячейки в поиках элемента с
		// заданным значением ключа
		for (Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
			/*
			 * Сначала сравниваем хеш-коды, потому что это бысрее, чем вызов
			 * метода equals(). Поэтому очень важно, чтобы у равных объектов
			 * были одинаковые хеш-коды
			 */
			if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key)))) {
				return e.value;
			}
		}
		return null;
	}

	/*
	 * Изменение размера хеш-таблицы
	 */
	private void resize(int newCapacity) {
		Entry[] newTable = new Entry[newCapacity];
		for (int j = 0; j < table.length; j++) {
			Entry<K, V> e = table[j];
			if (e != null) {
				//удаляем ссылку из старой таблицы
				table[j] = null;
				do {
					Entry<K, V> next = e.next;
					/* Вычисляем индекс для элемента e в новой таблице.
					 * Он может отличаться от предыдущего, поскольку размер таблицы изменился.
					 */
					int i = indexFor(e.hash, newCapacity);
					//связываем элемент e cо списком в i-ой ячейке хеш-таблицы
					e.next = newTable[i];
					//новый элемент станет первым в списке, обновляем ссылку на него в i-ой ячейке хеш-таблицы
					newTable[i] = e;
					e = next;
				} while (e != null);
			}
		}
		table = newTable;
	}

	/*
	 * 1) Вычисляем хеш-код и индекс ячейки, в которую нужно поместить объект с
	 * данным значением ключа. 
	 * 2) Проверяем нет ли в данной ячейке элемента со значением ключа равным key. 
	 * 3) Если есть, то запоминаем старое значение, перезаписываем его на новое 
	 * и возвращаем старое значение 
	 * 4) Если нет, то добавляем новый элемент в начало списка и увеличиваем значение size.
	 * 5) Если коэффициент заполнения таблицы больше константы LOAD_FACTOR, 
	 * то увеличиваем размер таблицы в 2 раза с помощью метода resize()
	 */
	public V put(K key, V value) {
		//TODO
		return null;
	}

	public V remove(Object key) {
		int hash = (key == null) ? 0 : key.hashCode();
		// вычисляем индекс элемента с учетом размера таблицы
		int i = indexFor(hash, table.length);
		Entry<K, V> prev = table[i];
		Entry<K, V> e = prev;
		// идем по списку из заданной ячейки и ищем удаляемый элемент
		while (e != null) {
			/* сначала сравниваем хеш-коды, потому что это бысрее, чем equals */
			if (e.hash == hash
					&& (e.key == key || (key != null && key.equals(e.key)))) {
				size--;
				// удаляемый элемент был первым в цепочке
				if (prev == e) {
					table[i] = e.next;
				} else {
					prev.next = e.next;
				}
				e.next = null;
				modCount++;
				return e.value;
			}
			prev = e;
			e = e.next;
		}
		return null;
	}
	
	/**
	 * Вычисление индекса с учетом размера таблицы
	 * @param hash   Хеш-код 
	 * @param length Размер таблицы
	 * @return номер ячейки
	 */
	static int indexFor(int hash, int length) {
		return Math.abs(hash) % length;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Итератор для перебора элементов хеш-таблицы
	 */
	private abstract class HashIterator<E> implements Iterator<E> {
		//следующий элемент, который вернет итератор
		Entry<K, V> next; 
		//поле для защиты от модификации хеш-таблицы во время ее перебора 
		int expectedModCount; 
		//текущая ячейка хеш-таблицы
		int index;
		//последний элемент, который вернул итератор
		Entry<K, V> current;

		HashIterator() {
			//запоминаем количество модификаций, которое было до начала перебора
			expectedModCount = modCount;
			if (size > 0) {
				//пропускаем нулевые ячейки в начале таблицы
				while (index < table.length && table[index] == null){
					index++;
				}
				next = table[index];
			}
		}

		public final boolean hasNext() {
			return next != null;
		}
		/* */
		final Entry<K, V> nextEntry() {
			//проверяем, не было ли модификаций хеш-таблицы во время перебора элементов
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}			
			if (next == null) {
				throw new NoSuchElementException();
			}
			current = next;
			//ищем следующий элемент
			next = next.next;			
			if (next == null) {
				//если в текущей цепочке закончились элемненты, то перемещаемся в следующую ненулевую ячейку
				while (++index < table.length && (next = table[index]) == null)
					;
			}
			return current;
		}

		public void remove() {
			if (current == null) {
				throw new IllegalStateException();
			}
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			Object k = current.key;
			current = null;
			HashMap.this.remove(k);
			expectedModCount = modCount;
		}
	}	
	/**Метод, который возвращает итерируемый набор значений элементов, которые лежат в хеш-таблице*/
	public Iterable<V> values() {
		return values != null ? values : (values = new Values());
	}
	
	/**
	 * Итератор для перебора значений элементов, которые лежат в хеш-таблице
	 */
	private final class ValueIterator extends HashIterator<V> {
		public V next() {
			return nextEntry().value;
		}
	}
	
	/**
	 * Вспомогательный класс с возможностью итерации по значениям
	 */
	private final class Values implements Iterable<V> {
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}	

	public Iterable<K> keySet() {
		//TODO
		return null;
	}

	public Iterable<Entry<K, V>> entrySet() {
		return entrySet != null ? entrySet : (entrySet = new EntrySet());
	}

	private final class EntryIterator extends HashIterator<Entry<K, V>> {
		public Entry<K, V> next() {
			return nextEntry();
		}
	}

	private final class EntrySet implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	}
}
