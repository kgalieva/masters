package term2.graph;

import term2.queue.LinkedQueue;
import term2.queue.Queue;
import term2.stack.Stack;

public class Graph {

	private static class Vertex {
		public char label; // например, 'A'
		public boolean visited;

		public Vertex(char lab) {
			label = lab;
			visited = false;
		}

		@Override
		public String toString() {
			return "[" + label + "]";
		}	
	}

	//массив вершин
	private Vertex vertexList[]; 
	//матрица смежности
	private boolean adjMat[][]; 
	//количество вершин
	private int nVerts; 

	public Graph(int maxSize) {
		vertexList = new Vertex[maxSize];
		adjMat = new boolean[maxSize][maxSize];
	}

	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}

	public void addEdge(int start, int end) {
		adjMat[start][end] = true;
		adjMat[end][start] = true;
	}

	/* Обход в глубину (depth-first search)
	 * 
	 * 1) Посетить начальную вершину, пометить ее посещенной и поместить в стек  
	 *  Пока в стеке есть элементы
	 *  (Текущей считается вершина, которая в данный момент находится на вершине стека)
	 * 2) Посетить смежную вершину, не посещавшуюся ранее, пометить ее и поместить в стек
	 * 3) Если непосещенных смежных вершин больше нет, то удаляем элемент из стека и переходим к пункту 2) 
	 * 
	 *  Если стек пустой, то мы закончили обход.
	 *  Устанавливаем флаг visited для всех вершин в false
	 */	
	public void dfs() {
		Stack stack = new Stack(vertexList.length);
		// начинаем с вершины 0 и помечаем ее посещенной
		vertexList[0].visited = true;
		System.out.print(vertexList[0]);
		stack.push(0);
		while (!stack.isEmpty()) {
			//находим непосещенную вершину смежную к той, которая сейчас на вершине стека
			int v = getAdjUnvisitedVertex(stack.peek());
			//если таких вершин нет
			if (v == -1) {
				stack.pop();
			} else { 
				//помечаем вершину посещенной
				vertexList[v].visited = true; 
				//отображаем ее
				System.out.print(vertexList[v]); 
				//кладем в стек
				stack.push(v); 
			}
		}

		// если стек пустой, то обход закончен
		for (int j = 0; j < nVerts; j++) {
			//сбрасываем значения флагов visited
			vertexList[j].visited = false;
		}
	}

	/**
	 * Обход в ширину(breadth first search). 
	 * Похож на DFS, но в нем используется очередь вместо стека для хранения вершин.
	 */
	public void bfs() {
		Queue queue = new LinkedQueue();
		// начинаем с вершины 0 и помечаем ее посещенной
		vertexList[0].visited = true;
		System.out.print(vertexList[0]);
		queue.offer(0);
		while (!queue.isEmpty()) {
			//находим непосещенную вершину смежную к той, которая сейчас первая в очереди
			int v = (Integer)getAdjUnvisitedVertex((Integer)queue.peek());			
			if (v == -1) {
				//если таких вершин нет, удаляем текущую вершину из очереди
				queue.poll();
			} else { 
				vertexList[v].visited = true; 
				System.out.print(vertexList[v]); 
				queue.offer(v); 
			}
		}

		for (int j = 0; j < nVerts; j++) {
			vertexList[j].visited = false;
		}
	}
	
	public void dfsBacktracking(){
		dfsBacktracking(0);
		for (int j = 0; j < nVerts; j++) {
			//сбрасываем значения флагов visited
			vertexList[j].visited = false;
		}
	}
	
	/**
	 * Обход в глубину с помощью алгоритма поиска с возвратом(backtracking algorithm)
	 */
	private void dfsBacktracking(int v) {
		vertexList[v].visited = true;
		System.out.print(vertexList[v]);
		for (int j = 0; j < nVerts; j++) {
			if (adjMat[v][j] && !vertexList[j].visited) {
				dfsBacktracking(j);
			}
		}
	}
	
	/**
	 * Возвращает идентификатор смежной непосещенной вершины,
	 * -1, если вершина не найдена 
	 */
	public int getAdjUnvisitedVertex(int v) {
		for (int j = 0; j < nVerts; j++) {
			if (adjMat[v][j] && !vertexList[j].visited) {
				return j;
			}
		}
		return -1;
	}
	
	

}