package 网易互娱.D20260306.T2.答案;
import java.util.*;
/*
【网易互娱】2026-3-6-第二题-蛋仔滚动
题目描述
给定一个 m 行 n 列的地图，蛋仔从起点 (sr, sc) 出发，需要到达终点 (er, ec)。
蛋仔每次可以选择向上、下、左、右四个方向之一开始滚动。一旦开始滚动，在遇到阻挡前无法主动停下。为了防止蛋仔滚出地图，地图外侧视为额外包裹了一圈障碍物。
地图中有三类特殊格子：# 为障碍物，蛋仔不能进入；/ 为斜挡板；\ 为反斜挡板。题目保证起点和终点所在格子均不是特殊格子，给出的特殊格子坐标互不重复。
挡板本身占据一个格子，蛋仔进入挡板格后会立即根据挡板类型改变方向并继续滚动。/ 型挡板：从左侧进入改为向上，从右侧进入改为向下，从上方进入改为向左，从下方进入改为向右。\ 型挡板：从左侧进入改为向下，从右侧进入改为向上，从上方进入改为向右，从下方进入改为向左。
补充说明：一次"滚动"定义为从某个静止位置出发，选择一个方向，直到再次停下为止，即使途中多次改变方向也只记为 1 次滚动。若蛋仔即将进入某个挡板格，但按挡板规则计算出的出口方向对应的下一格是障碍物或另一个挡板，则该挡板视为不可进入，蛋仔停在进入挡板前的那一格。只要蛋仔在某次滚动过程中经过终点格，就视为成功到达，不需要恰好停在终点。若蛋仔在挡板间无限反弹且经过了终点，仍视为可以到达。
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
1 5

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

public class T20 {
    // --- 全局变量区 ---
    // m, n: 地图的实际行数和列数
    // sr, sc: 蛋仔的起点坐标
    // er, ec: 皇冠(终点)的坐标
    static int m, n, sr, sc, er, ec;

    // 【核心优化1：稀疏图存储】
    // 题目中绝大多数格子是平地，没必要开一个很大的二维数组。
    // 我们用 HashMap 只存储有特殊组件（墙、挡板）的格子。
    // Key: r * 1000 + c (将二维坐标压缩成一维整数，1000大于题目最大边界100，保证唯一性)
    // Value: 具体的组件字符 ('#', '/', '\')
    static Map<Integer, Character> grid = new HashMap<>();

    // 【核心定义：方向映射】
    // 规定方向数字：0=上, 1=下, 2=左, 3=右
    // 配合 dr 和 dc 数组，例如 d=0 时，行数-1(向上)，列数+0
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 【核心优化2：挡板反射硬编码】
    // slash_exit 对应 '/' 挡板。例如从下方撞入(方向0向上)，会被弹向右方(方向3)。所以 slash_exit[0] = 3。
    static int[] slash_exit = {3, 2, 1, 0};
    // bslash_exit 对应 '\' 挡板。例如从下方撞入(方向0向上)，会被弹向左方(方向2)。所以 bslash_exit[0] = 2。
    static int[] bslash_exit = {2, 3, 0, 1};

    // --- 辅助方法：获取某个格子的状态 ---
    static char getCell(int r, int c) {
        // 【逻辑整合】将“越出地图边界”统一视为“撞到了一堵无法逾越的墙 '#'”
        // 这样在后续逻辑中，就不需要单独写 if 来判断边界了
        if (r < 1 || r > m || c < 1 || c > n) return '#';
        // 如果格子里没有存特殊组件，说明是普通平地 '.'
        return grid.getOrDefault(r * 1000 + c, '.');
    }

    // --- 数据结构：记录单次滚动的最终结果 ---
    static class RollResult {
        int r, c;           // 最终停下来的坐标。如果是 -1 表示陷入了死循环
        boolean reached;    // 滚动过程中是否“路过”了终点

        public RollResult(int r, int c, boolean reached) {
            this.r = r;
            this.c = c;
            this.reached = reached;
        }
    }

    // --- 核心方法：模拟单次“发射” ---
    // 从坐标 (r, c) 以初始方向 d 开始滚动，直到撞墙或停下
    static RollResult rollSim(int r, int c, int d) {
        boolean reached = false;

        // 【核心优化3：单局死循环检测】
        // 记录蛋仔在本次滚动中，是否以相同的“方向”经过了相同的“位置”
        Set<Integer> seen = new HashSet<>();

        // 只要没撞停，就一直滚 (while(true))
        while (true) {
            // 【状态压缩】将 (行, 列, 方向) 压成一个数字
            // 因为行列最大是 100，所以 r * 101 + c 可以唯一标识位置；再乘以 4 加上方向 d，唯一标识一个动态状态
            int state = (r * 101 + c) * 4 + d;

            // 如果这个状态出现过，说明蛋仔在几个挡板之间无限来回弹了，直接宣告这条路径作废 (返回 -1)
            if (seen.contains(state)) return new RollResult(-1, -1, reached);
            seen.add(state); // 记录当前状态

            // 预测下一步要迈入的格子坐标 (nr, nc)
            int nr = r + dr[d];
            int nc = c + dc[d];
            char cell = getCell(nr, nc);

            // 状况 A：前方是墙壁或边界
            if (cell == '#') {
                // 刹车，停在撞墙前的那一格 (r, c)，并返回结果
                return new RollResult(r, c, reached);
            }

            // 状况 B：前方是挡板
            if (cell == '/' || cell == '\\') {
                // 1. 查表得出如果进入挡板，出来时的新方向 (nd)
                int nd = (cell == '/') ? slash_exit[d] : bslash_exit[d];

                // 2. 【关键规则校验】：预测挡板弹射出去的下一个格子 (exitCell)
                char exitCell = getCell(nr + dr[nd], nc + dc[nd]);

                // 3. 如果弹射出去立马是墙或另一个挡板，题目规定“该挡板不可进入”
                if (exitCell == '#' || exitCell == '/' || exitCell == '\\') {
                    // 所以蛋仔没法进入 (nr, nc)，只能委屈地停在挡板前一格 (r, c)
                    return new RollResult(r, c, reached);
                }

                // 4. 如果出口合法，蛋仔正式进入挡板，坐标更新为挡板位置，方向更新为弹射方向
                r = nr;
                c = nc;
                d = nd;
            } else {
                // 状况 C：前方是普通平地
                // 毫无阻碍，直接把坐标往前推一格
                r = nr;
                c = nc;
            }

            // 【终点检测】：走完这一步后，看看是不是踩到了终点。
            // 注意：踩到终点不代表停下！只要没撞墙，还得继续往前滚，只做标记 reached = true
            if (r == er && c == ec) reached = true;
        }
    }

    // --- 核心方法：广度优先搜索 (找最短操作次数) ---
    static int bfs() {
        // 如果起点就是终点，操作次数为 0
        if (sr == er && sc == ec) return 0;

        final int INF = (int) 1e9; // 弄一个足够大的数代表“无穷大/无法到达”

        // dist[i][j] 记录：从起点到达停留点 (i, j) 最少需要“发射”几次
        int[][] dist = new int[m + 1][n + 1];
        for (int[] row : dist) {
            Arrays.fill(row, INF); // 初始全设为没去过
        }
        dist[sr][sc] = 0; // 起点到自己需要 0 次

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc}); // 把起点放入待探索队列

        int ans = INF; // 全局最优解(最少步数)，初始为无穷大

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int r = current[0];
            int c = current[1];
            int dCur = dist[r][c]; // 获取到达当前探索起点的已有步数

            // 【核心优化4：剪枝】
            // 如果我们目前已经走了 dCur 步，而接下来发射一次最少也要 dCur + 1 步。
            // 如果 dCur + 1 已经大于等于目前找到的最佳答案 ans，那说明这条探索分支毫无前途，直接跳过。
            if (dCur + 1 >= ans) continue;

            // 站在当前停留点，尝试向上、下、左、右 4 个方向“发射”
            for (int d = 0; d < 4; d++) {
                // 调用核心模拟方法，得到发射一轮后的结局
                RollResult res = rollSim(r, c, d);

                // 1. 如果这趟滚动途经了终点
                if (res.reached) {
                    // 更新全局最优解。只取更小的那个。
                    ans = Math.min(ans, dCur + 1);
                }

                // 2. 处理没遇到终点的情况：判断落脚点是否合法
                // res.r == -1 说明死循环了，不要；
                // res.r == r && res.c == c 说明朝着一堵墙发射，直接原地没动，也不要。
                if (res.r == -1 || (res.r == r && res.c == c)) continue;

                // 3. 这是一个合法的、移动过的落脚点。
                // 只有当我们通过这趟发射，能以更少的总步数到达 (res.r, res.c) 时，才有价值记录并作为下一个发射基点
                if (dist[res.r][res.c] > dCur + 1) {
                    dist[res.r][res.c] = dCur + 1;  // 更新最短步数
                    q.offer(new int[]{res.r, res.c}); // 放入队列，留待以后再从这里往外射
                }
            }
        }

        // 如果最后答案还是 INF，说明怎么滚都碰不到终点，返回 -1
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 1. 读取基础行列
        int N = in.nextInt(), M = in.nextInt();

        // 2. 读取起点和终点
        int Sr = in.nextInt(), Sc = in.nextInt();
        int Er = in.nextInt(), Ec = in.nextInt();

        // 3. 读取障碍物和挡板
        int K = in.nextInt();
        for (int i = 0; i < K; i++) {
            int r = in.nextInt();
            int c = in.nextInt();
            // 读取字符（因为 Scanner 没有 nextChar，先读字符串再取第 0 个字符）
            char ch = in.next().charAt(0);
            grid.put(r * 1000 + c, ch); // 存入稀疏图
        }

        // 4. 把 Scanner 读到的数据，对接到算法所需的全局变量上
        m = N;
        n = M;
        sr = Sr;
        sc = Sc;
        er = Er;
        ec = Ec;

        // 5. 启动核心算法并输出
        System.out.println(bfs());

        in.close(); // 养成好习惯
    }
}