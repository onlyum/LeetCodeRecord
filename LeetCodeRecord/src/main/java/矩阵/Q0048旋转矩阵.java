package 矩阵;

/*
48.旋转矩阵
给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Q0048旋转矩阵 {
    public static void main(String args[]){
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);
        for(int i = 0 ; i < matrix.length;i++){
            for(int j = 0 ;j < matrix[0].length;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }
    public static void rotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        //  水平翻转 + 对角线翻转
        for(int i = 0 ; i < m/2;i++){
            for(int j = 0 ;j < n;j++){
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[m-i-1][j];
                matrix[m-i-1][j] = temp;
            }
        }
        for(int i = 0 ; i < m;i++){
            for(int j = 0 ;j < i;j++){
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
