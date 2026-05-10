package 阿里.T20260401;
/*
 * =======================================================
 * 题目简介：
 * AK机拿到了两个长度均为 n 的非负整数数组 a 和 b。她可以执行三种操作：
 * 1. a 中某元素减 1。
 * 2. b 中某元素减 1。
 * 3. a 和 b 中各挑一个元素同时减 1（花费 1 次操作）。
 * 目标：求使两个数组完全相等的最少操作次数。
 * * 解题思路：
 * 1. 要想操作最少，最后每个位置 i 上的值应尽量大，即减为 Math.min(a[i], b[i])。
 * 2. 分别计算数组 A 需要单独减去的部分总和 sumA = sum(max(0, a[i] - b[i]))，
 * 以及数组 B 需要单独减去的部分总和 sumB = sum(max(0, b[i] - a[i]))。
 * 3. 我们可用操作 3 同时抵消 A 和 B 需要减少的部分，最多抵消 Math.min(sumA, sumB) 次。
 * 4. 剩下的部分只能用操作 1 或 2 单独减去。
 * 5. 故最少总操作次数为 max(sumA, sumB)。
 * 6. 注意：sumA 和 sumB 最大可达 2*10^14，须使用 long 类型防止溢出。
 * =======================================================
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T1 {
    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 和 PrintWriter 进行快速 I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);
        if (!st.hasMoreTokens()) return;

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 循环读取数组 a
            long[] a = new long[n];
            int countA = 0;
            while (countA < n) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens() && countA < n) {
                    a[countA++] = Long.parseLong(st.nextToken());
                }
            }

            // 循环读取数组 b
            long[] b = new long[n];
            int countB = 0;
            while (countB < n) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens() && countB < n) {
                    b[countB++] = Long.parseLong(st.nextToken());
                }
            }

            long sumA = 0;
            long sumB = 0;

            // 计算数组 A 和 B 各自需要减少的总量
            for (int i = 0; i < n; i++) {
                if (a[i] > b[i]) {
                    sumA += (a[i] - b[i]);
                } else if (b[i] > a[i]) {
                    sumB += (b[i] - a[i]);
                }
            }

            // 输出最少操作次数
            pw.println(Math.max(sumA, sumB));
        }

        // 刷新输出流
        pw.flush();
    }
}
