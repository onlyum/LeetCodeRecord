package 二叉树;

import java.util.HashMap;
import java.util.Map;

public class Q0437路径总和三 {
    public static void main(String[] args){
        int targetSum = 22;
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1});
        System.out.println(pathSum(root, targetSum));
    }
    static int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();//HashMap记录当前分支前缀和出现次数，前缀和：个数
        prefix.put(0L, 1);//最重要初始化，从根节点开始的分支必须计入
        return DFS(root, targetSum, prefix, 0);
    }
    static int DFS(TreeNode root, int targetSum, Map<Long, Integer> prefix, long curSum){
        if(root==null) return 0;
        curSum +=root.val;//当前前缀和
        int count = prefix.getOrDefault(curSum - targetSum,0);//个数

        //进子树前
        prefix.put(curSum, prefix.getOrDefault(curSum,0)+1);
        //进子树
        count += DFS(root.left,targetSum,prefix,curSum);
        count += DFS(root.right,targetSum,prefix,curSum);
        //回父节点
        prefix.put(curSum, prefix.getOrDefault(curSum,0)-1);

        return count;
    }
}
