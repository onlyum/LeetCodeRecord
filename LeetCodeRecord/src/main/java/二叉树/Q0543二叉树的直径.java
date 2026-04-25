package 二叉树;

public class Q0543二叉树的直径 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{1,2,3,4,5});
        System.out.println(diameterOfBinaryTree(root));
    }
    static int res = 0;
    static int diameterOfBinaryTree(TreeNode root) {
        res = 1;//由于是静态变量，所以必须重置
        DFS(root);
        return res-1;
    }
    static int DFS(TreeNode p){//以p为起点向下走的最长路径
        if(p==null) return 0;
        int L = DFS(p.left);
        int R = DFS(p.right);
        res = Math.max(res, L+R+1);//更新
        return Math.max(L, R) + 1;
    }
}
