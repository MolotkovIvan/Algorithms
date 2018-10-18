import java.util.*;

public class StringSort {
	public static void main(String[] args) {
		String[] s = new String[] {"aek", "cde", "aeln", "aelnbs", "pra", "aekk", "aek"};
		radixQuicksort(s);
		System.out.println(Arrays.toString(s));
	}
	
	public static void MSDsort(String[] s) {
		String[] aux = new String[s.length];
		MSDsort(s, aux, 0, s.length, 0);
	}
	
	private static void MSDsort(String[] s, String[] aux, int from, int to, int d) {
		if (to - from <= 1) return;
		int R = 256;
		int[] count = new int[R+2];
		for (int i = from; i < to; i++) {
			count[charAt(s[i], d) + 2]++;
		}
		for (int r = 0; r < R+1; r++) {
			count[r+1] += count[r];
		}
		for (int i = from; i < to; i++) {
			aux[count[charAt(s[i], d) + 1]++] = s[i];
		}
		for (int i = from; i < to; i++) {
			s[i] = aux[i - from];
		}
		for (int r = 0; r < R; r++) {
			MSDsort(s, aux, from + count[r], from + count[r+1], d+1);
		}
	}
		
	public static void LSDsort(String[] s) {
		int maxLength = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > maxLength) maxLength = s[i].length();
		}
		int W = maxLength;
		int R = 256;
		int N = s.length;
		for (int i = W-1; i >= 0; i--) {
			int[] count = new int[R+1];
			String[] aux = new String[N];
			for (int j = 0; j < N; j++) {
				count[optionalCharAtLSD(s[j],i) + 1]++;
			}
			for (int j = 1; j < R+1; j++) {
				count[j] += count[j-1];
			}
			for (int j = 0; j < N; j++) {
				aux[count[optionalCharAtLSD(s[j],i)]++] = s[j];
			}
			for (int j = 0; j < N; j++) {
				s[j] = aux[j];
			}
		}	
	}
	
	private static int optionalCharAtLSD(String s, int d) {
		return d >= s.length() ? 0 : s.charAt(d);		
	}
	
	public static void radixQuicksort(String[] s) {
		radixQuicksort(s, 0, s.length, 0);
	}

	private static void radixQuicksort(String[] s, int from, int to, int d) {
		if (to - from <= 1) return;
		Random rand = new Random();
		int pivot = rand.nextInt(to-from-1) + from;
//		int pivot = from;
		int c = charAt(s[pivot], d);
		int l1 = from, l2 = to-1;
		for (int i = from; i <= l2;) {
			int t = charAt(s[i], d);
			if (t < c) swap(s, l1++, i++);
			if (t > c) swap(s, l2--, i);
			if (t == c) i++;
		}
		radixQuicksort(s, from, l1, d);
		if (c >= 0) radixQuicksort(s, l1, l2+1, d+1);
		radixQuicksort(s, l2+1, to, d);
	}
	
	private static void swap(String[] s, int i, int j) {
		String c = s[i];
		s[i] = s[j];
		s[j] = c;
	}

	private static int charAt(String s, int d) {
		return d >= s.length() ? -1 : s.charAt(d);
	}

}
