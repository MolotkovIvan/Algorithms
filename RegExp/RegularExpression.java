import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class RegularExpression {
	String regex;
	List<Integer>[] adj;
	int m;
	
	public RegularExpression(String regex) {
		this.regex = regex;
		m = regex.length();
		adj = (Vector<Integer>[]) new Vector[m+1];
		for (int i = 0; i < m+1; i++) adj[i] = new Vector<Integer>();
		Stack<Integer> ops = new Stack<Integer>();
		for (int i = 0; i < m; i++) {
			int lp = i;
			if (regex.charAt(i) == '(' || regex.charAt(i) == '|') {
				ops.push(i);
			}
			if (regex.charAt(i) == ')') {
				int prev = ops.pop();
				if (regex.charAt(prev) == '|') {
					lp = ops.pop();
					adj[lp].add(prev+1);
					adj[prev].add(i);
				} else if (regex.charAt(prev) == '(') {
					lp = prev;
				}
			}
			if (i+1 < m && regex.charAt(i+1) == '*') {
				adj[i+1].add(lp);
				adj[lp].add(i+1);
			}
			if (regex.charAt(i) == '(' || regex.charAt(i) == ')' || regex.charAt(i) == '*') {
				adj[i].add(i+1);
			}
		}
		if (!ops.isEmpty()) throw new IllegalArgumentException("regex is not valid");
	}
	
	public boolean matches(String txt) {
		DFS dfs = new DFS();
		Vector<Integer> pc = new Vector<Integer>();
		for (int v = 0; v < m+1; v++) {
			if (dfs.marked(v)) pc.add(v);
		}
		for (int i = 0; i < txt.length(); i++) {
			Vector<Integer> match = new Vector<Integer>();
			for (int v : pc) {
				if (v == m) continue;
				if (txt.charAt(i) == regex.charAt(v) || regex.charAt(v) == '.') {
					match.add(v+1);
				}
			}
			dfs = new DFS(match);
			pc = new Vector<Integer>();
			for (int v = 0; v < m+1; v++) {
				if (dfs.marked(v)) pc.add(v);
			}
		}
		for (int v : pc) {
			if (v == m) {
				return true;
			}
		}
		return false;
	}
	
	private class DFS {
		boolean[] marked = new boolean[m+1];
		private void dfs(int v) {
			marked[v] = true;
			for (int u : adj[v]) {
				if (!marked[u]) dfs(u); 
			}
		}
		
		public DFS() {
			dfs(0);
		}
		
		public DFS(List<Integer> start) {
			for (int v : start) {
				if (!marked[v]) dfs(v);
			}
		}
		
		public boolean marked(int v) {
			return marked[v];
		}
	}
}
