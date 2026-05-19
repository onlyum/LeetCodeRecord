package 华为.T20260422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // 校验输入维度的合法性
        if (m <= 0 || m > 100 || n <= 0 || n > 100) {
            pw.println(-1);
            pw.flush();
            return;
        }

        int[][] map = new int[m][n];//0可走，1障碍
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur==0?0:1;
            }
        }

        if(map[0][0]==1||map[m-1][n-1]==1){
            pw.println(-1);
            pw.flush();
            pw.close();
            return;
        }

        int INF = 0x3f3f3f3f;
//dp[i][j][0]表示‘右移’到（i，j）的转向次数,dp[i][j][1]表示‘下移’到（i，j）的转向次数
        int[][][] dp = new int[m][n][2];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0] = INF;
                dp[i][j][1] = INF;
            }
        }
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(map[i][j] != 0){//不可达
                    continue;
                }
                if(j>0)//可右移到达
                    dp[i][j][0] = Math.min(dp[i][j-1][0], dp[i][j-1][1]+1);
                if(i>0)//可下移到达
                    dp[i][j][1] = Math.min(dp[i-1][j][1], dp[i-1][j][0]+1);
            }
        }

        int res = Math.min(dp[m-1][n-1][0], dp[m-1][n-1][1]);
        if (res >= INF) {
            pw.println(-1);
        } else {
            pw.println(res);
        }

        pw.flush();
        pw.close();
    }
}
