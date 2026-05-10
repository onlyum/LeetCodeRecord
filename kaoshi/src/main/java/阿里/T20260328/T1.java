package 阿里.T20260328;
/**
 * 【阿里算法岗】2026-3-28-第一题-回合制游戏
 * * 题目简介：
 * 给定两个长度为 n 的数组 a 和 b。Alice 和 Bob 轮流选择未被选过的位置。
 * Alice 选位置 i 净收益为 (a_i - b_i)；Bob 选位置 i 净收益为 (b_i - a_i)。
 * 最终 Alice 总收益为 A，Bob 为 B。求两者在最优策略下的 A - B 差值。
 * * 解题思路：
 * 假定 Alice 选的位置集合为 SA，Bob 为 SB。
 * A - B = sum_{(i in SA)}(a_i - b_i) - sum_{(j in SB)}(b_j - a_j)
 * = sum_{(i in SA)}a_i - sum_{(i in SA)}b_i - sum_{(j in SB)}b_j + sum_{(j in SB)}a_j
 * = (sum_{(i in SA)}a_i + sum_{(j in SB)}a_i) - (sum_{(i in SA)}b_i + sum_{(j in SB)}b_i)
 * = sum(所有 a_i) - sum(所有 b_i)
 * 结论：无论二人怎么选，最终的分差是固定的，恒等于 a数组总和 减去 b数组总和。
 * 策略上只需要直接计算两个数组的差值即可。注意使用 long 防溢出。
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            long sumA = 0;
            // 读取数组 a，因为仅仅需要求和，所以无需开辟数组存储，直接累加
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                sumA += Long.parseLong(st.nextToken());
            }

            long sumB = 0;
            // 读取数组 b，同样直接累加
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                sumB += Long.parseLong(st.nextToken());
            }

            // 输出双方最优策略下的分差 A - B
            pw.println(sumA - sumB);
        }

        // 刷新缓冲区并输出
        pw.flush();
    }
}
