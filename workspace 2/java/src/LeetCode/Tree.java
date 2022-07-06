package LeetCode;

public class Tree {

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int leftSide = maxDepth(root.left) + 1;
		
		int rightSide = maxDepth(root.right) + 1;
		
		if (leftSide > rightSide) {
			return leftSide;
			
		} else if (rightSide > leftSide) {
			return rightSide;
			
		} else {
			return leftSide;
			
		}
		
	}
	
	public static void main (String[] args) {
		
		TreeNode root = new TreeNode(3);
		TreeNode nine = new TreeNode(9);
		TreeNode twenty = new TreeNode(20);	
		TreeNode fifteen = new TreeNode(15);
		TreeNode seven = new TreeNode(7);
		
		root.left = nine;
		root.right = twenty; 
		
		twenty.left = fifteen;
		twenty.right = seven;
		
		Tree test = new Tree();
		
		System.out.println(test.maxDepth(root));
	
	}
}
