package 二叉树;

public class Q0124二叉树中的最大路径和 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{9,6,-3,null,null,-6,2,null,null,2,null,-6,-6,-6});
        System.out.print(maxPathSum(root));
    }
    static int maxSum = Integer.MIN_VALUE;

    static int maxPathSum(TreeNode root) {
        DFS(root);
        return maxSum;
    }

    static int DFS(TreeNode node) {//返回以当前节点的最大贡献值
        if (node == null) {
            return 0;
        }

        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(DFS(node.left), 0);
        int rightGain = Math.max(DFS(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int curMaxPath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, curMaxPath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
//    static int res;
//    static int maxPathSum(TreeNode root) {
//        res = Integer.MIN_VALUE;
//        DFS(root);
//        return res;
//    }
//    static int DFS(TreeNode root){//返回以当前节点为结尾的最大路径和
//        if(root==null) return 0;
//
//        int leftMaxSum = DFS(root.left);
//        int rightMaxSum = DFS(root.right);
//        //取 加左/加右/都加/都不加 中最大
//        int curMaxSum = Math.max(Math.max(leftMaxSum, rightMaxSum), Math.max(leftMaxSum+rightMaxSum,0)) + root.val;
//
//        res = Math.max(res, curMaxSum);
//        //取 加上左边/加上右边/都不加 中最大
//        return Math.max(Math.max(leftMaxSum, rightMaxSum), 0) + root.val;
//    }
}
