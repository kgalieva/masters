package term2.hashtable;

import java.util.Iterator;

public class HashMapTest {

	public static void main(String[] args) {
		HashMap<String, Integer> stat = new HashMap<>();
		stat.put("Kazan", 1000000);
		stat.put("Moscow", 11000000);
		stat.put("Saint Petersburg", 5000000);
		/*Integer population = stat.get("Kazan");
		System.out.println(population);
		stat.remove("Kazan");
		population = stat.get("Kazan");
		System.out.println(population);*/
		
		for(String k: stat.keySet()) {
			System.out.println(k);
		}
		Iterator<String> iter = stat.keySet().iterator(); 
		while (iter.hasNext()) {
			System.out.println(iter.next());
			//iter.remove();
		}
		for(Integer v: stat.values()){
			System.out.println(v);
		}
		for(HashMap.Entry<String, Integer> e: stat.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}

}
