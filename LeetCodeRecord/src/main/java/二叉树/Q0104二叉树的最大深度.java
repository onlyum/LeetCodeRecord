package 二叉树;

public class Q0104二叉树的最大深度 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{3,9,20,null,null,15,7});
        System.out.println(maxDepth(root));
    }
    static int maxDepth(TreeNode root) {
        return dfs(root);
    }
    static int dfs(TreeNode root){
        if(root==null)  return 0;
        else{
            int leftH = dfs(root.left);
            int rightH = dfs(root.right);
            return Math.max(leftH, rightH) + 1;
        }
    }
}
