package Q0283;

public class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length, l = 0, r = 0, temp = -1;
        while (r < n) {
            if(nums[r] != 0){
                temp = nums[r];
                nums[r] = nums[l];
                nums[l] = temp;
                l++;
            }
            r++;
        }
    }
}
