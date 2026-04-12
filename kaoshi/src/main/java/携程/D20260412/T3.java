package 携程.D20260412;
/*
向右移动 和 向左移动可能相撞

3
4 3
0 2 6 9
1001
5 2
-2 -1 0 1 2
01010
2 1
5 7
10

2
3
1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class T3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens())  return;
        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            long[] x = new long[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                x[i] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            char[] dir = st.nextToken().toCharArray();

            pw.println(meet(x, dir, k));
        }

        pw.flush();
        pw.close();
    }

    public static int meet(long[] x, char[] dir, int time){
        long[] xNew = new long[x.length];
        int res = 0;
        for(int i=0;i<x.length;i++){
            if(dir[i]=='0'){
                xNew[i] = x[i] - time;
            }else if(dir[i]=='1'){
                xNew[i] = x[i] + time;
            }
        }

        for(int i=0;i<x.length;i++){
            if(dir[i]=='1'){//向右的为基准
                //向右的初始 < 向左的初始 且 向右的结束 > 向左的结束 才能相遇
                for(int j=0;j<x.length;j++){//找向左的满足条件的
                    if(dir[j]=='0'&&xNew[i]>=xNew[j] && x[i] < x[j]){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
