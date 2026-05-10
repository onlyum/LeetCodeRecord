package 阿里.T20260425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
public class T3 {
    /*
     * =================================================================================
     * 【题目简介】
     * 给定一个正整数数组 a 以及一个初始数字 x。你可以把数组 a 重新排列成任意一个排列，
     * 然后从初始数字 x 开始，依次对排列中的每个元素进行取模运算。
     * 现要求输出所有可能的排列中，最终结果能够得到的最大值。
     * 数据范围：T <= 2*10^5, sum(n) <= 5*10^3, x <= 10^18, a_i <= 2*10^3。
     * * 【解题思路】
     * 其实就是找一个有效的降序序列，来对x按顺序MOD
     * 1. 命运的门槛（界限推论）：
     * 因为必须使用数组的所有元素，最终结果必然严格小于数组 a 中的最小值 min(a)。
     * 一旦数值掉到 min(a) 以下，后续遇到任何数字取模都不会再发生改变。
     * 因此：如果初始 x 已经 < min(a)，直接返回 x；最终答案必然在 [0, min(a) - 1] 之间。
     * * 2. 漏斗原理（有效序列推论）：
     * 取模运算中，只有当“除数 <= 被除数”时，数值才会被改变；遇到比自己大的除数会直接“穿透”（如 3 % 10 = 3）。
     * 这意味着，要让取模连续发生作用，选用的除数必须是严格降序的。
     * => 结论：所有 n! 种全排列的最终取模效果，完全等价于从数组 a 中挑出一个“降序子序列”进行取模。
     * （DP 中没有被选中的数字，等价于被排在了全排列的末尾，因数值已经较小而失去作用）。
     * * 3. 动态规划 (DP) 与 状态压缩：
     * 因为 a_i 最大不超过 2000，取模结果永远在 [0, 2000) 内。
     * - 状态定义：boolean[] dp[v]，表示数值 v 是否可以达到。我们只关心“能不能到”，不关心“怎么到的”。
     * - 遍历顺序：数组升序排序后，从后往前【逆序遍历】（完美模拟从大到小挑选除数的降序过程）。
     * - 状态转移：
     * 对于当前遍历到的元素 val = a[i]：
     * a) 开辟新路线：直接让 x 对 val 取模，dp[x % val] = true。
     * b) 历史路线接力：遍历所有已达到的状态 v，如果 v >= val（即大漏斗的结果能进小漏斗），
     * 则触发连锁反应，将 val 接入之前的降序序列：dp[v % val] = true。
     * * 4. 收集答案：
     * 从“天花板” min(a) - 1 开始向下遍历，找到的第一个 dp[v] == true 的 v，即为全局最大结果。
     * =================================================================================
     */
    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 和 StringTokenizer 进行快速输入，PrintWriter 进行快速输出
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line = br.readLine();
        if (line == null) return;

        StringTokenizer st = new StringTokenizer(line);
        if (!st.hasMoreTokens()) return;

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            // 读取 n 和 x
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());

            // 读取数组 a，并同时找出数组的最大值和最小值
            int[] a = new int[n];
            int minA = Integer.MAX_VALUE;
            int maxA = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                minA = Math.min(minA, a[i]);
                maxA = Math.max(maxA, a[i]);
            }

            // 特判：如果初始值已经小于最小值，取模不会改变结果
            if (x < minA) {
                pw.println(x);
                continue;
            }

            // 升序排序，便于后续逆序（降序）处理
            Arrays.sort(a);

            // dp 数组，记录所有可能达到的取模结果
            boolean[] dp = new boolean[maxA + 1];

            // 逆序遍历数组
            for (int i = n - 1; i >= 0; i--) {
                int val = a[i];

                // 情况 1：直接作为 x 遇到的第一个起作用的取模数字
                int firstMod = (int) (x % val);
                dp[firstMod] = true;

                // 情况 2：作为后续继续改变数值的数字
                for (int v = val; v <= maxA; v++) {
                    if (dp[v]) {
                        dp[v % val] = true;
                    }
                }
            }

            // 从 minA - 1 向下寻找最大的可行解
            long ans = 0;
            for (int v = minA - 1; v >= 0; v--) {
                if (dp[v]) {
                    ans = v;
                    break;
                }
            }

            // 写入输出流
            pw.println(ans);
        }

        // 刷新并关闭输出流
        pw.flush();
        pw.close();
    }
}
