package 二叉树;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Q0105从前序与中序遍历序列构造二叉树 {
    public static void main(String[] args){
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        Q0105从前序与中序遍历序列构造二叉树 sol = new Q0105从前序与中序遍历序列构造二叉树();
        TreeNode.printTree(sol.buildTree(preorder, inorder));
    }
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        indexMap = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            indexMap.put(inorder[i], i);
        }
        return DFS(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    private TreeNode DFS(int[] preorder, int preStart, int preEnd,
                         int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd)   return null;

        int rootVal = preorder[preStart];//先序找root
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInorder = indexMap.get(rootVal);//中序中索引
        int leftSubTreeSize = rootIndexInorder - inStart;//左子树长度

        root.left = DFS(preorder, preStart+1, preStart+leftSubTreeSize, inorder, inStart, rootIndexInorder-1);
        root.right = DFS(preorder, preStart+leftSubTreeSize+1, preEnd, inorder, rootIndexInorder+1, inEnd);

        return root;
    }
}
