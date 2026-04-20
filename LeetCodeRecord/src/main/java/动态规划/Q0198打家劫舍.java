package 动态规划;

public class Q0198打家劫舍 {
    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        System.out.println(rob(nums));
    }
    public static int rob(int[] nums) {
        //dp[i]记录前i间最多偷得
        //偷i家或者不偷i家
        int len = nums.length;
        if(len==1)  return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2;i<len;i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[len-1];
    }
}
