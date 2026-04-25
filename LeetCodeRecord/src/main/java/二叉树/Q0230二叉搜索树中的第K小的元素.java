package 二叉树;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Q0230二叉搜索树中的第K小的元素 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{3,1,4,null,2});

        System.out.println(kthSmallest(root, 1));
    }
    static int kthSmallest(TreeNode root, int k) {
        return DFS(root, k);
    }
    static int DFS(TreeNode root, int k){
        Deque<TreeNode> stack = new ArrayDeque<>();//栈顶是当前最小的节点
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){//把最左边全推进去
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();

            k--;
            if(k==0) break;
            cur = cur.right;//左子树全部找完了，换右子树
        }
        return cur.val;
    }
}
