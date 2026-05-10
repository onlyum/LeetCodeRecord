package 阿里.T20260401;
/*
【阿里研发岗】2026-4-1-第二题-约束差值数组
* 【题目简介】
 * Alice 需要构造一个长度为 n 的正整数数组 a，满足 a_i > 0。
 * 给定 m 条约束 (i, j, k)，要求满足 a_i - a_j = k，且 1 <= a_i <= 10^18。
 * 若存在则输出该数组，否则输出 -1。
 *
 * 【解题思路】
 * 1. 建图：将 a_i - a_j = k 转化为从 j 到 i 的一条权值为 k 的有向边，
 *    以及从 i 到 j 的一条权值为 -k 的有向边。
 * 2. 遍历连通块验证冲突：对未访问的节点赋初始值 0 并进行 BFS 遍历。
 *    通过 a_next = a_curr + weight 传递推导，若遇到已访问的节点，
 *    校验推算值和其现有值是否相等。如果不等，说明存在矛盾，返回 -1。
 * 3. 数组平移：由于题意要求所有 a_i >= 1，我们对每个连通块找出最小值 min_val，
 *    将该连通块的所有元素整体加上 (1 - min_val)。这样该连通块内的最小值刚好等于 1，
 *    既满足了相对差值，也满足了正数范围。题目极值 2*10^11 远小于 10^18 上限。
输入描述
每个测试文件均包含多组测试数据。第一行输入一个整数 T(1 <= T <= 10^5) 代表测试组数，每组测试数据描述如下：
第一行输入两个整数 n, m(1 <= n, m <= 2 * 10^5)。
接下来 m 行，每行输入三个整数 i, j, k(1 <= i, j <= n, -10^6 <= k <= 10^6)。
保证所有测试中 n 的总和不超过 5 * 10^5，m 的总和不超过 5 * 10^5。

输出描述
对于每组测试数据，输出一行：若存在满足所有约束且 1 <= a_i <= 10^18 的数组 a ，则输出 n 个正整数 a_1, a_2, ..., a_n；否则输出 -1。

样例1
输入
2
3 2
1 2 1
2 3 1
2 2
1 2 100
2 1 1

输出
3 2 1
-1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class T2 {
    static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 建立邻接表
            List<Edge>[] graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            int count = 0;
            int totalParams = m * 3;
            long[] inputs = new long[totalParams];

            // 按照指定的格式，循环读取 m*3 个参数（适配换行或同行空格分隔的输入）
            while (count < totalParams) {
                st = new StringTokenizer(br.readLine());
                inputs[count++] = Long.parseLong(st.nextToken());
            }

            // 解析约束并建图
            for (int i = 0; i < m; i++) {
                int u = (int) inputs[i * 3];     // i
                int v = (int) inputs[i * 3 + 1]; // j
                long w = inputs[i * 3 + 2];      // k
                // a_u - a_v = w  =>  a_u = a_v + w
                graph[v].add(new Edge(u, w));
                graph[u].add(new Edge(v, -w));
            }

            long[] ans = new long[n + 1];
            boolean[] visited = new boolean[n + 1];
            boolean isPossible = true;

            // 遍历所有的点，处理每个不连通的子图
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    List<Integer> component = new ArrayList<>();
                    Queue<Integer> queue = new ArrayDeque<>();

                    ans[i] = 0;
                    visited[i] = true;
                    queue.offer(i);
                    component.add(i);

                    long minVal = 0; // 记录当前连通块的最小值

                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        for (Edge edge : graph[curr]) {
                            int next = edge.to;
                            long weight = edge.weight;

                            if (!visited[next]) {
                                visited[next] = true;
                                ans[next] = ans[curr] + weight;
                                minVal = Math.min(minVal, ans[next]);
                                queue.offer(next);
                                component.add(next);
                            } else {
                                // 已经访问过的点，校验数值冲突
                                if (ans[next] != ans[curr] + weight) {
                                    isPossible = false;
                                }
                            }
                        }
                    }

                    if (!isPossible) {
                        break;
                    }

                    // 将当前连通块做平移：使得最小值为 1
                    long offset = 1 - minVal;
                    for (int node : component) {
                        ans[node] += offset;
                    }
                }
            }

            // 输出结果
            if (isPossible) {
                for (int i = 1; i <= n; i++) {
                    pw.print(ans[i] + (i == n ? "" : " "));
                }
                pw.println();
            } else {
                pw.println("-1");
            }
        }
        // 记得刷新输出流
        pw.flush();
    }
}
