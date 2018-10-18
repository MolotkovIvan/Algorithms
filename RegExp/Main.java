
public class Main {

	public static void main(String[] args) {
		RegularExpression re = new RegularExpression("((a|bc)*ef.d)");
		System.out.println(re.matches("aefcd"));
		System.out.println(re.matches("aaaefpd"));
		System.out.println(re.matches("abcbcaefrd"));
		System.out.println(re.matches("abcefd"));
		System.out.println(re.matches("aerffd"));
		System.out.println(re.matches("abcd"));
		System.out.println(re.matches("efd"));
		System.out.println(re.matches("efcd"));
		System.out.println(re.matches("rmn"));
	}

}
