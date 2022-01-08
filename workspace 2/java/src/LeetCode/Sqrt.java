package LeetCode;

public class Sqrt {
	public int mySqrt(int x) {
		
		
		if (x == 0){
			return 0;
		} else if (x < 4){
			return 1;
		}
		
		for (int y = 0; y <= 46340; y++) {
			if (y * y > x) {
				return y - 1;
			}
		}
		
		return 46340;
		
	}
	
	public static void main(String[] args) {
		Sqrt s = new Sqrt();
		
		System.out.println(s.mySqrt(2147395600));
	}
}
