package 二叉树;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class Q0114二叉树展开为链表 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{1,2,5,3,4,null,6});

        flatten(root);

        TreeNode.printTree(root);
    }
    static void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        DFS(root, list);

        for(int i=1;i<list.size();i++){
            list.get(i-1).right = list.get(i);
            list.get(i-1).right = null;
        }
    }
    static void DFS(TreeNode root, List<TreeNode> list){
        if(root==null)  return;

        list.add(root);
        DFS(root.left, list);
        DFS(root.right, list);
    }
}
