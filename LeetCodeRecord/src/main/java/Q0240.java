/*
240. 搜索二维矩阵 II
每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
 */
public class Q0240 {
    public static void main(String[] args){
        int[][] in = {{1,4,7,11,15},{2,5,8,12,19},{2,5,8,12,19},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
        System.out.print(searchMatrix(in, target));
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        //从右上角开始，排除法（找处于中值的角点，从左下角开始也可以）
        //<target说明当前行没有答案，排除，行++
        //>target说明当前列没有答案，排除，列--
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
//public class Q0240 {
//    public static void main(String[] args){
//        int[][] in = {{1,4,7,11,15},{2,5,8,12,19},{2,5,8,12,19},{10,13,14,17,24},{18,21,23,26,30}};
//        int target = 5;
//        System.out.print(searchMatrix(in, target));
//    }
//    public static boolean searchMatrix(int[][] matrix, int target) {
//
//    }
//}
