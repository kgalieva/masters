package term2.graph;

public class GraphTest {
	public static void main(String[] args) {
		Graph graph = new Graph(20);
		graph.addVertex('A'); // 0 
		graph.addVertex('B'); // 1
		graph.addVertex('C'); // 2
		graph.addVertex('D'); // 3
		graph.addVertex('E'); // 4

		graph.addEdge(0, 1); // AB
		graph.addEdge(1, 2); // BC
		graph.addEdge(0, 3); // AD
		graph.addEdge(3, 4); // DE

		/* Для простоты все обходы начинаются с нулевой вершины.
		 * Если вы хотите задавать стартовую вершину с помощью метки, а не индекса, то используйте HashMap для хранения соответствий label - id,
		 * т.к. операция поиска по ключу в хеш-таблице занимает O(1)(при хорошей реализации hashCode())
		 * */
		System.out.println("dfs:");
		graph.dfs(); // обход в глубину  depth-first search		
		System.out.println("\ndepth-first search backtracking algorithm:");
		graph.dfsBacktracking(); // обход в глубину с помощью алгоритма поиска с возвратом (depth-first search backtracking algorithm)
		System.out.println("\nbfs:");
		graph.bfs();
	} 
}