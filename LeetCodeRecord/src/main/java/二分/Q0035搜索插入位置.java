package 二分;

public class Q0035搜索插入位置 {
    public static void main(String[] args){
        int[] nums = {1,3,5,6};
        int target = 4;
        System.out.println(searchInsert(nums, target));
    }
    static int searchInsert(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] < target){
                left = mid + 1;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return left;
    }
}
