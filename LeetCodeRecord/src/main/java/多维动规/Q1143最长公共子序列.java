package 多维动规;

public class Q1143最长公共子序列 {
    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
    }
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];//dp[i][j]表示t1前i个字符和t2前j个字符的LCS
        for(int i=1;i<=m;i++){
            char c1 = text1.charAt(i-1);
            for(int j=1;j<=n;j++){
                char c2 = text2.charAt(j-1);
                if(c1==c2)  dp[i][j] = dp[i-1][j-1] + 1;//字符相同则长度加1
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);//字符不同则取最大长度
            }
        }
        return dp[m][n];
    }
}
