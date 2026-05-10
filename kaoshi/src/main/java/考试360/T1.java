package 考试360;

import java.util.*;
import java.io.*;

public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;

        int n = Integer.parseInt(st.nextToken());
        Long m = Long.parseLong(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        //2^45次方以内
        int LOG = 45;
        int[][] nextIdx = new int[n][LOG];
        long[][] curSum = new long[n][LOG];

        for(int i=0;i<n;i++){
            curSum[i][0] = a[i];
            nextIdx[i][0] = (a[i] + i) % n;
        }

        for(int j=1;j<LOG;j++){
            for(int i=0;i<n;i++){
                int curIdx = nextIdx[i][j-1];
                nextIdx[i][j] = nextIdx[curIdx][j-1];
                curSum[i][j] = curSum[i][j-1] + curSum[curIdx][j-1];
            }
        }

        long res = 0;
        int newIdx = 0;
        for(int j=LOG-1;j>0;j--){
            if(((m>>1)&1) == 1){
                res += curSum[newIdx][j];
                newIdx = nextIdx[newIdx][j];
            }
        }

        pw.println(res);
        pw.flush();
        pw.close();
    }
}

