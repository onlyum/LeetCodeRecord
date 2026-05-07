package WPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

//过60%,剩余超时
//求不会爆炸的子串个数（字符串至少有k对相邻字母不同，则会爆炸）
public class T2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        pw.print(solution(s,n,k));

        pw.flush();
        pw.close();
    }
    /*
4 1
abba
     */
    static int solution(String s, int n, int k){
        int count = 0;
        int[] dp = new int[n];//dp[i]表示[0,i]中有多少响铃对数\
        dp[0] = 0;
        for(int i = 1; i < n; i++){
            dp[i] = dp[i-1] +  (s.charAt(i) == s.charAt(i-1) ? 0 : 1);
        }
        for(int i = 0; i < n-1; i++){
            for(int j = i+1; j < n; j++){
                if(dp[j] - dp[i] < k){
                    count++;
                }else{
                    break;
                }
            }
        }

        return count + n;
    }
}
