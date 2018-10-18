public class TST {
	private Node root; 
	private final int R = 256;
	private class Node {
		Object value;
		char c;
		Node l, r, next;
		public Node(char c) {
			this.c = c;
		}
	}
	
	public void add(String key, Object value) {
		root = add(root, key, value, 0);
	}

	private Node add(Node x, String key, Object value, int d) {
		if (x == null) x = new Node(key.charAt(d));
		if (d == key.length() - 1 && key.charAt(d) == x.c) {
			x.value = value;
			return x;
		}
		if (key.charAt(d) < x.c) {
			x.l = add(x.l, key, value, d);
		} else if (key.charAt(d) > x.c) {
			x.r = add(x.r, key, value, d);
		} else {
			x.next = add(x.next, key, value, d+1);
		}
		return x;
	}
	
	public Object get(String key) {
		return get(root, key, 0);
	}
	
	private Object get(Node x, String key, int d) {
		if (x == null) return null;
//		System.out.println(key.charAt(d) + " " +  x.c);
		if (d == key.length() - 1 && key.charAt(d) == x.c) {
			return x.value;
		}
		if (key.charAt(d) < x.c) {
			return get(x.l, key, d);
		} else if (key.charAt(d) > x.c) {
			return get(x.r, key, d);
		} else {
			return get(x.next, key, d+1);
		}
		
	}
}
