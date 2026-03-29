package Q0120;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>(); // 放入当前层所有的节点

        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> cur = new ArrayList<>();  // 放入当前层所有节点的值
            int curSize = queue.size();
            for(int i=0; i<curSize ; i++){
                TreeNode node= queue.poll();
                cur.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(cur);
        }
        return res;

    }
}
