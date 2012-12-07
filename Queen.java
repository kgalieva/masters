public class Queen {

	public static void locateQueens(int n) {
		int[][] result = locateQueens(new int[n][n], 0);
		if (result == null) {
			System.out.printf(
					"It's imposible to locate %d queens on board %dx%d %n", n,
					n, n);
		} else {
			Matrix.printMatrix(result);
		}
	}

	public static int[][] locateQueens(int[][] board, int located) {
		if (located == board.length) {
			return board;
		}
		for (int i = 0; i < board.length; i++) {
			if (isValidLocation(board, located, i)) {
				board[located][i] = 1;
				int[][] result = locateQueens(board, located + 1);
				if (result != null) {
					return result;
				}
				board[located][i] = 0;

			}
		}
		return null;
	}

	public static boolean isValidLocation(int[][] board, int located, int column) {
		int queens = 0;
		for (int i = 1; i <= located; i++) {
			// считаем ферзей на данной вертикали
			queens += board[located - i][column];
			// считаем ферзей на диагонали параллельной главной
			if (column - i >= 0) {
				queens += board[located - i][column - i];
			}
			// считаем ферзей на диагонали параллельной главной
			if (column + i < board[0].length) {
				queens += board[located - i][column + i];
			}
		}
		return queens == 0;
	}

	public static void main(String[] args) {
		locateQueens(6);
	}

}
