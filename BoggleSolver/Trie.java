import java.util.List;
import java.util.Vector;

public class Trie {
		public Node root = new Node();
		private final int R = 26;
		
		public class Node {
			public boolean isWord;
			public Node[] next;
			
			public Node() {
				isWord = false;
				next = new Node[26];
			}
		}
		
		public Trie(String[] dictionary) {
			for (String word : dictionary) {
				add(word.toUpperCase());
			}
		}
	
		public void add(String s) {
			root = add(root, s, 0);
		}
				
		private Node add(Node x, String s, int d) {
			if (x == null) x = new Node();
			if (d == s.length()) {
				x.isWord = true;
				return x;
			}
			x.next[code(s.charAt(d))] = add(x.next[code(s.charAt(d))], s, d+1);
			return x;
		}		
		
		public boolean get(String s) {
			s = s.toUpperCase();
			Node x = root;
			for (char c : s.toCharArray()) {
				if (x.next[code(c)] == null) return false;
				x = x.next[code(c)];
			}
			if (x.isWord) return true;
			return false;
		}
		
		public Iterable<String> getKeys() {
			List<String> answer = new Vector<String>();
			collect(root, answer, new StringBuilder());
			return answer;
		}
		
		private void collect(Node x, List<String> ans, StringBuilder pref) { 
			if (x == null) return;
			if (x.isWord) ans.add(new String(pref));
			for (int i = 0; i < R; i++) {
				collect(x.next[i], ans, pref.append((char)('A' + i)));
				pref.deleteCharAt(pref.length()-1);
			}
			
		}
		
		private int code(char c) {
			if ( c > 'Z' || c < 'A') throw new IllegalArgumentException("Wrong character");
			return c - 'A';
		}

		public Node progress(Node x, String s, int d) {
			if (x == null) return null;
			if (d == s.length()) return x;
			return progress(x.next[code(s.charAt(d))], s, d+1);
		}

/*		public Node progress(Node x, String s, List<String> answer, StringBuilder word) {
			s = s.toUpperCase();
			word.append(s);
			for (char c : s.toCharArray()) {
				if (x.next[code(c)] == null) return null;
				x = x.next[code(c)];
			}
			if (x.isWord) answer.add(word.toString());
			return x;
		}
*/
}
