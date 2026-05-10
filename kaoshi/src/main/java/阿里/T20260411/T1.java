package 阿里.T20260411;
/*
 * 题目简介：
 * 【阿里AI研发岗】2026-4-11-第一题-模乘循环数
 * 给定初始值 a = 1 和两个整数 k, m。系统不断执行更新：a = (a * k) mod m。
 * 由于取模运算，a 的取值最终会进入循环。要求计算在无限次执行过程中，a 一共可能取到多少个不同的值。
 * * 简要解题思路：
 * 1. 模拟过程：从 a = 1 开始，不断计算下一个 a 值。
 * 2. 去重记录：利用一个 boolean 数组 visited (大小为 m) 来记录每个余数是否已经出现过。
 * 3. 终止条件：当计算出的新 a 值在 visited 中已被标记为 true 时，说明进入了循环，停止模拟。
 * 4. 复杂度：时间复杂度为 O(m)，空间复杂度为 O(m)，在题目给定的 10^6 数据范围内可完美运行。
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;

        // 根据题目描述，输入为一行两个整数 k 和 m
        long k = Long.parseLong(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // visited 数组记录该数字是否出现过。
        // 注意：a 的初始值是 1，如果 m=1，a 经过一次运算会变为 0。
        // 所以数组大小建议设为 Math.max(m, 2) 或直接根据 m 的范围处理。
        boolean[] visited = new boolean[Math.max(m, 2)];

        long a = 1;
        int count = 0;

        // 开始模拟
        // 注意：初始的 a=1 也算作一个取值，但在取模 m 的环境下，如果 m=1，初始 a 就不在 [0, m-1] 范围内
        // 按照题目逻辑，先检查当前的 a，再计算下一个 a
        while (a < visited.length && !visited[(int)a]) {
            visited[(int)a] = true;
            count++;
            // 执行更新：a = (a * k) mod m;使用 long 防止 a * k 溢出
            a = (a * k) % m;
        }

        // 输出不同 a 的个数
        pw.println(count);
        pw.flush();
        pw.close();
    }
}
