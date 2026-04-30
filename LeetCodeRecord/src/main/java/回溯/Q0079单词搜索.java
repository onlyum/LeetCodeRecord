package 回溯;

public class Q0079单词搜索 {
    public static void main(String[] args) {
        char[][] board = {  {'A','B','C','E'},
                            {'S','F','C','S'},
                            {'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }
    static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(DFS(board, visited, new StringBuilder(word), i, j, 0))
                    return true;
            }
        }
        return false;
    }
    static boolean DFS(char[][] board, boolean[][] visited, StringBuilder target,int row, int col, int index) {
        if(row<0||row> board.length-1||col<0||col> board[0].length-1||visited[row][col]||board[row][col]!=target.charAt(index)){
            return false;
        }
        if(index==target.length()-1){return true;}//字符串为目标的就以字符串索引到达长度为结束条件

        visited[row][col] = true;
        boolean res =   DFS(board, visited, target, row+1, col,index+1)||
                        DFS(board, visited, target, row, col+1,index+1 )||
                        DFS(board, visited, target, row-1, col,index+1 )||
                        DFS(board, visited, target, row, col-1,index+1 );
        visited[row][col] = false;

        return res;
    }
}
