package Q0238;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] beforeSelf = new int[nums.length];
        beforeSelf[0] = 1;
        int[] afterSelf = new int[nums.length];
        afterSelf[nums.length - 1] = 1;
        int[] result = new int[nums.length];
        int len = nums.length;
        int left = 1, right = len - 2, index = 0;
        for (;left<len;left++, right--) {
            beforeSelf[left] = beforeSelf[left-1] * nums[left - 1];
            afterSelf[right] = afterSelf[right+1] * nums[right + 1];
        }
        for(int i=0;i<len;i++){
            result[i] = beforeSelf[i] * afterSelf[i];
        }
        return result;
    }
}
