public class Maze {

	/**
	 * Прямоугольное поле заполнено нулями и единицам. Единицы это препятствия. Надо найти
	 * маршрут от левого верхнего угла в правый нижний при условии, что двигаться можно за один
	 * шаг только на одну клетку в прямом направлении, обходя препятствия. Вывести на экран
	 * последовательность шагов(up, down, right, left)
	 * @param maze Матрица, представляющая собой лабиринт из 0 и 1
	 * @param i    Текущая строка
	 * @param j    Текущий столбец
	 * @param path Пройденный путь в виде кодов шагов
	 * @return Пройденный путь в виде кодов шагов
	 */
	public static String findPath(int[][] maze, int i, int j, String path) {		
		int n = maze.length;
		int m = maze[0].length;
		if ((i == n - 1) && (j == m - 1)) {			
			return path;
		}		
		String newpath;
		maze[i][j] = 1;
		if ((i < n - 1) && (maze[i + 1][j] == 0)) {
			newpath = findPath(maze, i + 1, j, path + "1"); // down
			if (!newpath.equals(path)) {
				return newpath;
			}
		}
		if ((j < m - 1) && (maze[i][j + 1] == 0)) {
			newpath = findPath(maze, i, j + 1, path + "2"); // right
			if (!newpath.equals(path)) {
				return newpath;
			}
		}
		if ((i > 0) && (maze[i - 1][j] == 0)) {
			newpath = findPath(maze, i - 1, j, path + "3"); // up
			if (!newpath.equals(path)) {
				return newpath;
			}
		}
		if ((j > 0) && (maze[i][j - 1] == 0)) {
			newpath = findPath(maze, i, j - 1, path + "4"); // left
			if (!newpath.equals(path)) {
				return newpath;
			}
		}		
		return path.isEmpty()?path:path.substring(0, path.length() - 1);
	}

	public static void main(String[] args) {
		int[][] maze0 = { 	{ 0, 0, 0, 1, 0 }, 
							{ 0, 1, 0, 1, 0 },
							{ 0, 1, 1, 1, 0 }, 
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 0, 0, 1, 0 } };
		
		int[][] maze1 = { 	{ 0, 0, 0, 0, 0 }, 
							{ 0, 1, 0, 1, 0 },
							{ 0, 1, 1, 1, 0 }, 
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 0, 0, 1, 0 } };
		
		int[][] maze2 = { 	{ 0, 0, 0, 1, 0 }, 
							{ 0, 1, 0, 1, 0 },
							{ 0, 1, 0, 0, 0 }, 
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 0, 1, 1, 0 } };
		
		int[][] maze3 = { 	{ 0, 1, 0, 0, 0 }, 
							{ 0, 1, 0, 1, 0 },
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 0, 0, 1, 0 } };
		
		int[][] maze4 = { 	{ 0, 0, 0, 1, 0 }, 
							{ 0, 1, 0, 1, 0 },
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 1, 0, 1, 0 }, 
							{ 0, 1, 0, 0, 0 } };
		
		int[][] maze5 = { 	{ 0, 0, 0, 0, 0 }, 
							{ 1, 1, 1, 0, 1 },
							{ 0, 0, 0, 0, 0 }, 
							{ 0, 1, 0, 1, 1 }, 
							{ 0, 1, 0, 0, 0 } };
		
		int[][] maze6 = { 	{ 0, 0, 0, 0, 0 }, 
							{ 1, 1, 1, 0, 1 },
							{ 0, 0, 0, 0, 0 }, 
							{ 0, 1, 1, 1, 1 }, 
							{ 0, 0, 0, 0, 0 }};
		
		int[][][] testCases = {maze0, maze1, maze2, maze3, maze4, maze5, maze6};
		for(int[][] maze: testCases){
			Matrix.printMatrix(maze);
			String path = findPath(maze, 0, 0, "");
			if (path.isEmpty()) {
				System.out.println("There is no path");
			} else {
				System.out.print("Path:\t");
				path = path.replaceAll("1", " down ");
				path = path.replaceAll("2", " right ");
				path = path.replaceAll("3", " up ");
				path = path.replaceAll("4", " left ");			
				System.out.println(path);
			}
		}

	}

}
