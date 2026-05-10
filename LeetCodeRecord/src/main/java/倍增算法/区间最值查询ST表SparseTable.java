package 倍增算法;

public class 区间最值查询ST表SparseTable {
    public class SparseTable {
        static int[][] st;
        static int[] arr;

        // 预处理 ST 表
        static void buildST(int[] input) {
            int n = input.length;
            // 计算最大需要的 log 级别
            int maxLog = 31 - Integer.numberOfLeadingZeros(n) + 1;
            st = new int[n][maxLog];
            arr = input;

            // 长度为 2^0 (即 1) 的区间最大值就是它本身
            for (int i = 0; i < n; i++) {
                st[i][0] = arr[i];
            }

            // 状态转移方程：st[i][j] = max(st[i][j-1], st[i + 2^(j-1)][j-1])
            for (int j = 1; j < maxLog; j++) {
                // i + 2^j - 1 必须 < n，保证区间不越界
                for (int i = 0; i + (1 << j) - 1 < n; i++) {
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        // O(1) 时间查询区间 [L, R] 的最大值 (L 和 R 为 0-based 索引)
        static int queryMax(int L, int R) {
            int len = R - L + 1;
            // 找到最大的 k，使得 2^k <= len
            int k = 31 - Integer.numberOfLeadingZeros(len);

            // 取两个长度为 2^k 的区间的最大值（这两个区间会有重叠，但覆盖了整个 [L, R]）
            return Math.max(st[L][k], st[R - (1 << k) + 1][k]);
        }

        public static void main(String[] args) {
            int[] data = {5, 2, 8, 3, 7, 1, 9, 4, 6}; // 长度为 9
            buildST(data);

            System.out.println("--- ST 表区间最大值查询测试 ---");
            System.out.println("数组: [5, 2, 8, 3, 7, 1, 9, 4, 6]");
            System.out.println("区间 [0, 2] (即 5,2,8) 的最大值: " + queryMax(0, 2)); // 预期输出: 8
            System.out.println("区间 [3, 5] (即 3,7,1) 的最大值: " + queryMax(3, 5)); // 预期输出: 7
            System.out.println("区间 [0, 8] (整个数组) 的最大值: " + queryMax(0, 8)); // 预期输出: 9
        }
    }
}
