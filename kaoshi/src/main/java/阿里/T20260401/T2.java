package 阿里.T20260401;
/*
【阿里研发岗】2026-4-1-第二题-约束差值数组

题目描述
在幻光实验室中，Alice 需要构造一个长度为 n 的正整数数组 a ，其中每个元素 a_i > 0。但她手中有 m 条魔法约束，每条约束给出三元组 (i, j, k)，要求 a_i - a_j = k。请你判断是否存在满足所有约束且 1 <= a_i <= 10^18 的数组 a。若存在则输出任意一个符合条件的数组；否则输出 -1。

输入描述
每个测试文件均包含多组测试数据。第一行输入一个整数 T(1 <= T <= 10^5) 代表测试组数，每组测试数据描述如下：
第一行输入两个整数 n, m(1 <= n, m <= 2 * 10^5)。
接下来 m 行，每行输入三个整数 i, j, k(1 <= i, j <= n, -10^6 <= k <= 10^6)。
保证所有测试中 n 的总和不超过 5 * 10^5，m 的总和不超过 5 * 10^5。

输出描述
对于每组测试数据，输出一行：若存在满足所有约束且 1 <= a_i <= 10^18 的数组 a ，则输出 n 个正整数 a_1, a_2, ..., a_n；否则输出 -1。

样例1
输入
2
3 2
1 2 1
2 3 1
2 2
1 2 100
2 1 1

输出
3 2 1
-1
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class T2 {
    //理解：
    //档案柜：有2*m个抽屉，每个抽屉中若干档案
    //每个edgeCount当作一个档案，有[to, weight]作为档案内容，nxt作同抽屉中检索用
    //head就是档案柜的所有抽屉面板显示的编号

    // --- 链式前向星建图相关参数 ---
    // head 数组部分初始化为 -1
    static int[] head = new int[500005];
    // m 的总和不超过 5*10^5，因为我们建的是双向边（u->v 和 v->u），所以边的数量最多是 1000000
    static int[] to = new int[1000005];    // to[e] 档案内容：编号为 e 的边指向的终点节点
    static long[] weight = new long[1000005]; // weight[e] 档案内容：编号为 e 的边的权重（即题目中的差值 k）
    static int[] nxt = new int[1000005];   // nxt[e] 抽屉内部索引：与编号为 e 的边“同起点”的上一条边的编号
    static int edgeCount = 0;              // edgeCount 档案全局索引：是全局边的计数器，同时也作为每一条新边的“编号 ID”
    // --- BFS 遍历及业务逻辑相关参数 ---
    static long[] val = new long[500005];  // val[i] 记录推算出来的节点 i 的相对值（即 a_i 的值）
    static boolean[] visited = new boolean[500005]; // visited[i] 标记节点 i 是否在 BFS 中被访问过
    static int[] comp = new int[500005];   // comp 相当于一个手写的队列（或者叫数组列表），用来临时存放在同一个连通块里的所有节点，方便后续统一调整数值

    //链式前向星加边
    static void addEdge(int u,int v,long w){
        // 1. 记录档案内容
        weight[edgeCount] = w;
        to[edgeCount] = v;
        // 2. 头插法建柜内索引
        nxt[edgeCount] = head[u];
        // 3. 更新抽屉面板编号
        head[u] = edgeCount++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(!st.hasMoreTokens()) return;
        int T = Integer.parseInt(st.nextToken());

        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            //初始化
            for(int i=1;i<=n;i++){
                head[i] = -1;
                visited[i] = false;
            }

            edgeCount = 0;
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long w = Integer.parseInt(st.nextToken());

                //双向图
                addEdge(u, v, w);
                addEdge(v, u, -w);
            }

            boolean possible = true;
            //遍历所有节点处理
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    int compSize = 0;
                    comp[compSize++] = i;// 把起点 i 塞进队列
                    val[i] = 0; // 极其关键：起点的绝对值是多少不重要，先假设它是 0
                    visited[i] = true;
                    long minVal = 0;// 最小相对值初始为 0，记录“当前连通块”中推算出的最小的 val 值

                    // BFS 遍历连通块
                    int headPtr = 0;
                    while (headPtr < compSize) {
                        int curr = comp[headPtr++];// 经典手写队列：出队

                        for (int e = head[curr]; e != -1; e = nxt[e]) {
                            int nextNode = to[e];
                            long w = weight[e];

                            if (!visited[nextNode]) {
                                // 还没访问过：计算它的相对值 = 当前节点的值 + 边的权重
                                visited[nextNode] = true;
                                val[nextNode] = val[curr] + w;
                                minVal = Math.min(minVal, val[nextNode]);// 顺手记录连通块里的最小值
                                comp[compSize++] = nextNode;// 入队
                            } else {
                                // 【核心矛盾检测】：如果这个邻居以前被访问过，说明有环！
                                // 此时它早就被赋过值了。我们要检查：从别的路算出来的它的值，和从当前 curr 算过去的值，一样吗？
                                if (val[nextNode] != val[curr] + w) {
                                    possible = false; // 不一样！Alice 被骗了，存在自相矛盾的约束
                                    break;
                                }
                            }
                        }
                        if (!possible) break;
                    }

                    if (!possible) break;

                    // 整体拔高当前连通块，使其最小值为1，保证全部为正整数
                    long shift = 1 - minVal;
                    for (int j = 0; j < compSize; j++) {
                        val[comp[j]] += shift;
                    }
                }
            }

        }

    }
}
