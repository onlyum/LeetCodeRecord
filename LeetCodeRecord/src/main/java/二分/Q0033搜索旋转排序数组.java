package 二分;

public class Q0033搜索旋转排序数组 {
    public static void main(String[] args){
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }
    static int search(int[] nums, int target) {
        //跟right比较
        //>right说明有拐点，找右半部分
        //<right说明没拐点，找左半部分
        int left =0,right = nums.length-1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[0] <= nums[mid]){//0，mid为增
                if(nums[0] <= target && target < nums[mid]){//收缩右边界，右边无等号
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{//旋转点在中间
                if(nums[mid] < target && target <= nums[right]){//收缩左边界，左边无等号
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
