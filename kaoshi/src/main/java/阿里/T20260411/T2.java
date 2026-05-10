package 阿里.T20260411;
/*
 * ==========================================
 * 题目简介：
 * 【阿里AI研发岗】2026-4-11-第二题-逆转
 * 给定一个经过保留规则处理后得到的序列 b 和一个阈值 d。
 * 保留规则为：先保留 a_1；对于后续元素 a_i，如果 a_{i-1} + d <= a_i，则保留 a_i，否则跳过。
 * 要求逆向构造出一个合法的原序列 a，使得 a 的长度 m 尽可能小；
 * 在长度最小的前提下，a 的字典序尽可能小。
 * * 简要解题思路：
 * 1. 贪心策略：我们从左到右处理给定的目标序列 b。
 * 2. 对于 b 中相邻的两个元素 b_i 和 b_{i+1}：
 * - 如果 b_i + d <= b_{i+1}：说明 b_{i+1} 可以直接跟在 b_i 后面且被保留，我们直接将其加入序列，不增加多余长度。
 * - 如果 b_i + d > b_{i+1}：说明 b_{i+1} 不能直接跟在 b_i 后面（否则会被跳过）。
 * 此时我们必须在它们中间插入一个会被跳过的元素 x。
 * 为了满足长度 m 最小，我们只插入 1 个元素；
 * 为了满足字典序最小，我们要选最小的正整数，即 x = 1。
 * (题目保证有解，所以插入 1 一定能同时满足被跳过且不影响 b_{i+1} 被保留的条件)。
 * 3. 复杂度：由于只需线性遍历一次数组 b，时间复杂度为 O(n)，空间复杂度为 O(n)。
 * ==========================================
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class T2 {
    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 和 StringTokenizer 进行快速输入，PrintWriter 进行快速输出
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long d = Long.parseLong(st.nextToken());

            // 预分配数组，最大可能的长度为 2n（最坏情况每两个元素间都要插入一个 1）
            long[] ans = new long[2 * n];
            int size = 0;// size记录元素个数，ans[size - 1] 即为序列 a 中当前已经被保留的最后一个元素

            st = new StringTokenizer(br.readLine());

            // 读取第一个元素，直接放入答案序列
            long first = Long.parseLong(st.nextToken());
            ans[size++] = first;

            // 遍历序列 b 中的剩余元素
            for (int i = 1; i < n; i++) {
                long nextB = Long.parseLong(st.nextToken());
                if (ans[size - 1] + d <= nextB) {
                    // 满足保留条件，直接追加
                    ans[size++] = nextB;
                } else {
                    // 不满足保留条件，贪心插入最小正整数 1，然后再追加目标元素
                    ans[size++] = 1;
                    ans[size++] = nextB;
                }
            }

            // 输出构造序列的长度 m
            pw.println(size);

            // 输出构造的序列 a
            for (int i = 0; i < size; i++) {
                pw.print(ans[i]);
                if (i != size - 1) {
                    pw.print(" ");
                }
            }
            pw.println();
        }

        // 清空缓冲区并关闭流
        pw.flush();
        pw.close();
        br.close();
    }
}
