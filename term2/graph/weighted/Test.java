package term2.graph.weighted;

public class Test {

	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph();
		graph.addVertex('A'); // 0 
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4

		graph.addEdge('A', 'B', 4); // AB
		graph.addEdge('B', 'C', 3); // BC
		graph.addEdge('A', 'D', 2); // AD
		graph.addEdge('D', 'E', 5); // DE
		graph.addEdge('D', 'B', 1); // DB

		System.out.print("Visits: ");
		graph.path('A');
	}

}
