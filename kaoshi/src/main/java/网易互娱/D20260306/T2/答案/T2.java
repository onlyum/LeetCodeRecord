package 网易互娱.D20260306.T2.答案;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
【网易互娱】2026-3-6-第二题-蛋仔滚动
题目描述
给定一个 m 行 n 列的地图，蛋仔从起点 (sr, sc) 出发，需要到达终点 (er, ec)。
蛋仔每次可以选择向上、下、左、右四个方向之一开始滚动。一旦开始滚动，在遇到阻挡前无法主动停下。为了防止蛋仔滚出地图，地图外侧视为额外包裹了一圈障碍物。
地图中有三类特殊格子：# 为障碍物，蛋仔不能进入；/ 为斜挡板；\ 为反斜挡板。题目保证起点和终点所在格子均不是特殊格子，给出的特殊格子坐标互不重复。
挡板本身占据一个格子，蛋仔进入挡板格后会立即根据挡板类型改变方向并继续滚动。
/ 型挡板：从左侧进入改为向上，从右侧进入改为向下，从上方进入改为向左，从下方进入改为向右。
\ 型挡板：从左侧进入改为向下，从右侧进入改为向上，从上方进入改为向右，从下方进入改为向左。
补充说明：一次"滚动"定义为从某个静止位置出发，选择一个方向，直到再次停下为止，即使途中多次改变方向也只记为 1 次滚动。
若蛋仔即将进入某个挡板格，但按挡板规则计算出的出口方向对应的下一格是障碍物或另一个挡板，则该挡板视为不可进入，蛋仔停在进入挡板前的那一格。
只要蛋仔在某次滚动过程中经过终点格，就视为成功到达，不需要恰好停在终点。若蛋仔在挡板间无限反弹且经过了终点，仍视为可以到达。
请求出蛋仔从起点到终点的最少滚动次数，若无法到达输出 -1。

输入描述
第一行输入两个整数 m, n (1 ≤ m, n ≤ 100)，表示地图的行数和列数。
第二行输入一个整数 k (0 ≤ k ≤ 10000)，表示特殊格子的数量。
接下来 k 行，每行输入三个值 r, c, ch，表示第 r 行第 c 列是一个特殊格子，类型为 ch (#、/ 或 )。
接下来一行输入两个整数 sr, sc (1 ≤ sr ≤ m, 1 ≤ sc ≤ n)，表示起点坐标。
最后一行输入两个整数 er, ec (1 ≤ er ≤ m, 1 ≤ ec ≤ n)，表示终点坐标。所有坐标均为 1-based。

输出描述
输出一个整数，表示蛋仔到达终点的最少滚动次数；若无法到达，输出 -1。

样例1
输入:
3 5
3
1 3 #
1 5 \
2 5 /
2 1
2 3

输出:
1

样例解释
地图如下 (S 为起点，E 为终点，外圈为虚拟障碍物)：
#######
#..#.#
#S.E./#
#.....#
#######
蛋仔从起点向右滚动 1 次，滚动过程中经过终点，答案为 1。
 */
public class T2 {
    static int m, n;
    static char[][] map;
    static Point[] memo;
    static int er, ec;
    //‘\’:0->3;1->2;2->1;3->0                       3-当前 即可
    //‘/’:0->1;1->0;2->3(10B->11B);3->2(11B->10B)   异或1 即可
    // 行进方向定义：0:上, 1:右, 2:下, 3:左(顺时针顺序方便后续通过位运算计算转向)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    // 坐标点类
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 预定义特殊状态标识，用于 memo 数组的标记
    static final Point VISITING = new Point(-2, -2);    // 正在访问中（用于检测是否成环）
    static final Point LOOP = new Point(-1, -1);    // 已确认为死循环
    static final Point END = new Point(-3, -3);     // 成功经过终点

    public static void main(String[] args) throws IOException {
// ================= 步骤 1：读取输入并初始化地图 =================
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(map[i], '.');   // 默认全填为空地

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            // 题目输入是 1-based，代码里转成 0-based 方便数组操作
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            char ch = st.nextToken().charAt(0);
            map[r][c] = ch;
        }

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken()) - 1;
        int sc = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        // 特判：如果起点就是终点，直接不用滚了
        if (sr == er && sc == ec) {
            System.out.println(0);
            return;
        }
// ================= 步骤 2：初始化 BFS 所需的结构 =================
        // memo 大小：行数 * 列数 * 4个方向，确保能存下每一个独特的状态
        memo = new Point[m * n * 4];
        // dist 数组记录最少步数
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], -1);

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sr, sc));
        dist[sr][sc] = 0;// 起点的步数为 0

// ================= 步骤 3：开始广度优先搜索 (BFS) =================
        while (!q.isEmpty()) {
            Point curr = q.poll();
// 蛋仔每次可以向 4 个方向发车
            for (int d = 0; d < 4; d++) {
                // 核心：调用仿真器，看看从 curr 点往 d 方向滚，最终会停在哪里
                Point stopped = simulate(curr.r, curr.c, d);
                // 情况 A：如果在滚动中途或者最终停靠点碰到了终点，直接输出答案
                if (stopped == END) {
                    System.out.println(dist[curr.r][curr.c] + 1);
                    return;
                }
                // 情况 B：如果陷入了挡板间的无限反弹（死循环），这条路走不通，看下一个方向
                if (stopped == LOOP) {
                    continue; // 陷入无限死循环且未能碰触终点，这条路废弃
                }

                // 情况 C：停在了一个有效的新位置，且这个位置以前没停靠过
                if (dist[stopped.r][stopped.c] == -1) {
                    dist[stopped.r][stopped.c] = dist[curr.r][curr.c] + 1;
                    q.offer(stopped);
                }
            }
        }
        // 如果队列空了还没找到终点，说明到不了
        System.out.println(-1);
    }

// ================= 辅助函数区 =================
    // 辅助函数：安全地获取地图格子内容。如果超出边界，统一视为撞到障碍物 '#'
    static char getCell(int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n) return '#';
        return map[r][c];
    }

    // 辅助函数：计算撞到挡板后的新方向
    static int getNewDir(int d, char deflector) {
        if (deflector == '/') return d ^ 1;     // 异或 1 就能实现 / 挡板的转向逻辑
        else return 3 - d;                      // 3 减去原方向实现 \ 挡板的转向逻辑
    }

    // 辅助函数：将 (行, 列, 方向) 压缩成一个唯一的一维整数，作为 memo 数组的索引
    static int getState(int r, int c, int d) {
        return (r * n + c) * 4 + d;
    }

    // ================= 步骤 4：核心滚动仿真器 =================
    // 从 start_r, start_c 开始，朝着 start_d 方向滚动，返回最终停下的坐标
    // return的就是停止位置的Point行列
    static Point simulate(int start_r, int start_c, int start_d) {
        int r = start_r, c = start_c, d = start_d;
        // path 记录本次滚动沿途经历的所有状态，为了能在得出结果后，一并写入 memo 缓存
        List<Integer> path = new ArrayList<>();

        while (true) {
            int state = getState(r, c, d);

            // 4.1 检查缓存与死循环？？？
            if (memo[state] != null) {
                // 如果发现这个状态标记为 VISITING，说明我们正在这条路上，现在又绕回来了，绝对是死循环
                Point result = (memo[state] == VISITING) ? LOOP : memo[state];
                // 把这个结果同步给沿途经历的所有点
                for (int s : path) memo[s] = result;
                return result;
            }

            // 标记当前状态为正在访问，并加入路径记录
            memo[state] = VISITING;
            path.add(state);

            // 4.2 预测前方的下一个格子
            int nr = r + dr[d];
            int nc = c + dc[d];
            char nextCell = getCell(nr, nc);
            // (1)遇到障碍物：停在当前格子，本次滚动结束
            if (nextCell == '#') {
                Point result = new Point(r, c);
                for (int s : path) memo[s] = result;
                return result;
            }
            // (2)遇到挡板：处理转向及“挡板变实墙”的特殊规则
            if (nextCell == '/' || nextCell == '\\') {
                int nd = getNewDir(d, nextCell);// 计算出折射后的新方向
                // 预测出挡板后的下一个坐标点
                int nnr = nr + dr[nd];
                int nnc = nc + dc[nd];
                char nnCell = getCell(nnr, nnc);

                // 根据规则判断：如果预测挡板接下来的下一格是障碍物或另一个挡板，则当前挡板也视作实体墙壁
                if (nnCell == '#' || nnCell == '/' || nnCell == '\\') {
                    Point result = new Point(r, c);
                    for (int s : path) memo[s] = result;
                    return result;
                }

                // 顺利通过挡板，更新位置及方向
                r = nr;
                c = nc;
                d = nd;
            } else {
            // (3)遇到普通平地：继续朝原方向前进
                r = nr;
                c = nc; // 正常空格前进，方向不发生变化
            }

            // 终点检测：只要路过就算成功
            if (r == er && c == ec) {
                for (int s : path) memo[s] = END;
                return END;
            }
        }
    }
}
