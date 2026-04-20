import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q0139单词拆分 {
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        System.out.println(wordBreak(s, wordDict));
    }
    public static boolean wordBreak(String s, List<String> wordDict) {
        //dp[i]表示s的前i个字符（0到i-1）能否拼接成，结果就是dp[s.len]
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;//默认空字符可拆分
        Set<String> dic = new HashSet<>(wordDict);

        for(int i=1;i<s.length()+1;i++){
            for(int j=0;j<i;j++){
                dp[i] = dp[j] && (dic.contains(s.substring(j,i)));
                if(dp[i]) break;
            }
        }
        return dp[s.length()];
    }

}
