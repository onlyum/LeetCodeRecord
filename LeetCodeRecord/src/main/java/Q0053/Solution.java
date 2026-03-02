package Q0053;

public class Solution {
    // 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int maxRes = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + Math.max(dp[i-1], 0);
            maxRes = Math.max(maxRes, dp[i]);
        }
        return maxRes;
    }
}
