
public class RWayTrie {
	private Node root = new Node(); 
	private final int R = 256;
	private class Node {
		Object value;
		Node[] next = new Node[R];
	}
	
	public void add(String key, Object value) {
		root = add(root, key, value, 0);
	}
			
	private Node add(Node x, String key, Object value, int d) {
		if (x == null) x = new Node();
		if (d == key.length()) {
			x.value = value;
			return x;
		}
		x.next[key.charAt(d)] = add(x.next[key.charAt(d)], key, value, d+1);
		return x;
	}		
	
	public Object get(String key) {
		return get(root, key, 0);
	}

	private Object get(Node x, String key, int d) {
		if (x == null) return null;
		if (d == key.length()) return x.value;
		return get(x.next[key.charAt(d)], key, d+1);
	}
}
