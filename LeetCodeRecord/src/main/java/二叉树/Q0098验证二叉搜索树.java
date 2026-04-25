package 二叉树;

public class Q0098验证二叉搜索树 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{5,1,4,null,null,3,6});

        System.out.println(isValidBST(root));
    }
    static boolean isValidBST(TreeNode root) {
        return DFS(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    static boolean DFS(TreeNode root, long lower, long upper){
        if(root == null)    return true;
        long cur = root.val;
        if(cur<=lower||cur>=upper)  return false;
        return DFS(root.left, lower, cur) && DFS(root.right, cur, upper);
    }
}
