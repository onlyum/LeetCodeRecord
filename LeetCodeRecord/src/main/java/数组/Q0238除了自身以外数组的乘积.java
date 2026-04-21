package 数组;/*
238. Product of Array Except Self
 */

//import java.util.Arrays;
//
//public class 数组.Q0238 {
//    public static void main(String[] args){
//        int[] nums = {1,2,3,4};
//        int[] output = productExceptSelf(nums);
//        System.out.print(Arrays.toString(output));
//    }
//    public static int[] productExceptSelf(int[] nums) {
//        int[] beforeSelf = new int[nums.length];
//        beforeSelf[0] = 1;
//        int[] afterSelf = new int[nums.length];
//        afterSelf[nums.length - 1] = 1;
//        int[] result = new int[nums.length];
//        int len = nums.length;
//        int left = 1, right = len - 2, index = 0;
//        for (;left<len;left++, right--) {
//            beforeSelf[left] = beforeSelf[left-1] * nums[left - 1];
//            afterSelf[right] = afterSelf[right+1] * nums[right + 1];
//        }
//        for(int i=0;i<len;i++){
//            result[i] = beforeSelf[i] * afterSelf[i];
//        }
//        return result;
//    }
//}


//20260413
import java.util.Arrays;

public class Q0238除了自身以外数组的乘积 {
    public static void main(String[] args){
        int[] nums = {-1,1,0,-3,3};
        int[] output = productExceptSelf(nums);
        System.out.print(Arrays.toString(output));
    }
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int[] res = new int[nums.length];

        left[0] = 1;
        right[len-1] = 1;
        for(int i=1;i<nums.length;i++){
            left[i] = left[i-1] * nums[i-1];
            right[len-1-i] = right[len-i] * nums[len-i];
        }
        for(int i=0;i<nums.length;i++){
            res[i] = left[i]*right[i];
        }
        return res;
    }
}
