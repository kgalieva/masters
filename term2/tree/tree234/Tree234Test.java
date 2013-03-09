package term2.tree.tree234;

public class Tree234Test {
	
	public static void main(String[] args) {
		Tree234<Integer, String> tree= new Tree234<>();
		
		tree.insert(70, "70");
		tree.insert(50, "50");
		tree.insert(30, "30");
		tree.displayTree();
		tree.insert(40, "40");
		tree.displayTree();
		tree.insert(20, "20");
		tree.insert(80, "80");
		tree.displayTree();
		tree.insert(25, "25");
		tree.insert(90, "90");
		tree.displayTree();
		tree.insert(75, "75");
		tree.displayTree();
		tree.insert(10, "10");
		tree.displayTree();
		
		System.out.println("find 25: " + tree.find(25));
		System.out.println("find 70: " + tree.find(70));
		System.out.println("find 40: " + tree.find(40));
		System.out.println("find 90: " + tree.find(90));
		System.out.println("find 100: " + tree.find(100));
	}

}
