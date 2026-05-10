package WPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        // 确保 min 和 max 大小顺序正确
        long min = Math.min(a, b);
        long max = Math.max(a, b);

        long[] result = solution(min, max);

        // 输出 0-9 这 10 个数字的频率，用空格隔开
        for(int i = 0; i < 10; i++){
            pw.print(result[i] + (i == 9 ? "" : " "));
        }
        pw.println();

        pw.flush();
        pw.close();
    }

    /*
     * 核心转换思想：前缀和
     * 求区间 [a, b] 的次数，转化为：
     * [1, b] 中出现的总次数 减去 [1, a-1] 中出现的总次数
     */
    static long[] solution(long a, long b){
        long[] countB = countDigits(b);
        long[] countA = countDigits(a - 1);
        long[] ans = new long[10];

        for (int i = 0; i < 10; i++) {
            ans[i] = countB[i] - countA[i];
        }
        return ans;
    }

    /*
     * 核心算法：数位DP/按位统计
     * 统计从 1 到 n 所有数字中，0-9 每种数码一共出现了多少次
     *
     * 【详细推演案例】假设我们正在处理 n = 324，并且 m = 10（意味着我们正在统计“十位”上各个数字的出现次数）
     */
    static long[] countDigits(long n) {
        long[] count = new long[10];
        if (n <= 0) return count;

        long m = 1; // m 代表当前的位权：1(个位), 10(十位), 100(百位)...

        while (n / m > 0) {
            // 针对 n = 324, m = 10：
            long current = (n / m) % 10; // 当前位数字：(324 / 10) % 10 = 2
            long high = n / (m * 10);    // 高位数字：324 / 100 = 3
            long low = n % m;            // 低位数字：324 % 10 = 4

            // 遍历我们要统计的数码 d (0 到 9)
            for (int d = 0; d < 10; d++) {

                /*
                 * 步骤 1：计算【高位带来的“保底”次数】
                 * 解释：不管 d 是几，百位(high)经历了 0, 1, 2 这三个完整阶段(共3个)。
                 * 在每个100个数里，十位上每个数字(0-9)都会固定出现 10次(m次)。
                 * 所以保底次数 = high * m = 3 * 10 = 30次。
                 */
                count[d] += high * m;

                /*
                 * 步骤 2：计算【当前位带来的“额外”次数】
                 * 解释：现在百位进入了 '3' 的阶段 (300~324)，我们要看数字 d 在这 25 个数里出现了几次。
                 */
                if (d < current) {
                    // 情况 A: 目标数字 d 比当前位 2 小（比如找数字 1）
                    // 在 300~324 间，十位是 1 的群体(310~319)已经完整出现过了。
                    // 所以可以拿满 m 次 (10次)。
                    count[d] += m;
                } else if (d == current) {
                    // 情况 B: 目标数字 d 和当前位一样大（比如找数字 2）
                    // 在 300~324 间，十位是 2 的群体(320~329)还没走完，只走到了 324。
                    // 所以一共出现了 low + 1 次 (4 + 1 = 5次)，即 320,321,322,323,324。
                    count[d] += low + 1;
                }
                // 情况 C: 如果 d > current（比如找数字 5），在 300~324 间还没轮到它出现，加 0 次，省略不写。
            }

            /*
             * 步骤 3：【前导零特判】（最容易出错的地方）
             * 解释：回顾步骤 1，当我们在算保底次数(high * m)时，我们把百位(high)为 '0' 的阶段也算进去了。
             * 也就是说，我们默认存在 000~099 这个区间，并认为它贡献了 10 个十位上的 '0'（000~009）。
             * 但在现实中，数字 5 就是 5，不叫 05，它根本没有十位，所以这些十位上的 '0' 是假象（前导零）。
             * 解决办法：直接把这 m 个（10个）并不存在的假零扣掉就行了！
             */
            count[0] -= m;

            m *= 10; // 进阶到下一位（处理完个位去十位，处理完十位去百位...）
        }
        return count;
    }
}