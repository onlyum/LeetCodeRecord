package 二叉树;

public class Q0236二叉树的最近公共祖先 {
    public static void main(String[] args){
        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode p = root.left;
        TreeNode q = root.right;
        System.out.print(lowestCommonAncestor(root,p,q).val);
    }
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return DFS(root, p, q);
    }
    static TreeNode DFS(TreeNode root, TreeNode p, TreeNode q){//后序遍历，知道左右子树结果后再判断
        if(root==null || root==p || root==q){//找到p/q或到底了，只要返回值不是null说明在子树里找到p/q了
            return root;
        }

        TreeNode left = DFS(root.left, p, q);
        TreeNode right = DFS(root.right, p, q);

        //----根据子树结果判断
        //p/q都找到了，一个左子树一个右子树，说明root为答案
        if(left!=null && right!=null){
            return root;
        }
        //有一个没找到，则说明都在同一边子树
        if(left==null){
            return right;
        }else{
            return left;
        }
    }
}
