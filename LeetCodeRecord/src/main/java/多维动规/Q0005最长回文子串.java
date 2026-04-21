package 多维动规;

/*
方法1：动态规划
dp[i][j] = dp[i+1][j-1] && flag
注意依赖下一行和前一列，所以从右下角开始遍历，解决状态依赖问题
 */

public class Q0005最长回文子串 {
    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(longestPalindrome(s));
    }
    public static String longestPalindrome(String s) {
        //dp[i][j]表示(i,j)是否为回文子串，前闭后闭
        int len = s.length();
        int maxStart = 0, maxEnd = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {//一个字母一定回文
            dp[i][i] = true;
        }
        for(int i = len-1; i >= 0; i--) {
            for(int j = i+1; j < len; j++) {
                boolean flag = (s.charAt(i)==s.charAt(j));
                if(j-i>=2)
                    dp[i][j] = dp[i+1][j-1] && flag;
                else
                    dp[i][j] = flag;
            }
        }
        for(int i = 0; i < len; i++) {
            for(int j = i; j < len; j++) {
                if(dp[i][j] && j-i>maxEnd-maxStart) {
                    maxStart = i;
                    maxEnd = j;
                }
            }
        }
        return s.substring(maxStart,maxEnd+1);
    }
}

/*
方法2：Manacher。复杂度只有O(N)
 */
