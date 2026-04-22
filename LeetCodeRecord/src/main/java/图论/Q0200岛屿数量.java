package 图论;

import java.util.Arrays;
import java.util.Scanner;

public class Q0200岛屿数量 {
    public static void main(String args[]) {
        char[][] grid =
                {
                        {'1','1','0','0','0'},
                        {'1','1','0','0','0'},
                        {'0','0','1','0','0'},
                        {'0','0','0','1','1'}
                };
        System.out.print(numIslands(grid));
    }
    static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' &&  !visited[i][j]) {
                    count++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return count;
    }
    static void dfs(char[][] grid, int row, int col, boolean[][] visited) {
        if(row < 0||row>= grid.length||col < 0||col >= grid[0].length||visited[row][col]||grid[row][col] == '0') return;
        visited[row][col] = true;
        dfs(grid, row-1, col, visited);
        dfs(grid, row+1, col, visited);
        dfs(grid, row, col-1, visited);
        dfs(grid, row, col+1, visited);
    }

}
