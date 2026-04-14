import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Q0054 {
//    public static void main(String[] args){
//        int[][] in = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
//        spiralOrder(in);
//        for(int i =0;i<in.length;i++){
//            System.out.print(Arrays.toString(in[i]));
//        }
//    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        if(m==0)    return res;
        int n = matrix[0].length;
        //  基于四个边界进行
        int up = 0, down = m-1, right = n-1, left = 0;
        while(res.size()<m*n){
            //往右走（上边界上，走完++）
            for(int i = left; i<=right;i++){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[up][i]);
            }
            up++;

            //往下走（右边界上，走完--）
            for(int i = up; i<=down;i++){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[i][right]);
            }
            right--;

            //往左走（下边界上，走完--）
            for(int i = right; i>=left;i--){
                if (res.size() >= m * n)
                    return res;
                res.add(matrix[down][i]);
            }
            down--;

            //往上走（左边界上，走完++）
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
