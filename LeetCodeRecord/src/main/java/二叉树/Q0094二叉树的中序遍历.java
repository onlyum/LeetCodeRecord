package 二叉树;

import java.util.ArrayList;
import java.util.List;

public class Q0094二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    static void inorder(TreeNode root, List<Integer> res){
        if(root==null)  return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
