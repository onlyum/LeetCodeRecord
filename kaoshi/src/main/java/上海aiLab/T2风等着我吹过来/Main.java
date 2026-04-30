package 上海aiLab.T2风等着我吹过来;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String firstLine = br.readLine();
        if(firstLine == null) return;
        StringTokenizer st = new StringTokenizer(firstLine);
        if(!st.hasMoreTokens()) return;

        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());


            ArrayList<Long> sandHeight = new ArrayList<>();
            long[] winHeight = new long[m];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                sandHeight.add(Long.parseLong(st.nextToken()));
            }
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                winHeight[i] = Long.parseLong(st.nextToken());
            }

            solution(sandHeight, winHeight, n, m);


            for(int i = 0; i < sandHeight.size(); i++){
                pw.print(sandHeight.get(i));
                if(i != sandHeight.size() - 1){
                    pw.print(" ");
                }
            }
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    //直接修改传入的 ArrayList
    static void solution(ArrayList<Long> sandHeight, long[] winHeight, int n, int m){
        for(int i = 0; i < m; i++){ // 总共m阵风
            int size = sandHeight.size();

            for(int j = size - 2; j >= 0; j--){
                long change = Math.min(sandHeight.get(j), winHeight[i]);
                if (change > 0) {
                    sandHeight.set(j + 1, sandHeight.get(j + 1) + change);
                    sandHeight.set(j, sandHeight.get(j) - change);

                    if(sandHeight.get(j) == 0){
                        sandHeight.remove(j);
                    }
                }
            }
        }
    }
}