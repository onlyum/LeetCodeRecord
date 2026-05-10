package 二分;

public class Q0074搜索二维矩阵 {
    public static void main(String[] args){
        int[][] nums = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 20;
        System.out.println(searchMatrix(nums, target));
    }
    static boolean searchMatrix(int[][] nums, int target) {
        int m = nums.length, n = nums[0].length;
        int left = 0,right = m*n-1;
        while(left <= right){
            int mid = left + (right-left)/2;
            int val = nums[mid/n][mid%n];
            if(val < target){
                left = mid + 1;
            }else if(val > target) {
                right = mid - 1;
            }else {
                return true;
            }
        }
        return false;
    }
//    static boolean searchMatrix(int[][] nums, int target) {
//        int left = 0,right = nums.length-1;
//        int mid = 0;
//        while(left <= right){
//            mid = left + (right-left)/2;
//            if(nums[mid][0] < target){
//                left = mid + 1;
//            }else if(nums[mid][0] > target) {
//                right = mid - 1;
//            }else {
//                return true;
//            }
//        }
//
//        int row = right;
//        if (row < 0) return false;
//        left = 0;
//        right = nums[row].length-1;
//        while(left <= right){
//            int subMid = left + (right-left)/2;
//            if(nums[row][subMid] < target){
//                left = subMid + 1;
//            }else if(nums[row][subMid] > target) {
//                right = subMid - 1;
//            }else {
//                return true;
//            }
//        }
//        return false;
//    }
}
