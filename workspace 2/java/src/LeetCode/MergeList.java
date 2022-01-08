package LeetCode;

public class MergeList {
	
	public static void printList(NodeList list) {
		/*
		System.out.println(list.val);
		System.out.println(list.next.val);
		System.out.println(list.next.next.val);
		*/
		
		NodeList current = list;
		
		while(current != null) {
			System.out.println (current.val);
			current = current.next; 
		}
	}
	
	public static void main(String[] args) {
		NodeList list1 = new NodeList(1);
		list1.next = new NodeList(2);
		list1.next.next = new NodeList(3);
		
		NodeList list2 = new NodeList(4);
		list2.next = new NodeList(5);
		
		
		NodeList list3 = new NodeList();
		NodeList current = list3;
		
		while(list1 != null && list2 !=  null) {
			if(list1.val > list2.val) {
				current.next = list2;
				list2 = list2.next;
			} else {
				current.next = list1;
				list1 = list1.next;
			}
			current = current.next;
		}
		
		if(list1 != null) {
			current.next = list1;
		} else {
			current.next = list2;
		}
		
		
		printList(list3.next);
	}
}
