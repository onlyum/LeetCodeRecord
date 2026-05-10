package 阿里.T20260408;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
 * 题目简介：
 * AK机有多张银行卡，每张卡的密码由6个数字组成（可含前导0）。
 * 为了方便记忆，AK机决定进行一次统一改动：选择同一个位置 pos ∈ {1, 2, 3, 4, 5, 6}，
 * 并选择一个数字 d ∈ {0, 1, ..., 9}，把所有卡在该位置的数字都改成 d。
 * 目标：计算完成一次上述改动后，不同密码的最少种类数。
 *
 * 解题思路：
 * 1. 核心等价转化：在某个位置 pos 上全部修改为同一个数字 d，意味着在这个位置上所有的密码变得完全相同。
 * 2. 因此，修改后两个密码是否相同，完全取决于剩下的 5 个位置上的数字是否完全一致（相当于把第 pos 位删掉）。
 * 3. 问题转化为了：枚举被修改（忽略）的位置 pos (0 到 5)，将所有密码去掉第 pos 位后，剩下的 5 位构成的组合进行去重。
 * 4. 统计在每个位置 pos 被忽略时，剩余 5 位数字的去重数量（即此时不同密码的数量）。
 * 5. 取所有枚举情况（共 6 种）中，去重后数量的最小值，即为最终答案。
 * 6. 性能优化：去掉一位后剩下的 5 位数字最大为 99999。我们可以将其转化为整数，并使用一个大小为 100000 的整型数组
 */
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            String[] passwords = new String[n];
            int count = 0;
            // 循环读取 n 个字符串（适配换行或同行空格分隔的输入）
            while (count < n) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens() && count < n) {
                    passwords[count++] = st.nextToken();
                }
            }

            int minDistinct = n;

            // 枚举修改密码的位置 pos ∈ [0, 5]
            for (int pos = 0; pos < 6; pos++) {
                // 优化：使用数组代替 Set 去重，最大 5 位数是 99999
                boolean[] visited = new boolean[100000];
                int distinctCount = 0;

                for (int k = 0; k < n; k++) {
                    int val = 0;
                    // 提取忽略 pos 位后的 5 位数字
                    for (int j = 0; j < 6; j++) {
                        if (j != pos) {
                            val = val * 10 + (passwords[k].charAt(j) - '0');
                        }
                    }

                    // 如果这个数字没出现过，记录并累加
                    if (visited[val]) {
                        visited[val] = true;
                        distinctCount++;
                    }
                }

                // 记录所有修改方案中，最少的种类数
                minDistinct = Math.min(minDistinct, distinctCount);
            }

            pw.println(minDistinct);
        }

        // 刷新输出流
        pw.flush();
    }
}
