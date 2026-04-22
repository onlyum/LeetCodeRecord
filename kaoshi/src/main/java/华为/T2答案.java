package 华为;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class T2答案 {

    // 简化节点结构，不需要 parent 和 preSum
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // 1. 处理空输入边界
        if (s == null || s.trim().isEmpty() || s.equals("[]")) {
            System.out.println(0);
            return;
        }

        // 2. 字符串预处理
        s = s.trim();
        s = s.substring(1, s.length() - 1); // 去掉头尾中括号
        if (s.isEmpty()) {
            System.out.println(0);
            return;
        }

        String[] parts = s.split(",");

        // 3. 构建二叉树
        TreeNode root = buildTree(parts);

        // 4. 计算平衡路径数量 (和为0的路径)
        System.out.println(pathSum(root, 0));
    }

    // 标准的二叉树层序反序列化
    private static TreeNode buildTree(String[] parts) {
        String first = parts[0].trim();
        if (first.equals("None")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(first));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty() && i < parts.length) {
            TreeNode curr = queue.poll();

            // 处理左孩子
            if (i < parts.length) {
                String leftVal = parts[i].trim();
                if (!leftVal.equals("None")) {
                    curr.left = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(curr.left); // 只有非空节点才进队列
                }
                i++;
            }

            // 处理右孩子
            if (i < parts.length) {
                String rightVal = parts[i].trim();
                if (!rightVal.equals("None")) {
                    curr.right = new TreeNode(Integer.parseInt(rightVal));
                    queue.offer(curr.right); // 只有非空节点才进队列
                }
                i++;
            }
        }
        return root;
    }

    // 记录路径上的前缀和频率，使用 Long 防止大数溢出
    static Map<Long, Integer> prefixMap = new HashMap<>();
    static int target = 0;

    public static int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        // 初始化：前缀和为0的路径默认有一条（即刚开始还没加任何节点时）
        prefixMap.put(0L, 1);
        return dfs(root, 0L);
    }

    private static int dfs(TreeNode node, long currSum) {
        if (node == null) return 0;

        // 计算当前前缀和
        currSum += node.val;

        // 核心：当前前缀和 - 目标值 = 需要寻找的历史前缀和
        // 如果历史路径中存在这个前缀和，说明中间这段子路径和刚好为 target (0)
        int count = prefixMap.getOrDefault(currSum - target, 0);

        // 将当前前缀和加入哈希表，继续向下遍历
        prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);

        // 递归累加左右子树中符合条件的路径数
        count += dfs(node.left, currSum);
        count += dfs(node.right, currSum);

        // 回溯 (Backtracking)：重点！
        // 当这个节点及其子树遍历完后，向上返回时，需要把它的前缀和从 Map 中移出，
        // 保证这个前缀和不会被平行分支的节点错误使用。
        prefixMap.put(currSum, prefixMap.get(currSum) - 1);

        return count;
    }
}
