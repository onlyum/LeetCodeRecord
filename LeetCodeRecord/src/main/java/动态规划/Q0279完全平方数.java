package 动态规划;

public class Q0279完全平方数 {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(numSqure(n));
    }
    public static int numSqure(int n){
        int[] dp = new int[n+1];//dp[i]表示和为i的完全平方数最少数量
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            int min = Integer.MAX_VALUE;
            for(int j=1;j*j<=i;j++){//直接先去掉一个平方数，找剩下最少的
                min = Math.min(min, dp[i-j*j]);
            }
            dp[i] = min + 1;//最后加1
        }
        return dp[n];
    }
}


//public class Q0279完全平方数 {
//    public int numSqure(int n){
//        int[] f = new int[n+1];
//        f[0] = 0;
//        for(int i = 1;i<=n;i++){
//            int min = Integer.MAX_VALUE;
//            for(int j = 1;j*j<=i;j++){
//                min = Math.min(f[i-j*j], min);
//            }
//            f[i] = min + 1;
//        }
//        return f[n];
//    }
//}


