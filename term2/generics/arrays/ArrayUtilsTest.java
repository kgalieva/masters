package term2.generics.arrays;


public class ArrayUtilsTest {
	
	public static void main(String[] args) {		
		String[] names = {"Tom", "Alice", "James"};
		
		//требуется явное приведение типа
		String value = (String)ArrayUtils.randomElement(names);
		System.out.println(value);
		
		/* 
		 * При вызове параметризованного метода не требуется указывать параметр типа,
		 * он вычисляется автоматически по типу аргумента.
		 * Возвращаемое значение метода ArrayUtils.genericRandomElement в данном случае 
		 * будет иметь тип String, поэтому явное приведение типа не требуется.
		 */
		value = ArrayUtils.genericRandomElement(names);
		System.out.println(value);
		
	}

}
