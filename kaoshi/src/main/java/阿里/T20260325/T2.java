package 阿里.T20260325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
【阿里】2026-3-25-第二题-公共子序列

MIDDLE | 时间限制：1000ms | 空间限制：256M

题目描述：
给定两个长度为 n 的排列 p 和 q（均为 1 到 n 的一个排列，元素两两不同），请你在它们的公共子序列中，找到一条字典序最大的序列并输出。

名词解释：

字典序：对两序列从左到右比较第一个不同的元素，较大者字典序更大；若一个序列是另一个序列的前缀，则更长的序列字典序更大。

子序列：子序列为从原序列中删除任意个（可以为零、可以为全部）元素得到的新序列。

数组公共子序列：如果数组 a 的一个子序列 a' 与数组 b 的一个子序列 b' 完全相等，那么子序列 a', b' 是数组 a, b 的一个公共子序列。

输入描述：
第一行输入一个整数 n (1 <= n <= 2 * 10^5)，表示排列的长度。
第二行输入 n 个整数 p1, p2, ..., pn，表示排列 p。
第三行输入 n 个整数 q1, q2, ..., qn，表示排列 q。
保证 p 与 q 都是 1 到 n 的排列。

输出描述：
第一行输出一个整数 k，表示答案序列的长度。
第二行输出 k 个整数，为这条字典序最大的公共子序列。

样例1：
输入：
5
3 5 1 2 4
2 5 4 1 3

输出：
2
5 4

样例解释：
两序列的公共子序列中，以 5 开头后，能够继续选择的最大元素为 4，得到 [5, 4]。与 [5, 1] 相比，第二个元素更大，因此 [5, 4] 的字典序更大。
 */
public class T2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        int k = Integer.parseInt(st.nextToken());

        int[] pPos = new int[k+1];//记录1~k在原序列中的位置
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            int val = Integer.parseInt(st1.nextToken());//1~k
            pPos[val] = i;
        }

        int[] qPos = new int[k+1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            int val = Integer.parseInt(st2.nextToken());
            qPos[val] = i;
        }

        ArrayList<Integer> list = new ArrayList<>();
        int currP = 0;//p起始下标
        int currQ = 0;//q起始下标
        //贪心
        for(int cand = k;cand>0;cand--){
            //如果候选cand的下标在P和Q当前下标之后，则加入
            if(pPos[cand] >= currP && qPos[cand] >= currQ){
                list.add(cand);

                currP = pPos[cand] + 1;
                currQ = qPos[cand] + 1;
            }
        }

        System.out.println(list.size());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i< list.size();i++){
            sb.append(list.get(i));
            if(i<list.size()-1){
                sb.append(" ");
            }
        }
        System.out.print(sb);
    }
}
