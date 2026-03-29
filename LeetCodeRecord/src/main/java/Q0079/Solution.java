package Q0079;

class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        // 遍历每一个格子作为起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 只要有一个起点能成功，就返回 true
                if (backtracking(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean backtracking(char[][] board, char[] word, int i, int j, int k) {
        // 1. 边界检查 & 字符匹配检查
        // i, j 越界 或者 字符不匹配，直接返回 false
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word[k]) {
            return false;
        }

        // 2. 终止条件：已经匹配到单词的最后一个字母
        if (k == word.length - 1) return true;

        // 3. 做选择：标记当前格子已访问
        char temp = board[i][j];
        board[i][j] = '\0'; // 用特殊字符占位，防止这一轮递归再踩回来

        // 4. 纵向探索：四个方向（上下左右）
        // 只要有一条路通了，res 就是 true
        boolean res = backtracking(board, word, i + 1, j, k + 1) ||
                backtracking(board, word, i - 1, j, k + 1) ||
                backtracking(board, word, i, j + 1, k + 1) ||
                backtracking(board, word, i, j - 1, k + 1);

        // 5. 撤销选择（回溯）：恢复现场
        board[i][j] = temp;

        return res;
    }
}