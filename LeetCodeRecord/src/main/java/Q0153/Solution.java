package Q0153;
public class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] < nums[right]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
