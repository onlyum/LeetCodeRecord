package 二叉树;

public class Q0226翻转二叉树 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{4,2,7,1,3,6,9});
        TreeNode.printTree(invertTree(root));
    }

    static TreeNode invertTree(TreeNode root) {
        return DFS(root);
    }
    static TreeNode DFS(TreeNode root){
        if(root == null) return root;
        TreeNode newRight = DFS(root.left);
        TreeNode newLeft = DFS(root.right);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }
}
