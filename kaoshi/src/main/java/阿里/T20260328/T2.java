package 阿里.T20260328;
import java.io.*;
import java.util.*;

/**
 * 题目简介：
 * 定义一个正整数为“完全完全平方数”，当且仅当满足：
 * 1. 该数本身是完全平方数。
 * 2. 存在一个切分点，将其十进制表示切分为两段，两段均非空且对应的整数也是完全平方数。
 * 3. 切分后任何一段都不允许有前导零（除非该段仅为单个字符 '0'）。
 * 给定上限 n，问不超过 n 的完全完全平方数共有多少个。
 *
 * 解题思路：
 * 1. 根据题意，n 的最大值为 10^9。完全平方数的底数最多只有 sqrt(10^9) ≈ 31622。
 * 2. 可以在 O(sqrt(n)) 的时间内预处理出所有符合条件的“完全完全平方数”。
 * 3. 对于 1 到 31622 的每一个整数 i，计算 x = i * i，并转换为字符串。
 * 4. 遍历所有可能的切分点，分成 left 和 right 两部分，检查前导零以及是否都是完全平方数。
 * 5. 将符合条件的 x 加入预处理列表中。
 * 6. 对于每组输入查询，直接统计列表中 <= n 的元素个数即可。
 */
public class T2 {

    // 判断一个数字是否是完全平方数
    static boolean isPerfectSquare(long num) {
        if (num < 0) return false;
        long root = (long) Math.round(Math.sqrt(num));
        return root * root == num;
    }

    public static void main(String[] args) throws IOException {
        // --- 1. 预处理 ---
        List<Long> validNumbers = new ArrayList<>();
        long limit = 1000000000L;
        long maxBase = (long) Math.sqrt(limit);

        // 遍历所有可能的平方根
        for (long i = 1; i <= maxBase; i++) {
            long x = i * i;
            String s = String.valueOf(x);
            boolean isValid = false;

            // 枚举所有的切分点（切分为两段，所以切分点在 1 到 length-1 之间）
            for (int j = 1; j < s.length(); j++) {
                String leftStr = s.substring(0, j);
                String rightStr = s.substring(j);

                // 检查前导零限制：任一段不允许有前导零（除非该段仅为单个字符 '0'）
                if (leftStr.length() > 1 && leftStr.charAt(0) == '0') continue;
                if (rightStr.length() > 1 && rightStr.charAt(0) == '0') continue;

                long leftVal = Long.parseLong(leftStr);
                long rightVal = Long.parseLong(rightStr);

                // 如果切分后的两段都是完全平方数
                if (isPerfectSquare(leftVal) && isPerfectSquare(rightVal)) {
                    isValid = true;
                    break; // 只要找到一种满足的切分方式即可
                }
            }

            if (isValid) {
                validNumbers.add(x);
            }
        }

        // --- 2. 快速输入与输出 ---
        // 使用 BufferedReader 和 StringTokenizer 进行快速输入，适配题目类似的格式
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());

            // 统计不超过 n 的完全完全平方数的数量
            int count = 0;
            for (long val : validNumbers) {
                if (val <= n) {
                    count++;
                } else {
                    break; // 列表是有序的，遇到大于 n 的数字即可提前跳出
                }
            }
            pw.println(count);
        }

        pw.flush();
    }
}
