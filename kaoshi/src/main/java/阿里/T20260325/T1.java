package 阿里.T20260325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.StringTokenizer;

/**
题目描述
【阿里】2026-3-25-第一题-圣诞老人分糖果

题目描述：
圣诞老人有 n 种糖果，第 i 种糖果有 ci 个。他要把这些糖果分给 k 个小朋友。分配规则如下：

每一种糖果必须全部分完。

对于任意一种糖果，任意两个小朋友所分得的该种糖果的数量之差不超过 1。
在所有合法分配中，任选一个小朋友，统计其最终得到的糖果总数；求该数值在所有合法分配方案中可能达到的最小值与最大值。换句话说，求在所有合法分配方案中，任意一个小朋友所能获得的糖果总数最小值与最大值。

输入描述：
每个测试文件均包含多组测试数据。第一行输入一个整数 T 代表数据组数。每组测试数据描述如下：
第一行输入两个整数 n, k (1 <= n <= 210^5, 1 <= k <= 10^9)。
第二行输入 n 个整数 c1, ..., cn (0 <= ci <= 10^9)。
保证单个测试文件的 n 之和不超过 510^5。

输出描述：
对于每组测试数据，输出两个整数：在所有合法分配中，单个小朋友可能得到的糖果总数的最小值与最大值。

样例1：
输入：
3
3 2
5 2 7
4 3
0 0 0 0
2 5
10 1

输出：
6 8
0 0
2 3

# 解题思路
1. 对于单种糖果分配：每个小朋友的基础获取量为 `c_i / k`。余下的糖果数量为 `c_i % k`，这部分需要分配给不同的 `c_i % k` 个小朋友（每人1个）。
2. 因为余数 `c_i % k` 永远严格小于 `k`，这就意味着在分配额外那1个糖果时，永远有小朋友分不到。
3. 如果要求单人获取的最小值：我们可以在每种糖果分配时，都不把余数的1个糖果给他。因此 `最小值 = sum(c_i / k)`。
4. 如果要求单人获取的最大值：只要有余数，我们就优先把多出的1个分给他。因此 `最大值 = sum(c_i / k) + (有多少种糖果满足 c_i % k > 0)`。
5. 结果累加可能会超出 int 范围，需要使用 long 类型。
**/
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens())  return;
        int T = Integer.parseInt(st.nextToken());
        
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());

            long minCandies = 0;
            long maxCandies = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                long c = Long.parseLong(st.nextToken());
                // 计算保底数量和余数
                long base = c / k;
                long rem = c % k;
                // 最小值：只能拿到保底的糖果
                minCandies += base;
                // 最大值：如果该种糖果除以人数有余数，就可以让他多拿1个
                maxCandies += base + (rem > 0 ? 1 : 0);
            }

            // 输出当前测试用例的结果
            pw.println(minCandies + " " + maxCandies);
        }

        // 刷新输出流
        pw.flush();
        pw.close();
    }
}
