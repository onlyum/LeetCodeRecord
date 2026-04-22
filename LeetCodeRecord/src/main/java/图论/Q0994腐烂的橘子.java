package 图论;

import java.util.ArrayDeque;
import java.util.Queue;

class Q0994腐烂的橘子 {
    public static void main(String[] args) {
        int[][] grid = {
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        System.out.println(orangesRotting(grid));
    }
    static int orangesRotting(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return -1;
        Queue<int[]> queue = new ArrayDeque<>();//队列存当前腐败橘子
        int time = 0;
        int row = grid.length;
        int col = grid[0].length;
        int[] dr = {-1, 0, 1, 0};//上右下左
        int[] dc = {0, 1, 0, -1};
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            boolean rottedThisMinute = false;
            for(int qs = 0;qs < size;qs++){
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                for(int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(nr>=0&&nr<row&&nc>=0&&nc<col){
                        if(grid[nr][nc] == 0) continue;
                        else if(grid[nr][nc] == 1){
                            grid[nr][nc] = 2;
                            rottedThisMinute = true;
                            queue.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            if(rottedThisMinute) time++;
        }
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) return -1;
            }
        }
        return time;
    }
}

//class Q0994腐烂的橘子 {
//    public int orangesRotting(int[][] grid) {
//        int[] dr = {-1, 0, 1, 0};
//        int[] dc = {0,  1, 0, -1};
//        int res = 0;
//        Queue<int[]> queue = new ArrayDeque<>();
//        int ROW = grid.length, COL = grid[0].length;
//        for(int i=0;i<ROW;i++){
//            for(int j = 0;j<COL;j++){
//                if(grid[i][j]==2){
//                    queue.add(new int[]{i, j, 0});
//                }
//            }
//        }
//        while(!queue.isEmpty()){
//            int[] temp = queue.remove();
//            int r = temp[0], c = temp[1], depth = temp[2];
//            for(int k=0;k<4;k++){
//                int tr = r + dr[k];
//                int tc = c + dc[k];
//                if(0<=tr && tr<ROW && 0<=tc && tc<COL){
//                    if(grid[tr][tc] == 1){
//                        grid[tr][tc] = 2;
//                        queue.add(new int[]{tr, tc, depth +  1});
//                    }
//                }
//            }
//            res = depth;
//        }
//        for(int[] row:grid){
//            for(int c:row){
//                if(c==1){
//                    return -1;
//                }
//            }
//        }
//        return res;
//    }
//}