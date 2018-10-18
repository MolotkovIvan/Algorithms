
public class SubstringSearch {
	public static void main(String[] args) {
		String a = "sojfdlkekdsma";
		String b = "jfdl";
		System.out.println(KMP(a, b));
	}
	
	public static int KMP(String s, String t) {
		String text = t + '#' + s;
		int[] prefixFunction = new int[text.length()+1];
		computePrefixFunction(text, prefixFunction);
		for (int i = 2 * t.length() + 1; i <= text.length(); i++) {
			if (prefixFunction[i] == t.length()) {
				return i - 2 * t.length() - 1;
			}
		}
		return s.length();
	}
	
	private static void computePrefixFunction(String s, int[] p) {
		p[1] = 0;
		for (int i = 2; i < p.length; i++) {
			p[i] = p[i-1];
			while (p[i] > 0 && s.charAt(p[i]) != s.charAt(i-1)) {
				p[i] = p[p[i]-1];
			}
			if (s.charAt(p[i]) == s.charAt(i-1)) p[i]++;
		}
	}

	public static int BoyerMoore(String s, String t) {
		int R = 256;
		int[] jump = new int[R];
		for (int r = 0; r < R; r++) {
			jump[r] = t.length();
		}
		
		for (int i = 0; i < t.length(); i++) {
			jump[t.charAt(i)] = t.length() - i - 1;
		}
//		String a = "sojfdlkekdsma";
//		String b = "jfdl";
		for (int i = t.length()-1; i < s.length();) {
			int j = t.length()-1;
			while (j >= 0 && s.charAt(i) == t.charAt(j)) {
				i--; j--;
			}
			if (j < 0) {
				return i+1;
			} else {
				i += jump[s.charAt(i)];
			}
		}
		return s.length();
	}
}
