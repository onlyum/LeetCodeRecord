package 动态规划;

public class Q0070爬楼梯 {
    public static void main(String[] args){
        int n = 2;
        System.out.println(climbStairs(3));
    }
    public static int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
