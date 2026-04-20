package 动态规划;

public class Q0152乘积最大子数组 {
    public static void main(String[] args) {
        int[] nums = {-1,-2,-9,-6};
        System.out.println(maxProduct(nums));
    }
    public static int maxProduct(int[] nums) {
        //dp[i]表示以nums[i]结尾的最大乘积的数组
        //dpMax[i] = max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i])
        //dpMin[i] = min(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i])
        //遍历时，每次决定是否以当前值为新起点，即使用nums[i]的值
        int len = nums.length;
        int[] dpMax = new int[len];
        int[] dpMin = new int[len];
        int result = nums[0];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            dpMax[i] = Math.max(Math.max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i]), nums[i]);
            dpMin[i] = Math.min(Math.min(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i]), nums[i]);
            result = Math.max(dpMax[i], result);
        }
        return result;
    }
}
