package Q0437;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }
    //如果不同路径下（例如左子树某个位置和右子树某个位置）有相同的前缀和，由于深度优先搜索（DFS）的特性，它们在同一时刻不会同时对计数产生逻辑干扰
    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1); //进入子树前：我们将当前路径的前缀和存入Map
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1); //退出子树后：我们将当前前缀和的计数减1

        return ret;
    }
}

