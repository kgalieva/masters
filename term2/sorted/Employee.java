package term2.sorted;

import java.math.BigDecimal;

public class Employee implements Comparable{
	
	private String lastname;
	private BigDecimal salary;
	
	public Employee(String lastname, BigDecimal salary) {
		this.lastname = lastname;
		this.salary = salary;
	}

	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	} 
	
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public int compareTo(Object o) {
		Employee e = (Employee)o;
		return lastname.compareTo(e.lastname);
	}

	@Override
	public String toString() {
		return "Employee [lastname=" + lastname + ", salary=" + salary + "]";
	}	
	
}
