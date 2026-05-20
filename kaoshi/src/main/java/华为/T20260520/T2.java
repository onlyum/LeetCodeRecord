package 华为.T20260520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class T2 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String st = br.readLine();
        String[] preStr = st.split(" ");
        int n = preStr.length;
        int[] preorder = new int[n];
        for(int i = 0; i < n; i++){
            preorder[i] = preStr[i].charAt(0) - '0';
        }
        st = br.readLine();
        String[] inStr = st.split(" ");
        int[] inorder = new int[n];
        for(int i = 0; i < n; i++){
            inorder[i] = Integer.parseInt(inStr[i]);
        }

        TreeNode head = buildTree(preorder, inorder);
        pw.flush();
        pw.close();
    }

    /*
1 2 3 4 5 6
2 1 3 5 4 6
 */
    private static HashMap<Integer, Integer> indexMap = new HashMap<>();
    //根据前序和中序返回后序
    static TreeNode buildTree(int[] preorder,int[] inorder){
        for(int i = 0; i < inorder.length; i++){
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder,0, preorder.length  - 1, inorder, 0, inorder.length - 1);
    }
    static TreeNode buildTree(int[] preorder,int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        if(preStart > preEnd){//前序走不下去，中序肯定也结束了
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inIndex = indexMap.get(rootVal);//中序遍历的root位置
        int leftLength = inIndex - inStart;//左子树长度

        root.left = buildTree(preorder, preStart + 1, preStart + leftLength, inorder, inStart, inIndex - 1);
        root.right = buildTree(preorder, preStart + leftLength + 1, preEnd, inorder, inIndex + 1, inEnd);
        return root;
    }
}
