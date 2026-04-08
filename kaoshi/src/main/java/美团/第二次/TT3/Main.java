package 美团.第二次.TT3;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = in.nextInt();
        }

        Map<Integer, List<Integer>> bian = new HashMap<>();
        for(int i = 0; i < n; i++){
            List<Integer> list = new ArrayList<>();
            bian.put(i,list);
        }
        for(int i = 0; i < n-1; i++){
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            bian.get(u).add(v);
        }

        for(int i = 0; i < m; i++){
            int x  = in.nextInt();
            int u = in.nextInt();
            int v = in.nextInt();
            if(x==1){
                fanzhi(a, bian,u,v);
            }
            if(x==2){
                xunwen(u,v);
            }
        }

    }
    public static void fanzhi(int[] a,Map<Integer, List<Integer>> bian, int u, int v){
        List<Integer> list = bian.get(u);
        if(list.contains(v)){
            a[v] = 1 - a[v];
            return;
        }else{
            for(int i = 0; i < list.size(); i++){
                fanzhi(a,bian,list.get(i),v);
            }
        }
    }
    public static int xunwen(int u, int v){
        return 0;
    }
}

/*
输入：
5 5
0 0 0 0 0
1 2
1 3
2 4
2 5
2 1 4
1 1 3
2 4 1
1 2 5
2 5 1

输出：
0
1
7
 */
