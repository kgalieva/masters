package term2.hashtable;

public class HashMapTest {

	public static void main(String[] args) {
		HashMap<String, Integer> stat = new HashMap<>();
		stat.put("Kazan", 1000000);
		stat.put("Moscow", 11000000);
		stat.put("Saint Petersburg", 5000000);
		Integer population = stat.get("Kazan");
		System.out.println(population);
		stat.remove("Kazan");
		population = stat.get("Kazan");
		System.out.println(population);
	}

}
