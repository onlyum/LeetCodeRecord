package 二叉树;

public class Q0108将有序数组转换为二叉搜索树 {
    public static void main(String[] args){
//        TreeNode root = TreeNode.buildByLevelOrder(new Integer[]{4,2,7,1,3,6,9});

        TreeNode.printTree(sortedArrayToBST(new int[]{-10,-3,0,3,5,9}));
    }
    static TreeNode sortedArrayToBST(int[] nums) {
        //二分
        return DFS(nums, 0, nums.length-1);
    }
    static TreeNode DFS(int[] nums, int left, int right){
        if(left>right)  return null;
        int mid = (left+right+1)/2;//左子树重，根节点靠右
        TreeNode root = new TreeNode(nums[mid]);
        root.left = DFS(nums, left, mid-1);
        root.right = DFS(nums, mid+1,right);
        return root;
    }
}
