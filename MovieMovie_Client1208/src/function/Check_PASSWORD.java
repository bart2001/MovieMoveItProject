package function;

public class Check_PASSWORD {
	
	public static boolean check(String p1, String p2) {

		if (p1.equals(p2)) {
			return true;
		}
		return false;
	}
}
