package Q0543;

public class Solution {
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        res = 1;
        maxNodeCount(root);
        return res-1;
    }
    public int maxNodeCount(TreeNode p){
        if(p == null)   return 0;
        int L = maxNodeCount(p.left);
        int R = maxNodeCount(p.right);
        res = Math.max(res, L+R+1);
        return Math.max(L, R) + 1;
    }
}
