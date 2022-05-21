package LeetCode;

public class AddBinary {
	
	public int binaryToInt(String s) {
		System.out.println(s);
		
		int l = s.length();
		int sum = 0;
		
		for (int i = 0; i < l; i++) {
			if(s.charAt(i) == '1') {
				int sublen = l - i;
				sum = sum + ((int) Math.pow(2, (sublen - 1)));
			}
		}
		
		System.out.println(sum);
		return sum;
	}
	
	public int addInteger (int a, int b) { 
		
		int s = a + b;
		
		System.out.println(s);
		return s;
	}
	
	public String intToBinary(int s) {
		
		String SumString = Integer.toBinaryString(s);
		
		return SumString;   
	}
	
	public static void main(String args[]) {
		AddBinary AD = new AddBinary();
		
		int a = AD.binaryToInt("10111001001010101001001011011100100101010100100101101110010010101010010010110111001001010101001001011011100100101010100100101101110010010101010010010110111001001010101001001011011100100101010100100101101110010010101010010010110111001001010101001001011011100100101010100100101");
		int b = AD.binaryToInt("11001000101011101010010011100100010101110101001001110010001010111010100100111001000101011101010010011100100010101110101001001110010001010111010100100111001000101011101010010011100100010101110101001001110010001010111010100100111001000101011101010010011100100010101110101001001");
		
		int SumInt = AD.addInteger(a, b);
		
		String SumString = AD.intToBinary(SumInt);
		
		System.out.println(SumString);
	}
}

/*
class Solution {
    
    public String addBinary(String a, String b) {
        int ai = binaryToInt(a);
		int bi = binaryToInt(b);
		
		int SumInt = addInteger(ai, bi);
		
		String SumString = intToBinary(SumInt);
		
		return SumString;
    }
    
    public int binaryToInt(String s) {
		System.out.println(s);
		
		int l = s.length();
		int sum = 0;
		
		for (int i = 0; i < l; i++) {
			if(s.charAt(i) == '1') {
				int sublen = l - i;
				sum = sum + ((int) Math.pow(2, (sublen - 1)));
			}
		}
		
		System.out.println(sum);
		return sum;
	}
	
	public int addInteger (int a, int b) { 
		
		int s = a + b;
		
		System.out.println(s);
		return s;
	}
	
	public String intToBinary(int s) {
		
		String SumString = Integer.toBinaryString(s);
		
		return SumString;   
	}
}
*/
