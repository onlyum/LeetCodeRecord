package Q0131;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    boolean[][] dp; // 预处理回文状态表

    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];

        // 1. 动态规划预处理：dp[i][j] 表示 s[i...j] 是否是回文
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }

        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int startIndex) {
        // 2. 终止条件：切割位置到达字符串末尾
        if (startIndex >= s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 3. 遍历可能的切割点
        for (int i = startIndex; i < s.length(); i++) {
            // 如果 s[startIndex...i] 是回文，则继续切割
            if (dp[startIndex][i]) {
                String sub = s.substring(startIndex, i + 1);
                path.add(sub);
                backtrack(s, i + 1); // 递归处理剩余部分
                path.remove(path.size() - 1); // 回溯：撤销选择
            }
        }
    }
}