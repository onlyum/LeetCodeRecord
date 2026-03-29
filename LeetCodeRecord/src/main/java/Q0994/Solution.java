package Q0994;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0,  1, 0, -1};
        int res = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        int ROW = grid.length, COL = grid[0].length;
        for(int i=0;i<ROW;i++){
            for(int j = 0;j<COL;j++){
                if(grid[i][j]==2){
                    queue.add(new int[]{i, j, 0});
                }
            }
        }
        while(!queue.isEmpty()){
            int[] temp = queue.remove();
            int r = temp[0], c = temp[1], depth = temp[2];
            for(int k=0;k<4;k++){
                int tr = r + dr[k];
                int tc = c + dc[k];
                if(0<=tr && tr<ROW && 0<=tc && tc<COL){
                    if(grid[tr][tc] == 1){
                        grid[tr][tc] = 2;
                        queue.add(new int[]{tr, tc, depth +  1});
                    }
                }
            }
            res = depth;
        }
        for(int[] row:grid){
            for(int c:row){
                if(c==1){
                    return -1;
                }
            }
        }
        return res;
    }
}