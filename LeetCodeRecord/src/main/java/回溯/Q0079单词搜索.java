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
        // 遍历每一个格子作为起点,只要有一个起点能成功，就返回 true
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(DFS(board, visited, new StringBuilder(word), i, j, 0))
                    return true;
            }
        }
        return false;
    }
    static boolean DFS(char[][] board, boolean[][] visited, StringBuilder target,int row, int col, int index) {
        //i, j 越界 或者 字符不匹配，直接返回 false
        if(row<0||row> board.length-1||col<0||col> board[0].length-1||visited[row][col]||board[row][col]!=target.charAt(index)){
            return false;
        }
        if(index==target.length()-1){return true;}//终止条件：已经匹配到单词的最后一个字母

        visited[row][col] = true;
        boolean res =   DFS(board, visited, target, row+1, col,index+1)||
                        DFS(board, visited, target, row, col+1,index+1 )||
                        DFS(board, visited, target, row-1, col,index+1 )||
                        DFS(board, visited, target, row, col-1,index+1 );
        visited[row][col] = false;

        return res;
    }
}
