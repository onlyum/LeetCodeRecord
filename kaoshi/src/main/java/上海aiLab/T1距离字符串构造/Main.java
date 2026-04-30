package 上海aiLab.T1距离字符串构造;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int[] input = new int[25];
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 25; i++) {
            StringBuilder temp = new StringBuilder();
            input[i] = Integer.parseInt(st.nextToken());
            for(int j = 0; j < input[i]; j++){
                temp.append(alphabet[i]);
            }
            res.append(temp);
        }
        StringBuilder temp = new StringBuilder("z");
        res.append(temp);
        pw.println(res.length());
        pw.println(res.toString());
        pw.flush();
        pw.close();
    }
}
