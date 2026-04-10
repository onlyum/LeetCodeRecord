package 网易互娱.D20260409;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class T3答案 {
    public static void main(String[] args) throws IOException {
        // 1. 高效 I/O 准备
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        if (!st.hasMoreTokens()) return;

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        double[][] doc = new double[n][d];
        double[] query = new double[d];
        double[] score = new double[n]; // 专门存分数的数组

        // 2. 读取 Document 矩阵
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < d; j++) {
                doc[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        // 3. 读取 Query 向量并提前计算模长的平方
        st = new StringTokenizer(br.readLine());
        double queryNormSq = 0;
        for (int i = 0; i < d; i++) {
            query[i] = Double.parseDouble(st.nextToken());
            queryNormSq += query[i] * query[i];
        }

        // 4. 计算所有文档的 Cosine Similarity 得分
        for (int i = 0; i < n; i++) {
            double dotProduct = 0;
            double docNormSq = 0;
            for (int j = 0; j < d; j++) {
                dotProduct += query[j] * doc[i][j];
                docNormSq += doc[i][j] * doc[i][j];
            }
            if (queryNormSq != 0 && docNormSq != 0) {
                score[i] = dotProduct / (Math.sqrt(queryNormSq) * Math.sqrt(docNormSq));
            }
        }

        // ==========================================
        // 5. 核心：方案 A（排序索引数组）全流程
        // ==========================================

        // 第一步：创建一个必须是包装类 Integer[] 的索引数组
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; i++) {
            index[i] = i; // 初始状态就是 [0, 1, 2, ..., n-1]
        }

        // 第二步：使用 Lambda 表达式自定义排序规则
        // a 和 b 分别代表 index 数组里准备进行比较的两个索引
        Arrays.sort(index, (a, b) -> {
            // 通过索引 a 和 b 去 score 数组里查出对应的实际分数
            if (score[a] != score[b]) {
                // 【核心：如何实现降序】
                // Java 默认是升序。如果写 Double.compare(score[a], score[b])，则是分数从小到大排。
                // 为了实现降序（分数大的排前面），我们故意颠倒参数的位置，写成 (score[b], score[a])。
                // 这样当 score[b] < score[a] 时，compare会返回负数，Java就会把此时的 a 判定为“更小/更优先”，从而排在前面，达到降序的目的。
                return Double.compare(score[b], score[a]);
            }
            // 【核心：如何实现升序】
            // 如果分数相同，按照 ID 升序排（ID 小的排前面）。
            // 升序是 Java 的默认行为，所以我们按正常的顺序 (a, b) 传参即可。
            return Integer.compare(a, b);
        });

        // ==========================================

        // 6. 输出 Top-K 结果
        int limit = Math.min(k, n); // 防御性编程：如果 K 大于 N，最多只能输出 N 个
        for (int i = 0; i < limit; i++) {
            pw.print(index[i]); // 此时的 index 数组已经是按分数排好序的 ID 了
            if (i < limit - 1) {
                pw.print(" ");
            }
        }

        // 7. 刷新并关闭流
        pw.flush();
        pw.close();
    }
}