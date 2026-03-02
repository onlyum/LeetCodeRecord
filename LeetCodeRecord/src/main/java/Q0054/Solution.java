package Q0054;
import java.util.List;
import java.util.ArrayList;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        int m = matrix.length;
        if(m==0)    return res;
        int n = matrix[0].length;
        //  基于四个边界进行
        int up = 0, down = m-1, right = n-1, left = 0;
        while(res.size()<m*n){
            //右
            for(int i = left; i<=right;i++){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[up][i]);
            }
            up++;

            //下
            for(int i = up; i<=down;i++){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[i][right]);
            }
            right--;

            //左
            for(int i = right; i>=left;i--){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[down][i]);
            }
            down--;

            //上
            for(int i = down; i>=up;i--){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }
}
