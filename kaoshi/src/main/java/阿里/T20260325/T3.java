package 阿里.T20260325;

import java.io.*;
import java.util.StringTokenizer;

/*
【阿里】2026-3-25-第三题-喜欢的正整数HARD  时间限制：1000ms  空间限制：256M
题目描述
AK机不喜欢能被 3 整除的正整数，也不喜欢十进制表示中包含数字 3 的正整数。现在他要将所有他喜欢的正整数按升序排成一个序列。
给定正整数 k，请你找出这个序列的第 k 个数。
友情提醒：第 $10^{18} + 1$ 项为 $10 995 467 216 611 448 857$。
输入描述
第一行输入一个整数 t(1<=t<=10^4)，表示测试用例的数量。
接下来 $t$ 行，每行输入一个整数 k(1<=k<=10^{18})，表示要找的第 k 个喜欢的正整数的序号。
输出描述
对于每个测试用例，在一行上输出一个整数，表示所求的第 $k$ 个喜欢的正整数。

样例1输入
1
2
3
4
5
输出
2
4
5
7
样例解释在这个样例中，喜欢的正整数序列的前五项为 1, 2, 4, 5, 7，因此对应输出分别为 1, 2, 4, 5, 7。
 */
public class T3 {
    // 预处理 9 的幂次，避免重复计算 (L最长在20左右)
    static long[] p9 = new long[20];
    static {
        p9[0] = 1;
        for (int i = 1; i < 20; i++) {
            p9[i] = p9[i - 1] * 9;
        }
    }

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String firstToken = sc.next();
        if (firstToken == null) return;
        int t = Integer.parseInt(firstToken);

        int[] firstChoices = {1, 2, 4, 5, 6, 7, 8, 9};
        int[] midChoices = {0, 1, 2, 4, 5, 6, 7, 8, 9};
        int[] singleChoices = {1, 2, 4, 5, 7, 8};

        while (t-- > 0) {
            long k = sc.nextLong();

            // 特判长度为 1 的情况
            if (k <= 6) {
                out.println(singleChoices[(int)k - 1]);
                continue;
            }

            // 减去长度为1的总量
            k -= 6;
            int L = 2;

            // 找出结果所在的长度 L
            while (true) {
                long countL = 48 * p9[L - 2];
                if (k <= countL) {
                    break;
                }
                k -= countL;
                L++;
            }

            StringBuilder sb = new StringBuilder();
            int S = 0; // 记录前缀和模 3 的值

            // 逐位确定具体数字
            for (int i = 1; i <= L; i++) {
                if (i == 1) {
                    // 第 1 位
                    long B = 6 * p9[L - 1 - 1]; // 后续分支树的容量
                    int idx = (int) ((k - 1) / B);
                    int d = firstChoices[idx];
                    sb.append(d);
                    k -= idx * B;
                    S = (S + d) % 3;
                } else if (i < L) {
                    // 中间位
                    long B = 6 * p9[L - i - 1];
                    int idx = (int) ((k - 1) / B);
                    int d = midChoices[idx];
                    sb.append(d);
                    k -= idx * B;
                    S = (S + d) % 3;
                } else {
                    // 最后 1 位，必须要保证数位和不能被3整除
                    int[] validLast = new int[6];
                    int vIdx = 0;
                    for (int d : midChoices) {
                        if ((S + d) % 3 != 0) {
                            validLast[vIdx++] = d;
                        }
                    }
                    int idx = (int) (k - 1);
                    sb.append(validLast[idx]);
                }
            }
            out.println(sb.toString());
        }
        // 保证全部写入控制台
        out.flush();
    }

    // 内部类，实现快速 I/O 读取
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}