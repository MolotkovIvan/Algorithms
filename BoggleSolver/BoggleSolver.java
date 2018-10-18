import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class BoggleSolver {	
	private Trie t;
	private List<Integer>[] adj;
	private Set<String> allWords;
	private boolean marked[] = null;
	
	public BoggleSolver(String[] dictionary) {
		t = new Trie(dictionary);
	}
	
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		allWords = new HashSet<String>();
		int cols = board.cols();
		int rows = board.rows();
		marked = new boolean[cols*rows];
		adj = (Vector<Integer>[]) new Vector[cols*rows];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new Vector<Integer>();
		}
		for (int i = 0; i < adj.length; i++) {
			int x = i % cols;
			int y = i / cols;

			for (int j = -1; j <= 1; j++) {
				if (y > 0 && x + j >= 0 && x + j < cols) {
					adj[i].add( (y-1)*cols + (x+j) );
				}
			}

			if (x > 0) adj[i].add(i-1);
			if (x < cols-1) adj[i].add(i+1);

			for (int j = -1; j <= 1; j++) {
				if (y < rows-1 && x + j >= 0 && x + j < cols) {
					adj[i].add( (y+1)*cols + (x+j) );
				}
			}
		}
		for (int i = 0; i < adj.length; i++) {
			dfs(i, t.root, new StringBuilder(), getChar(i, board), board);
		}
		return allWords;
	}
	
	private char getChar(int index, BoggleBoard board) {
		return board.getLetter(index/board.cols(), index%board.cols());
	}
	
	private String stringFromChar(char c) {
		return c == 'Q' ? "QU" : (""+c);
	}
	
	private void dfs(int v, Trie.Node x, StringBuilder word, char addition, BoggleBoard board) {
//		System.out.println(word.toString() + addition);
		Trie.Node destination = t.progress(x, stringFromChar(addition), 0);
		if (destination == null) return;
		
		marked[v] = true;
		word.append(stringFromChar(addition));
		if (destination.isWord && word.length() >= 3) allWords.add(word.toString());
		for (int u : adj[v]) {
			if (!marked[u]) dfs(u, destination, word, getChar(u, board), board);
		}
		marked[v] = false;
		word.delete(word.length() - stringFromChar(addition).length(), word.length());
	}
	
	public int scoreOf(String word) {
		if (!t.get(word)) return 0;
		int len = word.length();
		if (len >= 0 && len <= 2) return 0;
		if (len >= 3 && len <= 4) return 1;
		if (len == 5) return 2;
		if (len == 6) return 3;
		if (len == 7) return 5;
		if (len >= 8) return 11;
		return 0;
	}
}
