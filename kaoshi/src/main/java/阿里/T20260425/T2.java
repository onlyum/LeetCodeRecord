package 阿里.T20260425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 题目简介：
 * 给定两个长度分别为 n 和 m 的非负整数序列 a 和 b，构造一个 n × m 的矩阵 C，其中 C[i][j] = a[i] * b[j]。
 * 给定一个阈值 k，统计矩阵中有多少个元素满足 C[i][j] >= k。
 *
 * 核心思路：
 * 1. 避开暴力：由于 n, m 最大可达 2*10^5，矩阵总元素达 4*10^10，直接双重遍历必然超时。
 * 2. 排序优化：对数组 b 进行从小到大排序，时间复杂度 O(m log m)。
 * 3. 枚举与二分：遍历数组 a，时间复杂度 O(n)。对于每一个 a[i]：
 * - 如果 k = 0，则所有的 b[j] 都满足条件（共 m 个，因为数组元素全为非负数）。
 * - 如果 a[i] = 0 且 k > 0，则没有 b[j] 能满足条件。
 * - 如果 a[i] > 0 且 k > 0，我们需要寻找满足 b[j] >= ⌈k / a[i]⌉ 的最小索引。
 * - 利用公式 (k + a[i] - 1) / a[i] 计算向上取整的目标值，然后通过二分查找在 b 中定位，时间复杂度 O(log m)。
 * - 满足条件的数量即为 m - left。
 * 4. 数据类型：最终的结果 ans 最大可能为 4*10^10，输入 k 最大为 10^18，必须使用 long 防止溢出。
 */
public class T2 {
    public static void main(String[] args) throws IOException {
        // 使用 BufferedReader 和 StringTokenizer 进行快速输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());

            long[] a = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            long[] b = new long[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Long.parseLong(st.nextToken());
            }

            // 排序数组 b 以便二分查找
            Arrays.sort(b);

            long ans = 0;
            for (int i = 0; i < n; i++) {
                if (k == 0) {
                    ans += m;
                } else if (a[i] == 0) {
                    continue;
                } else {
                    // 目标值：b[j] >= k / a[i] (向上取整)
                    long target = (k + a[i] - 1) / a[i];

                    // 二分查找寻找下界 (lowerBound)找第一个>=target的位置
                    int left = 0, right = m;
                    /*
                    注意这里是 m 而不是 m - 1。因为有可能数组 b 中的所有元素都小于 target。
                    这种情况下，“第一个大于等于 target 的位置”实际上是不存在的，或者说是在数组末尾之外（索引为 m）。
                    将 right 设为 m 可以妥善处理这种边界情况。
                     */
                    while (left < right) {
                        int mid = left + (right - left) / 2;
                        if (b[mid] >= target) {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    }
                    ans += (m - left);
                }
            }
            pw.println(ans);
        }

        pw.flush();
        pw.close();
    }
}
