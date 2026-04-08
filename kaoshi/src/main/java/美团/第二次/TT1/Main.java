package 美团.第二次.TT1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[][] arr = new int[T][];
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            arr[i] = new int[n];
            for(int j=0;j<n;j++){
                arr[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < T; i++) {
            System.out.println(lengthMax(arr[i]));
        }
    }
    public static int lengthMax(int[] arr){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(Integer.MIN_VALUE);
        for(int a:arr){
            pq.offer(a);
        }
        List<Integer> list = new ArrayList<>();
        int pre = pq.poll(), cur;
        while(!pq.isEmpty()){
            cur = pq.poll();
            if(pre==cur){
                continue;
            }else{
                list.add(cur);
                pre = cur;
            }
        }
        return list.size();
    }
}
