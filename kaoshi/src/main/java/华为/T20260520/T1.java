package 华为.T20260520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
5
30 11 23 4 20
5

4
3 6 7 11
8
 */
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;

        int n = Integer.parseInt(st.nextToken());
        int[] tasks = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            tasks[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());

        Arrays.sort(tasks);

        int minRes = 0x3f3f3f3f;
        int minIndex = -1;
        {
            int fwq = tasks[n-1];
            int time = 0;
            boolean isNone = false;
            for(int j=n-1; j >= 0; j--){//从大到小遍历耗时
                if(tasks[j]%fwq==0)   time += tasks[j]/fwq;
                else time += tasks[j]/fwq + 1;
                if(time > h){
                    isNone = true;//不满足
                    break;
                }
            }
            if(isNone){
                pw.println(-1);
                pw.close();
                return;
            }
        }
        //排序从最小开始逐步取task[i]
        for(int i = n-1; i >= 0; i--){
            int fwq = tasks[i];
            int time = 0;
            boolean isNone = false;
            for(int j=n-1; j >= 0; j--){//从大到小遍历耗时
                if(tasks[j]%fwq==0)   time += tasks[j]/fwq;
                else time += tasks[j]/fwq + 1;
                if(time > h){
                    isNone = true;//不满足
                    break;
                }
            }
            if(!isNone){
                minRes = Math.min(minRes, fwq);
                minIndex = i;
            }
        }

        if(minRes == 0x3f3f3f3f){
            pw.println(-1);
            pw.close();
            return;
        }

        if(minIndex == 0){//刚好第一个是答案
            pw.println(minRes);
            pw.flush();
            pw.close();
        }

        //继续往前找
        int left = tasks[minIndex-1];
        int right = tasks[minIndex];
        while(left < right){
            int mid = left + (right - left) / 2;
            int fwq = mid;
            int time = 0;
            boolean isNone = false;
            for(int j=n-1; j >= 0; j--){
                if(tasks[j]%fwq==0)   time += tasks[j]/fwq;
                else time += tasks[j]/fwq + 1;
                if(time > h){
                    isNone = true;//不满足
                    break;
                }
            }
            if(!isNone){//mid满足条件保留
                right = mid;
            }else{//mid不满足条件
                left = mid + 1;
            }
        }

        pw.println(right);
        pw.flush();
        pw.close();
    }
}
