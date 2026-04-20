package 动态规划;

import java.util.Arrays;

//全背包问题变体
public class Q0322零钱兑换 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }
    public static int coinChange(int[] coins, int amount) {
        //dp[i]表示对整数i最少需要硬币数量
        //dp[i]为Math.min(dp[i-coin])+1,防止不存在初始化dp为大值
        int max = amount+1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for(int i=0;i<=amount;i++){
            for(int coin:coins){
                if(i-coin>=0){//注意=0时，表示当前数值为coin，所以直接dp[0]+1=1
                    dp[i] = Math.min(dp[i-coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount]==max?-1:dp[amount];
    }
}
