package 携程.D20260412;
/*
数字分裂
偶数
2(x/2+1)||x -> (x/2 + 1)*2 = x + 2
x/2+1为奇2(x/4 x/4 2) = x + 4
     为偶2((x/2+1)/2+1 (x/2+1)/2+1) = x + 6
奇数
x -> [(x-1)/2 + 1]*2 + 2 = x-1 + 2 + 2 = x + 3

输入
2
1 3
11 2
输出
32
20

模拟1 3
1   ||途中x/2不为2则最后一定有1
1     1     2 = 1+3 = 4 = 2*2   2奇1偶
1 1 2 1 1 2 2 2 = 2*(1+3)+ 1*(2+2) = 12 = 2*6   4奇4偶
112 112 22 112 112 22 22 22 = 4*(1+3)+4*(2+2) = 32 = 2*16   8奇12偶
奇 2 n次方
偶数 2 n次方 + 上一步奇数

模拟11 2
11 ||途中x/2可能为2则最后全是2，从这一步开始后面求和就是全×2
6 6 = 11 + 3 = 14 = 2*7   2偶
4 4  4 4  2 2 = 4*4 + 4*2 = 20 =2*10  4偶
4(2 2 2) 4(2 2) = 4*6 + 4*4 = 40 = 2*20    8偶
20(2 2) = 2*40
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens())  return;
        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long[] jiShu = new long[m+1];
            long[] ouShu = new long[m+1];
            if(n%2==0){//初始偶数
                jiShu[0] = 1;
                ouShu[0] = 0;
            }else{//初始奇数
                jiShu[0] = 1;
                ouShu[0] = 0;
            }
            int temp = n;
            temp = temp /2 +1;
            for(int i=1;i<=m;i++){
                if(temp%2==0){
                    ouShu[i] = 3*jiShu[i-1] + 2*ouShu[i-1];
                    jiShu[i] = 0;
                }else{
                    ouShu[i] = 2 * ouShu[i-1] + jiShu[i-1];
                    jiShu[i] = 2 * jiShu[i-1];
                }
                temp = temp /2 +1;
            }
            for(int i=1;i<=m;i++){
                pw.print("奇数:"+jiShu[i]+" 偶数:"+ouShu[i]+"\n");
            }
            pw.print("\n");
        }
        //找最终多少个奇数 和 偶数
        //每一步的分裂 若有
        //一个奇数 导致结果 +3
        //一个偶数 导致结果 +2
        //奇 2 m次方
        //偶数 2 m次方 + 上一步奇数
        //途中x/2可能为2则最后全是2，从这一步开始后面求和就是全×2,这一步之前都是x2再加上一步奇数
        pw.flush();
        pw.close();
    }
}
