/*
189.Rotate Array
 */

//public class 动态规划.Solution {
//    public void rotate(int[] nums, int k) {
//        if (nums.length == 0 || nums.length == 1)   return;
//        k = k % nums.length;
//        reverse(nums, 0, nums.length - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, nums.length - 1);
//    }
//    public void reverse(int[] nums, int start, int end){
//        for(int i = start, j = end; i < j; i++, j--){
//            int temp = nums[i];
//            nums[i] = nums[j];
//            nums[j] = temp;
//        }
//    }
//}

import java.util.Arrays;

//20260413
class Q0189 {
    public static void main(String[] args){
        int[] in = {1,2,3,4,5,6,7};
        int k = 3;
        rotate(in, k);
        System.out.println(Arrays.toString(in));
    }
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        if(len==1 || k==0)  return;
        rotate(nums, 0,len-1);
        rotate(nums, 0,k-1);
        rotate(nums, k,len-1);
    }
    public static void rotate(int[] nums, int start,int end) {//前闭后闭
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
