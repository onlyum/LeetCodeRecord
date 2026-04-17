package 云智;
/*
等式的可能
【1，15】长度的字符串，中间可以随即加‘+’，求得出的结果是质数的个数
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()){
            return;
        }

        String str = st.nextToken();
        int len = str.length();
        long N = strToInt(str, 0 , len-1);
/*
1234
1 234
1 23 4
1 2 34
1 2 3 4

12 34
12 3 4

123 4

1234
 */

        for(int i=0;i<len;i++) {//
            for(int j=i+1;j<len;j++) {

            }
        }

        pw.println(N);
        pw.flush();
        pw.close();
    }
    static boolean isPrime(long n){
        if(n<2){
            return false;
        }
        if(n%2==0){
            return false;
        }
        for(int i=3;i<Math.sqrt(n);i+=2){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    static long strToInt(String str, int l, int r) {
        String subStr = str.substring(l, r+1);
        int len = subStr.length();
        int[] nums = new int[len];
        long N = 0;
        for(int i=0;i<len;i++) {
            nums[i] = str.charAt(i) - '0';
            int  num = nums[i];
            N = N*10+num;
        }
        return N;
    }
}
