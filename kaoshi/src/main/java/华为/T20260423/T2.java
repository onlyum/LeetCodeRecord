package 华为.T20260423;
/**
 * 描述
 * 帕秋莉正在红魔馆的地下图书馆构建一套魔导传输网络。该网络由
 * n 个魔导单元组成，单元之间通过
 * m 条双向传输通道进行信息交换。
 * 每条通道连接两个特定的单元，并具有一定的传输延迟
 * 传输延迟c
 * 由于魔导路径的复杂性，同一对单元之间可能存在多条不同的通道。
 * 咲夜需要协助帕秋莉计算特定单元对之间的最小传输总延迟。如果两个单元之间存在多条路径，咲夜必须找出总延迟之和最小的一条；若两个单元之间没有任何路径相连，则认为它们无法建立通信。

 * 输入描述：
 * 第一行包含两个整数
 * n，m分别表示魔导单元的数量和传输通道的数量。
 * 接下来的m 行，每行包含三个整数
 * a b c 表示单元a与单元b之间存在一条双向传输通道，其延迟为c
 * 接下来的一个整数 k 表示咲夜需要进行的查询次数。
 * 接下来的k行，每行包含两个整数i,j表示询问单元

 * 输出描述：
 * 输出共 k 行。对于每组查询，输出一个整数表示最小延迟。
 * 若两单元间不存在任何路径，则输出0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class T2 {
    /**
     * 0x3f3f3f3f约10.6亿
     * 算法中int范围内的标准最大值
     */
    static final int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st =  new StringTokenizer(br.readLine().trim());
        if(!st.hasMoreTokens())  return;
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        //无向图复制
        int[][] graph = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for(int i=0 ; i<m ; i++){
            st =  new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.min(graph[u][v], w);
            graph[v][u] = Math.min(graph[v][u], w);
        }
        /**
         Floyd-Warshall算法
         Floyd 是一个严格的动态规划算法，k层依赖k-1层
         */
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(graph[i][k]!=INF &&  graph[k][j]!=INF){
                        graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                    }
                }
            }
        }

        st =  new StringTokenizer(br.readLine().trim());
        int k = Integer.parseInt(st.nextToken());
        while(k-->0){
            st =  new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(graph[a][b]==INF){
                pw.println("0");
            }else{
                pw.println(graph[a][b]);
            }
        }

        pw.flush();
        pw.close();
    }
}
