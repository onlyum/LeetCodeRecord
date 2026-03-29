package Q0055;

public class Solution {
    public boolean canJump(int[] nums) {
        int maxArrive = 0;
        for(int i=0;i<nums.length;i++){
            if(i<=maxArrive){
                maxArrive = Math.max(maxArrive, i+nums[i]);
            }
            if(maxArrive >= nums.length - 1){
                return true;
            }
        }
        return false;
    }
}