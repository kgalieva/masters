package term2.hashtable;

public class HashMap<K, V> {

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

	private int size;
	private Entry[] table = new Entry[16];
	static final float LOAD_FACTOR = 0.75f;

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
}
