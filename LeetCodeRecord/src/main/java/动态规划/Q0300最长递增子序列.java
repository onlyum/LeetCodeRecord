package 动态规划;

import java.util.Arrays;
import java.util.List;

public class Q0300最长递增子序列 {
    public static void main(String[] args) {
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(lengthOfLIS(nums));
    }
    public static int lengthOfLIS(int[] nums) {
        //dp[i]表示以nums[i]为结尾的最长严格递增子序列长度，dp
        //dp[i]=min(dp[j]) + 1当nums[j]<nums[i]
        if(nums==null||nums.length==0)  return 0;
        int len = nums.length;
        int[] dp = new int[len+1];
        int res = 1;
        Arrays.fill(dp, 1);
        for(int i=1;i<len;i++){
            for(int j=0;j<i;j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
