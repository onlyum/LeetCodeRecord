package 上海aiLab.T3三角形;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            long[] a = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            //数组升序排序
            Arrays.sort(a);

            long ans = 0;

            //固定 B（即索引 v）
            for (int v = 1; v < n - 1; v++) {
                int w = v; // C 的指针 w 从 v 开始，单调向右滑动
                long B = a[v];

                // 遍历 A（即索引 u）
                for (int u = 0; u < v; u++) {
                    long A = a[u];

                    // 处理特殊情况：当 A = B 时，任意 C 都成立
                    if (A == B) {
                        ans += (n - 1) - v;
                        continue;
                    }

                    // 当 A < B 时，滑动指针 w
                    // 判断条件为：C * (B - A) < A * B
                    // 注意：这里使用乘法避免除法带来的精度问题
                    while (w + 1 < n && a[w + 1] * (B - A) < A * B) {
                        w++;
                    }

                    // 当前合法的三元组数量为 [v+1, w] 区间内的元素个数
                    ans += (w - v);
                }
            }
            sb.append(ans).append("\n");
        }

        // 统一输出结果
        System.out.print(sb);
    }
}
