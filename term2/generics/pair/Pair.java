package term2.generics.pair;

/*
 *  * Для параметров типов приняты следующие обозначения
 *
 * E Элемент коллекции (Element)
 * K Ключ (Key)
 * V Значение (Value)
 * T Тип (Type)
 * S, U Дополнительные обозначения типов
 * */
/**
 * Объект для хранения пар значений
 * @param <T> Тип первого значения
 * @param <S> Тип второго значения
 */
public class Pair<T, S> {

	private T first;
	private S second;

	public Pair(T firstElement, S secondElement) {
		first = firstElement;
		second = secondElement;
	}

	public T getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	/*TODO Реализуйте метод swap, который создает новую пару 
	 * с переставленными местами значениями полей first и second*/
	
	
}