package 阿里.T20260325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
题目描述
【阿里】2026-3-25-第一题-圣诞老人分糖果

题目描述：
圣诞老人有 n 种糖果，第 i 种糖果有 ci 个。他要把这些糖果分给 k 个小朋友。分配规则如下：

每一种糖果必须全部分完。

对于任意一种糖果，任意两个小朋友所分得的该种糖果的数量之差不超过 1。
在所有合法分配中，任选一个小朋友，统计其最终得到的糖果总数；求该数值在所有合法分配方案中可能达到的最小值与最大值。换句话说，求在所有合法分配方案中，任意一个小朋友所能获得的糖果总数最小值与最大值。

输入描述：
每个测试文件均包含多组测试数据。第一行输入一个整数 T 代表数据组数。每组测试数据描述如下：
第一行输入两个整数 n, k (1 <= n <= 210^5, 1 <= k <= 10^9)。
第二行输入 n 个整数 c1, ..., cn (0 <= ci <= 10^9)。
保证单个测试文件的 n 之和不超过 510^5。

输出描述：
对于每组测试数据，输出两个整数：在所有合法分配中，单个小朋友可能得到的糖果总数的最小值与最大值。

样例1：
输入：
3
3 2
5 2 7
4 3
0 0 0 0
2 5
10 1

输出：
6 8
0 0
2 3
* */
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens())  return;
        int T = Integer.parseInt(st.nextToken());

        StringBuffer sb = new StringBuffer();

        while(T-->0){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st1.nextToken());
            int k = Integer.parseInt(st1.nextToken());

            long minTotal = 0;//最少
            long maxTotal = 0;//最多

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int i =0;i<n;i++){
                int c = Integer.parseInt(st2.nextToken());

                long base = (long) c/k;//每人基础分的
                long rem = c%k;//多出来的

                minTotal += base;
                maxTotal += (rem > 0)?(base+1):base;
            }

            sb.append(minTotal).append(" ").append(maxTotal).append("\n");
        }

        System.out.print(sb);
    }
}
