package Q0114;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(list, root);
        for(int i=1;i<list.size();i++){
            list.get(i-1).left = null;
            list.get(i-1).right = list.get(i);
        }
    }
    public void preorderTraversal(List<TreeNode> list, TreeNode root){
        if(root == null){
            return;
        }
        list.add(root);
        preorderTraversal(list, root.left);
        preorderTraversal(list, root.right);
    }
}
