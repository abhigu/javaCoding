package LeetCode;

import java.util.Arrays;

public class TwoSumAdvanced {
	
	public static int[] twoSum(int[] nums, int target) {
		
		//Logic: Look at first number in list, then check every number after that, then iterate to next number, check every number after that, ect.
		//Coding: Use two for loops to loop between the starting number and the checking numbers, and use an if statement to check before reaching the end to avoid an out of bounds error. 
		
		Arrays.sort(nums);
		
		int small = 0;
		int large = nums.length;
		
		while(small < large) {
			if (nums[small] + nums[large] > target) {
				large--;
			} else if (nums[small] + nums[large] < target) {
				small++;
			} else {
				return new int[] {small, large};
			}
		}
		return new int[2];
	}
	
	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 9; 
		
		int[] ans = twoSum(nums, target);
		for(int value : ans) {
			System.out.print(value + " ");
		}
	}
}

