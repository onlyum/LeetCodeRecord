package 阿里.T20260401;
/*
【阿里研发岗】2026-4-1-第一题-数组对齐
EASY  时间限制：1000ms  空间限制：256M

题目描述
AK机拿到两个长度均为 n 的非负整数数组 a1, a2, ..., an 与 b1, b2, ..., bn。她可以反复执行以下三种操作（每次操作会让所选元素的值减 1；若被选中的元素当前为 0，则该次操作不允许执行）：
操作1：选择一个下标 i，令 ai = ai - 1。
操作2：选择一个下标 j，令 bj = bj - 1。
操作3：选择两个下标 i, j（i, j 可以相同，也可以不同），同时令 ai = ai - 1 且 bj = bj - 1。
现在她希望通过若干次操作，使得最终对所有 1 ≤ i ≤ n 都满足 ai = bi。
请你计算：最少需要多少次操作才能达成目标。

输入描述
每个测试文件均包含多组测试数据。第一行输入一个整数 T(1 ≤ T ≤ 2×10^5) 表示数据组数，每组测试数据描述如下：
第一行输入一个整数 n(1 ≤ n ≤ 2×10^5)。
第二行输入 n 个整数 a1, a2, ..., an (0 ≤ ai ≤ 10^9)。
第三行输入 n 个整数 b1, b2, ..., bn (0 ≤ bi ≤ 10^9)。
除此之外，保证单个测试文件中所有测试数据的 n 之和不超过 2×10^5。

输出描述
对于每组测试数据，新起一行输出一个整数，表示最少操作次数。

样例1
输入
2
4
1 2 3 4
2 1 3 5
3
0 0 5
2 1 0

输出
2
5

样例解释
第1组：可先执行一次操作3，选(i, j) = (2, 1)；再执行一次操作2，选 j = 4，即可使两数组完全相等，因此最少操作数为 2。

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        int T = Integer.parseInt(st.nextToken());

        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int[] a = new int[n];
            for(int i=0;i<n;i++){
                a[i] = Integer.parseInt(st1.nextToken());
            }

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int[] b = new int[n];
            for(int i=0;i<n;i++){
                b[i] = Integer.parseInt(st2.nextToken());
            }

            int[] diff = new int[n];
            long countMinusA = 0;
            long countMinusB = 0;
            for(int i=0;i<n;i++){
                diff[i] = a[i] - b[i];
                if(diff[i]>0){
                    countMinusA+=diff[i];
                }else if(diff[i]<0){
                    countMinusB+= -diff[i];
                }
            }

//            long count1 = Math.min(countMinusA, countMinusB);
//            long count2 = Math.abs(countMinusA-countMinusB);
//            System.out.print(count1 + count2);

            System.out.print(Math.max(countMinusA, countMinusB));

        }

    }
}
