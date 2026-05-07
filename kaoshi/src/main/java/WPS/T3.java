package WPS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
//T3 没写出来
//给定两个正整数a和b，求[a,b]中的所有整数中，每个数码(digit出现多少次)？
public class T3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        Long a = Long.parseLong(st.nextToken());
        Long b = Long.parseLong(st.nextToken());
//        pw.print(a);
//        pw.print(b);
        pw.print(Arrays.toString(solution(a, b)));

        pw.flush();
        pw.close();
    }
    static int[] solution(Long a, Long b){
        int[] count = new int[10];
        //找每个位置的最大值和最小值
        //01 99
        //数字0 最高位一次*(10-1)
        //数字2 最高位一次*10+低位一次*10
        //个位最小0最大9 十位最小0最大9
        //最高位[9,0]

        //111 999
        //数字0 最高位0次 + 次位一次*10*（10-1）
        //数字2 首位一次*100+低位一次*100+最低位一次*100
        return count;
    }
}
