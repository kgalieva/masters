package term2.generics.pair;

public class TestPair {

	public static void main(String[] args) {
		Pair<String, Integer> moscow = new Pair<>("Moscow", 495);
		Pair<String, Integer> kazan = new Pair<>("Kazan", 843);
		System.out.printf("Phone code of %s is %d%n", moscow.getFirst(), moscow.getSecond());
		System.out.printf("Phone code of %s is %d%n", kazan.getFirst(), kazan.getSecond());
		/*TODO Создайте пару "Hello" и "World"*/
	}

}
