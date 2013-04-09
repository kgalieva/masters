package term2.graph.kruskal;

import java.util.ArrayList;
import java.util.List;

import term2.disjointsets.DisjointSets;
import term2.queue.priority.PriorityQueueHeap;

public class Graph {

	private static class Vertex {
		public int label; // например, 'A'
		public boolean visited;

		public Vertex(int lab) {
			label = lab;
			visited = false;
		}

		@Override
		public String toString() {
			return "[" + label + "]";
		}	
	}

	public static class Edge implements Comparable<Edge> {
		int vertexA;
		int vertexB;
		int weight;

		public Edge(int vertexA, int vertexB, int weight) {
			this.vertexA = vertexA;
			this.vertexB = vertexB;
			this.weight = weight;
		}		

		@Override
		public String toString() {
			return "(" + vertexA + ", " + vertexB + ") : Weight = " + weight;
		}

		public int compareTo(Edge edge) {
			return this.weight - edge.weight;
		}
	}

	//массив вершин
	private Vertex vertexList[]; 
	//матрица смежности
	private int adjMat[][]; 
	//количество вершин
	private int nVerts; 

	public Graph(int maxSize) {
		vertexList = new Vertex[maxSize];
		adjMat = new int[maxSize][maxSize];
		for(int i =0; i<adjMat.length; i++) {
			for (int j = 0; j < adjMat[i].length; j++) {
				adjMat[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public void addVertex(int lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}
	
	public void addEdge(int start, int end, int weight) {
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
	}
		
	public List<Edge> kruskal() {
		List<Edge> tree = new ArrayList<>();
		DisjointSets s = new DisjointSets(nVerts);
		//Для хранения ребер используется приоритетная очередь на основе пирамиды.
		PriorityQueueHeap<Edge> edges = new PriorityQueueHeap<Edge>();
		for(int i = 0; i < nVerts - 1; i++) {
			for(int j = i + 1; j < nVerts; j++) {
				if(adjMat[i][j] != Integer.MAX_VALUE){
					edges.offer(new Edge(i, j, adjMat[i][j]));
				}
			}
		}		
		Edge currentEdge;		
		int setA;
		int setB;
		while (!edges.isEmpty()) {
			currentEdge = edges.poll();
			setA = s.find(currentEdge.vertexA);			
			setB = s.find(currentEdge.vertexB);			
			if (setA != setB) {
				s.union(setA, setB);
				tree.add(currentEdge);
			}	
		}
		return tree;		
	}

	@Override
	public String toString() {
        StringBuilder str = new StringBuilder("Graph:\n");
        for (int i = 0; i < nVerts; i++) {
        	  for (int j = i; j < nVerts; j++) {
        		  if(adjMat[i][j] != Integer.MAX_VALUE) {
        			  str.append(vertexList[i]).append(" - ").append(vertexList[j]);
        			  str.append(" weight = ").append(adjMat[i][j]).append("\n");
        		  }
        	  }
        }
		return str.toString();
	}

}