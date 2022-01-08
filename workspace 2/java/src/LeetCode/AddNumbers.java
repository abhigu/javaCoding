package LeetCode;

public class AddNumbers {
	public static void main(String[] args) {
		NodeList num1 = new NodeList();
		num1.next = new NodeList(2);
		num1.next.next = new NodeList(4);
		num1.next.next.next = new NodeList(3);
		
		NodeList num2 = new NodeList();
		num2.next = new NodeList(5);
		num2.next.next = new NodeList(6);
		num2.next.next.next = new NodeList(4);

	}
}
