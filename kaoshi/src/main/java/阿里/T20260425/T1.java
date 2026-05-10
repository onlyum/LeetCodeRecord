package 阿里.T20260425;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
 * =================================================================================
 * 题目描述：
 * 给定一个长度为 n 的目标字符串 s。从一个空串开始，每次可以在当前字符串的任意位置
 * 插入一个小写字母。经过 n 次操作后，最终得到与 s 完全相同的字符串。
 * 问一共有多少种不同的操作序列？结果需对 10^9 + 7 取模。
 *
 * 核心思路（逆向思维 / 倒推删除法）：
 * 1. 字符串 s 的具体内容其实是“烟雾弹”，我们完全不需要关心它的具体字符。
 * 2. 将“从空串逐步插入 n 个字符变成 s”的正向过程，反向看作“从字符串 s 逐步删除字符变成空串”的逆向过程。
 * 3. 倒推删除：
 * - 第 1 次删除：在长度为 n 的串中任选一个位置删掉，有 n 种选择。
 * - 第 2 次删除：在长度为 n-1 的串中任选一个位置删掉，有 n-1 种选择。
 * - ... 以此类推，直到变成空串。
 * 4. 总的删除序列方案数为：n * (n-1) * ... * 1 = n! 种全排列。
 * 5. 因为每一个“删除位置序列”逆转后，都能唯一确定一个“插入位置及字母的序列”（一一映射），
 * 所以合法的操作序列总数恒等于 n! 。
 * 6. 算法实现：预处理阶乘数组 O(1) 查询 + 快速 I/O 防止超时。
 * =================================================================================
 */
public class T1 {
    // 定义取模常量
    static final int MOD = 1000000007;
    // 题目中单次 n 的最大值为 5 * 10^5
    static final int MAXN = 500005;
    // 预处理的阶乘数组
    static long[] fact = new long[MAXN];

    // 静态代码块，在类加载时预处理 1 到 MAXN-1 的阶乘
    static {
        fact[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens())  return;

        // 读取测试组数 T
        int t = Integer.parseInt(st.nextToken());

        while(t-->0) {
            // 读取 n
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 读取字符串 s
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            // 直接以 O(1) 的复杂度输出 n! % MOD
            pw.println(fact[n]);
        }

        pw.flush();
        pw.close();
    }
}
