//package Q0053;
//
//public class 动态规划.Solution {
//    // 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//    public int maxSubArray(int[] nums) {
//        int[] dp = new int[nums.length];
//        int maxRes = nums[0];
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = nums[i] + Math.max(dp[i-1], 0);
//            maxRes = Math.max(maxRes, dp[i]);
//        }
//        return maxRes;
//    }
//}
//

//20260413
public class Q0053 {
    public static void main(String[] args){
        int[] in  = {1,2,-1,-2,2,1,-2,1,4,-5,4};
        System.out.println(maxSubArray(in));
    }
    // 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];//以i为结尾的最大和
        dp[0] = nums[0];
        int maxRes = dp[0];
        for(int i =1;i<len;i++){
            dp[i] = Math.max(nums[i], nums[i]+dp[i-1]);
            maxRes = Math.max(maxRes, dp[i]);
        }
        return maxRes;
    }
}

