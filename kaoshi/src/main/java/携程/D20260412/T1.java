package 携程.D20260412;
/*
给定n，找x+y=2n且x,y都是合数
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class T1 {
    static int[] heShu = new int[2000000+5];//记录i是否为合数,2为质数1为合数

    public static void main(String[] args) throws IOException {
        heShu[0] = 2;//1,0不是合数
        heShu[1] = 2;
        heShu[2] = 2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens())  return;

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ifHeShu(n);
            boolean hasRes = false;

            for(int i = n; i >= 2; i--){
                if(heShu[i] == 1){
                    if(heShu[2*n-i] == 1){
                        pw.print(i);
                        pw.print(" ");
                        pw.print(2 * n - i);
                        pw.print("\n");
                        hasRes = true;
                        break;
                    }
                }
            }
            if(!hasRes)  pw.print("-1"+"\n");
        }

        pw.flush();
        pw.close();
    }
    public static void ifHeShu(int n){
        for(int i = 2; i <= 2*n; i++){
            if(heShu[i] == 0){//没判断过
                heShu[i] = 2;//默认质数
                for(int j = 2; j <= Math.sqrt(n) + 1; j++){
                    if(i%j==0){
                        heShu[i] = 1;//找到分子变合数
                        break;
                    }
                }
            }
        }
    }
}
