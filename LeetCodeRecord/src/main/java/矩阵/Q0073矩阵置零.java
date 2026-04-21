package 矩阵;

import java.util.Arrays;

/*
73.矩阵置零
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
* */
public class Q0073矩阵置零 {
    public static void main(String[] args){
        int[][] in = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(in);
        for(int i =0;i<in.length;i++){
            System.out.print(Arrays.toString(in[i]));
        }
    }
    public static void setZeroes(int[][] matrix) {
        //  第一行和第一列用来记录，对应的行或列是否需要全0
        int m = matrix.length, n = matrix[0].length;
        boolean firstCol = false, firstRow = false;
        for(int i=0;i<m;i++){
            if(matrix[i][0] == 0){
                firstCol = true;
            }
        }
        for(int i=0;i<n;i++){
            if(matrix[0][i] == 0){
                firstRow = true;
            }
        }

        //  全查询，记录需要置为0的行和列
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }
        //处理第一行，第一列以外的
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        //  处理第一行和第一列
        if(firstCol){
            for(int i=0;i<m;i++){
                matrix[i][0] = 0;
            }
        }
        if(firstRow){
            for(int i=0;i<n;i++){
                matrix[0][i] = 0;
            }
        }
    }
}
