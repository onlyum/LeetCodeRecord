public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = searchFirstColumn(matrix, target);
        if(row == -1) return false;
        return searchRow(matrix[row], target);
    }
    public int searchFirstColumn(int[][] matrix, int target){
        int left=-1,right=matrix.length-1;
        while(left<right){
            int mid = left + (right - left + 1)/2;
            if(matrix[mid][0]<=target){
                left=mid;
            }else{
                right=mid-1;
            }
        }
        return left;
    }
    public boolean searchRow(int[] matrix, int target){
        int left=0,right=matrix.length-1;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(matrix[mid]==target){
                return true;
            }else if(matrix[mid]>target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return false;
    }
}