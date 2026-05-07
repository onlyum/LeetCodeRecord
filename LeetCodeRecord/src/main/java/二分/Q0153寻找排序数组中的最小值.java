package 二分;

public class Q0153寻找排序数组中的最小值 {
    public static void main(String[] args){
        int[] nums = {0,1,2,4,5,6,7};
        System.out.println(findMin(nums));
    }
    static int findMin(int[] nums) {
        int left=0,right = nums.length-1;
        //注意内部有不+1或者-1的分支，则必须left<right做循环条件
        while(left<right){
            // 如果中间值大于右边界值，说明出现了旋转（逆序），最小值肯定在右半部分
            int mid = left + (right-left)/2;
            if(nums[mid] < nums[right]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return nums[right];
    }
}
