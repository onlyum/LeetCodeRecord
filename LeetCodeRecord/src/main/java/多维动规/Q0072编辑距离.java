package 多维动规;

public class Q0072编辑距离 {
    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }
    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (n * m == 0) {
            return n + m;
        }
        int[][] dp = new int[m+1][n+1];//dp[i][j]表示w1的前i个字符变成w2的前j个字符最少需要的操作数
        for(int i=0;i<=m;i++){//初始化
            dp[i][0] = i;// word1 变为空字符串：全部删除
        }
        for(int j=0;j<=n;j++){
            dp[0][j] = j;// 空字符串变为 word2：全部插入
        }
        for(int i=1;i<=m;i++){
            char c1 = word1.charAt(i-1);
            for(int j=1;j<=n;j++){
                char c2 = word2.charAt(j-1);
                if(c1==c2)  dp[i][j] = dp[i-1][j-1];
                else    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
            }
        }
        return dp[m][n];
    }
}
