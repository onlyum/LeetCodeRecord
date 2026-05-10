package 阿里.T20260408;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class T2 {
    /*
     * 题目：【阿里AI研发岗】2026-4-8-第二题-环形二进制串
     * * 题目简介：
     * 给定长度为 n 的环形二进制串 s，通过任意旋转得到一个线性串。求在所有可能线性串中，
     * 包含恰好 k 个 1 的最短前缀的长度。如果不满足条件则输出 -1。
     * * 解题思路：
     * 1. 把所有 '1' 的下标记录在数组 ones 中，记共有 m 个 '1'。如果 m < k，显然无解，输出 -1。
     * 2. 最短的前缀一定是以 '1' 开始，以 '1' 结束的。因此问题等价于求环形串中连续的 k 个 '1' 之间的最短距离。
     * 3. 遍历所有的起点 '1' (假设为第 i 个)，它的第 k 个 '1' 是第 (i + k - 1) 个。
     * 4. 由于是环形，如果跨过了末尾，下标可以通过 % m 获取，并在计算距离时加上总长 n。
     * 5. 求出所有长度中的最小值即为答案。
     */

    public static void main(String[] args) throws Exception {
        // 使用 BufferedReader 和 PrintWriter 加速 IO
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 读取二进制字符串 s
            String s = br.readLine().trim();

            // 提前分配最大可能的数组以存储 '1' 的下标
            int[] ones = new int[n];
            int m = 0; // 记录 '1' 的总数量

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '1') {
                    ones[m++] = i;
                }
            }

            // 如果总的 '1' 数量小于 k，则无论如何也找不到包含 k 个 1 的前缀
            if (m < k) {
                pw.println("-1");
            } else {
                int minLen = Integer.MAX_VALUE;

                // 遍历以第 i 个 '1' 为起点，寻找连续的 k 个 '1'
                for (int i = 0; i < m; i++) {
                    int endIdx = i + k - 1; // 需要找的第 k 个 '1' 的相对位置
                    int currentLen;

                    if (endIdx < m) {
                        // 没有跨越环的边界，正常计算距离
                        currentLen = ones[endIdx] - ones[i] + 1;
                    } else {
                        // 跨越了环的边界，需要通过环绕计算长度
                        currentLen = n - ones[i] + ones[endIdx % m] + 1;
                    }

                    // 维护最小值
                    if (currentLen < minLen) {
                        minLen = currentLen;
                    }
                }

                pw.println(minLen);
            }
        }

        // 输出所有结果并关闭流
        pw.flush();
        pw.close();
        br.close();
    }
}
