package 网易互娱.D20260306.T1.作答;

import java.io.*;
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class T1 {
    public static void main(String[] args) {
        //加速输入输出
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));


        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//玩家人数
        int[] p = new int[n];//出生点
        int[] q = new int[n];//终点
        for(int i = 0; i < n; i++){
            p[i] = in.nextInt();
            q[i] = in.nextInt();
        }

        long[] ans = new long[n];//持伞走的距离
        Queue<Integer> queue = new ArrayDeque<>();//存队伍中人的下标
        int nextIndex = 0;//下一个人的下标
        int pos = p[0];//当前伞位置

        while(!queue.isEmpty() || nextIndex < n){
            //队列空，下个人入队
            if(queue.isEmpty()){
                ans[nextIndex] += p[nextIndex]-pos;
                pos = p[nextIndex];
                queue.add(nextIndex);
                nextIndex++;
            }

            //当前队首到地方,则推出
            while(!queue.isEmpty() && q[queue.peek()] <= pos){
                queue.poll();
            }
            //两种事件：
            //1、遇到队首的终点 false
            //2、遇到下个人入队 true
            boolean isRuDui = false;
            int target = 0;//记录下一步的目标位置
            if(queue.isEmpty()){
                if(nextIndex < n){
                    continue;
                }else{
                    break;
                }
            }
            int cur = queue.peek();

            if(nextIndex >= n){
                target = q[cur];
                ans[cur] += target - pos;
                pos = target;
                continue;
            }
            if(q[cur] > p[nextIndex]){//先到下个人入队
                isRuDui = true;
                target = p[nextIndex];
                ans[cur] += target - pos;
                pos = target;
            }else{//先到队首的终点
                target = q[cur];
                ans[cur] += target - pos;
                pos = target;
            }

            if(isRuDui){
                queue.add(nextIndex);
                nextIndex++;
            }else{
                queue.poll();
            }
        }


        for(int i = 0; i < n; i++){
            System.out.print(ans[i] + " ");
        }


    }
}
