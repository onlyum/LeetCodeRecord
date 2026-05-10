package 二分;

import java.util.Arrays;

public class Q0034在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args){
        int[] nums = {5,7,7,8,8,10};
        int target = 2;
        int[] res = searchRange(nums, target);
        System.out.println(Arrays.toString(res));
    }
    static int[] searchRange(int[] nums, int target) {
        int left = searchTarget(nums,target);
        if(left==nums.length||nums[left]!=target) return new int[]{-1,-1};
        int right = searchTarget(nums,target+1)-1;
        return new int[]{left, right};
    }
    static int searchTarget(int[] nums, int target) {
        int left=0,right=nums.length - 1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
