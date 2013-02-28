package term2.tree.binary;

public class TreeTest {

	
	public static void main(String[] args) {
		Tree tree = new Tree();		
		tree.insert(10, 10.0);
		tree.insert(9, 9.0);
		tree.insert(5, 5.0);
		tree.insert(4, 4.0);
		tree.insert(8, 8.0);
		tree.insert(1, 1.0);
		tree.insert(2, 2.0);
		tree.insert(3, 3.0);
		tree.insert(5, 5.0);
		tree.insert(15, 15.0);
		tree.insert(12, 12.0);
		tree.insert(14, 14.0);
		tree.insert(11, 11.0);
		tree.insert(13, 13.0);
		System.out.println("pre order:");
		tree.preOrder();
		System.out.println("post order:");
		tree.postOrder();
		tree.displayTree();
		tree.delete(12);
		System.out.println("tree:");
		tree.displayTree();

	}

}
