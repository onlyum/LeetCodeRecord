package 二叉树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Q0102二叉树的层序遍历 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{3,9,20,null,null,15,7});
        System.out.println(levelOrder(root));
    }
    static List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null)  return new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.offer(root);
        TreeNode cur = null;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){
                cur = queue.poll();
                list.add(cur.val);
                if(cur.left!=null)  queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            res.add(list);
        }
        return res;
    }
}
