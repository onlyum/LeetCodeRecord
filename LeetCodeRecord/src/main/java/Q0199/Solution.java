package Q0199;

import java.util.*;

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>(); // 放入当前层所有的节点
        queue.offer(root);
        while(!queue.isEmpty()){
            int curSize = queue.size();	//记录当前层长度
            for(int i=0; i<curSize ; i++){	//遍历该层所有节点
                TreeNode node= queue.poll();	//遍历过的都出列，并把左右节点入列
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                if(i == curSize-1){
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
