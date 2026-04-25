package 二叉树;

import java.util.*;

public class Q0199二叉树的右视图 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{1,2,3,null,5,null,4});

        System.out.println(rightSideView(root));
    }
    static List<Integer> rightSideView(TreeNode root) {
        //层序遍历，每层最后一个记下来
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if(root==null)  return res;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode cur = queue.poll();
                if(cur.left!=null)  queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
                if(i==size-1)   res.add(cur.val);
            }
        }
        return res;
    }
}
