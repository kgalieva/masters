package term2.sorted;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		Employee[] employees = {new Employee("cccccc", new BigDecimal(123)),
				new Employee("nnnnn",  new BigDecimal(345)),
				new Employee("bbbbb",  new BigDecimal(134)),
				new Employee("wwwwwww",  new BigDecimal(565)),
				new Employee("qqqqq",  new BigDecimal(133))};
		
		List list = new LinkedList();
		for(Employee e: employees){
		list.addSort(e);
		}
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
			//list.remove(1);
			iter.remove();
		}
		System.out.println("is empty: " + list.isEmpty());	
		
		/*Comparator employeeComparator = new EmployeeComparator();
		for(Employee e: employees){
			list.addSort(e, employeeComparator);
		}
		iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		System.out.println("is empty: " + list.isEmpty());	*/
	}

}
