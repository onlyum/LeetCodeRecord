package 动态规划;

public class Q0416分割等和子集 {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        System.out.println(canPartition(nums));
    }
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num:nums) sum+=num;
        if(sum%2==1) return false;
        int target = sum/2;

        int len = nums.length;
        //dp[j] 表示是否能凑出和为 j
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int num:nums){
            for(int j=target;j>=num;j--){
                dp[j] = dp[j] || dp[j-num];
            }
            if(dp[target])  return true;
        }

        return dp[target];
    }
}
