package Q0240;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x<m && y>=0){
            if(matrix[x][y] == target){
                return true;
            }else if(matrix[x][y] < target){
                x++;
            }else{
                y--;
            }
        }
        return false;
    }
}
