package 二叉树;

public class Q0101对称二叉树 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{1,2,2,3,4,4});
        System.out.println(isSymmetric(root));
    }
    static boolean isSymmetric(TreeNode root) {
        return  DFS(root.left, root.right);
    }
    static boolean DFS(TreeNode p, TreeNode q){//递归查看p,q以及子树是否对称
        if(p==null&&q==null)    return true;
        if(p==null||q==null)    return false;
        return (p.val==q.val)&&DFS(p.left, q.right)&&DFS(p.right, q.left);
    }
}
