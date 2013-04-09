package term2.graph.kruskal;

import java.util.List;

public class KruskalTest {

	public static void main(String[] args) {		
		Graph graph = new Graph(20);
		graph.addVertex(0);  
		graph.addVertex(1); 
		graph.addVertex(2); 
		graph.addVertex(3); 
		graph.addVertex(4); 

		graph.addEdge(0, 1, 4); // 0-2
		graph.addEdge(1, 2, 3); // 2-3
		graph.addEdge(0, 3, 2); // 0-4
		graph.addEdge(3, 4, 5); // 4-5
		graph.addEdge(3, 1, 1); // 4-2		
		System.out.println(graph);
		List<Graph.Edge> tree = graph.kruskal();
		System.out.println("Kruskal Algorithm: ");
		for (Graph.Edge e: tree) {
			System.out.println(e);
		}
	}	
}
