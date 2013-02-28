package term2.tree.binary;

public class TreeTest {

	
	public static void main(String[] args) {
		Tree tree = new Tree();		
		tree.insert(6, 6.0);
		tree.insert(9, 9.0);
		tree.insert(5, 5.0);
		tree.insert(4, 4.0);
		tree.insert(8, 8.0);
		tree.insert(1, 1.0);
		tree.insert(2, 2.0);
		System.out.println("pre order:");
		tree.preOrder();
		System.out.println("post order:");
		tree.postOrder();
		tree.displayTree();
		tree.delete(4);
		tree.delete(2);
		tree.delete(5);
		System.out.println("tree:");
		tree.displayTree();

	}

}
