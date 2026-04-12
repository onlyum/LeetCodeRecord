package 携程.D20260329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
【携程】2026-3-12-第一题-n的阶乘
EASY  时间限制：1000ms  空间限制：256M
题目描述
众所周知 n! 很像一个尾巴，表示 n 的阶乘即 1 × 2 × ... × n。
给定一个整数 n ，AK机想知道 n! 的个位数字是多少，请输出这个值。
输入描述
输入一行一个整数 n(1 ≤ n ≤ 10) 表示询问值。
输出描述
输出一个整数表示 n! 的个位数字。
样例1
输入
1
输出
1
样例2
输入
3
输出
6
 */
public class 研发T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        if(!st.hasMoreTokens()) return;
        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){

        }

    }
}
