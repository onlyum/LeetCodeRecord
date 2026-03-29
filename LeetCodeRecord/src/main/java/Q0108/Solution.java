package Q0108;

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return binaryDST(nums, 0, nums.length - 1);
    }
    public TreeNode binaryDST(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = binaryDST(nums, left, mid-1);
        root.right = binaryDST(nums, mid+1, right);
        return root;
    }
}
