package term2.tree.rb;

public class TreeTest {

	
	public static void main(String[] args) {
		RedBlackTree<Integer, String> tree = new RedBlackTree<>();		
		tree.put(10, "10");
		tree.put(11, "11");
		tree.put(12, "12");	
		tree.put(13, "13");
		tree.put(14, "14");
		tree.put(15, "15");
		tree.put(20, "20");
		tree.put(30, "30");
		tree.put(40, "40");
		tree.put(50, "50");
		tree.put(60, "60");
		tree.put(70, "70");	
		tree.put(75, "75");		
		tree.put(80, "80");
		tree.put(90, "90");
		tree.put(92, "92");
		tree.put(95, "95");	
		tree.put(99, "99");	
		
		System.out.println("RED-BLACK tree:");
		tree.displayTree();
	}

}
