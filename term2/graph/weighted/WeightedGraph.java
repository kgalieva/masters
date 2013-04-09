package term2.graph.weighted;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Взвешенный граф и алгоритм Дейкстры
 */
public class WeightedGraph {

	private static class Vertex {
		int id;
		public char label; // label (e.g. 'A')
		public boolean visited;

		public Vertex(int id, char lab) {
			this.id = id;
			label = lab;
			visited = false;
		}

		@Override
		public String toString() {
			return "[" + label + "]";
		}
		
	}
	
	private class Distance implements Comparable<Distance>{
		//расстояние от исходной вершины до текущей
		int distance;
		//вершина, из которой мы перешли в текущую
		Vertex parentVertex;
		public Distance(int distance, Vertex parentVertex) {
			super();
			this.distance = distance;
			this.parentVertex = parentVertex;
		}
		@Override
		public int compareTo(Distance o) {			
			return distance - o.distance;
		}
		@Override
		public String toString() {
			return "[dist=" + distance + ", parent="
					+ parentVertex + "]";
		}
		
	}

	private final int MAX_VERTS = 20;
	//список вершин
	private Vertex vertexList[]; 
	//хеш-таблица соответствий лейблов вершинам
	private Map<Character, Vertex> lables = new HashMap<>();
	//матрица смежности. Каждая ячейка матрицы хранит вес ребра. Если соответствующего ребра нет, то вес равен бесконечности.
	private int adjMat[][]; 

	public WeightedGraph() {
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		for(int i =0; i<adjMat.length; i++) {
			for (int j = 0; j < adjMat[i].length; j++) {
				adjMat[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	public void addVertex(char lab) {
		vertexList[lables.size()] = new Vertex(lables.size(), lab);
		lables.put(lab, vertexList[lables.size()]);
	}

	public void addEdge(char vertex1, char vertex2, int weight) {
		int start = lables.get(vertex1).id;
		int end = lables.get(vertex2).id;
		adjMat[start][end] = weight;
		adjMat[end][start] = weight;
		
	}

	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}

	/**
	 * Алгоритм Дейкстры
	 * Заводим массив distances с минимальными расстояниями от начальной вершины до других вершин.
	 *	Начинаем с вершины с заданным названием. Выполнение метода начинается с включения начальной вершины в путь. Для этого достаточно поменять значение флага visited увеличить счетчик вершин в пути. Затем path() копирует расстояния из соответствующей строки матрицы смежности в distances.
	 *	Далее метод входит в основной цикл while алгоритма. Цикл завершается после того, как все вершины будут включены в дерево. В цикле выполняются 3 действия:
	 *	1.	Выбор элемента distances с наименьшим расстоянием.
	 *	2.	Включение соответствующей вершины в путь. Вершина становится текущей.
	 * 	3.	Обновление всех элементов distances в соответствии с расстояниями от текущей вершины.
	 *	Если метод path() обнаруживает, что минимальное расстояние равно бесконечности, то он знает, что некоторые вершины недостижимы от начальной точки. 
	 *	Перед выходом из метода выводится содержимое distances и сбрасываются флаги вершин.
	 *
	 * @param label Имя начальной вершины
	 */
	public void path(char label) {
		Distance[] distances = new Distance[lables.size()];
		int count = 1;
		Vertex current = lables.get(label);
		current.visited = true;
		for(int i = 0; i < distances.length; i++) {
			distances[i] = new Distance(adjMat[current.id][i], current);
		}
		while (count < lables.size()) {
			int minDist = min(distances);
			if(distances[minDist].distance == Integer.MAX_VALUE) {
				break;
			}
			current = vertexList[minDist];
			count++;
			current.visited = true;
			updatePathList(distances, current);
		}	
		
		System.out.println(Arrays.toString(distances));
	}
	
	/**
	 * Перебирает массив distances и возвращает идентификатор вершины с наименьшем расстоянием не включенной в путь
	 * @param distances массив с расстояними
	 * @return идентификатор вершины с наименьшем расстоянием не включенной в путь
	 */
	private int min(Distance[] distances) {
		int minIndex = -1;		
		for(int i = 0; i < distances.length; i++) {
			if(minIndex == -1) {
				if(!vertexList[i].visited) {
					minIndex = i;
				}
				continue;
			}
			if(!vertexList[i].visited && distances[i].distance < distances[minIndex].distance) {
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	/**
	 * Обновляет массив distances после посещения очередной вершины. Для каждого элемента distances, при условии, что соответствующая вершина не включена в путь, выполняются следующие действия:
	 *	1.	Расстояние до текущей вершины суммируется с весом ребра от текущей до i-ой вершины.
	 *	2.	Полученное значение сравнивается со значением в distances
	 *	3.	Если оно меньше, то элемент distances обновляется.
	 * @param distances массив с расстояними
	 * @param current вершина, которую мы посетили
	 */
	private void updatePathList(Distance[] distances, Vertex current){
		int currentDist = distances[current.id].distance;
		for(int i = 0; i < distances.length; i++) {
			if(!vertexList[i].visited && adjMat[current.id][i] != Integer.MAX_VALUE && distances[i].distance > currentDist + adjMat[current.id][i]) {
				distances[i].distance = currentDist + adjMat[current.id][i];
				distances[i].parentVertex = current;
			}
		}
	}
	
}