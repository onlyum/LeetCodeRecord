package WPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    //双指针解答
    static long solution(String s, int n, int k){
        long count = 0;
        char[] arr = s.toCharArray(); // 转成数组访问速度更快
        int diffCount = 0; // 当前窗口内的不同相邻字母对数
        int L = 0; // 左指针

        // R 为右指针，遍历字符串
        for (int R = 0; R < n; R++) {

            if (R > 0 && arr[R] != arr[R - 1]) {// 如果当前字符和前一个字符不同，差异数 +1
                diffCount++;
            }
            // 如果差异数达到了 k (即会发生爆炸)，则需要移动左指针缩小窗口
            while (diffCount >= k && L < R) {
                // 如果左边移出的字符和它右边的字符不同，差异数 -1
                if (arr[L] != arr[L + 1]) {
                    diffCount--;
                }
                L++;
            }
            //最关键点：如果[L, R]差异数严格小于 k，那么所有[l,R] L<=l<=R都差异数严格小于 k
            // 以 R 结尾的合法子串个数正好等于窗口的长度
            if (diffCount < k) {
                count += (R - L + 1);
            }
        }

        return count;
    }
}
