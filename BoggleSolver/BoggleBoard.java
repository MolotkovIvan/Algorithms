import java.util.*;

public class BoggleBoard {
	char[][] board;
	private final int rows;
	private final int cols;
	
	public BoggleBoard(char[][] board) {
		rows = board.length;
		cols = rows == 0 ? 0 : board[0].length;
		this.board = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	public int rows() { return rows; }
	public int cols() { return cols; }
	
	public char getLetter(int i, int j) {
		return board[i][j];
	}
}
