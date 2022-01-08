package LeetCode;

public class NumPal {
	public boolean isPalindrome(int x) {
		String num = String.valueOf(x);
		int middle = (int)num.length()/2;
		
		for(int i = 0; i < middle; i++) {
			if(num.charAt(i) != num.charAt(num.length() - 1 - i)) {
				return false;
			}
		}
	}
}
