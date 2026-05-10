package 倍增算法;
import java.util.*;
public class 树上最近公共祖先LCA {
    static final int MAX_NODE = 100;
    static final int MAX_LOG = 17; // 2^17 > 10^5，通常根据树的最大规模调整

    // 邻接表表示树
    static List<Integer>[] tree = new ArrayList[MAX_NODE];
    // depth[i] 表示节点 i 的深度
    static int[] depth = new int[MAX_NODE];
    // fa[i][j] 表示节点 i 向上跳 2^j 步到达的祖先节点
    static int[][] fa = new int[MAX_NODE][MAX_LOG];

    // 初始化树
    static void initTree(int n) {
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
    }

    static void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    // DFS 预处理深度和 fa[i][0]
    static void dfs(int curr, int parent, int d) {
        depth[curr] = d;
        fa[curr][0] = parent; // 向上跳 2^0 (即 1) 步就是父节点

        for (int next : tree[curr]) {
            if (next != parent) {
                dfs(next, curr, d + 1);
            }
        }
    }

    // 预处理倍增数组
    static void buildLCA(int n, int root) {
        // 1. 初始化深度和直接父节点
        dfs(root, 0, 1);

        // 2. 状态转移：fa[i][j] = fa[fa[i][j-1]][j-1]
        for (int j = 1; j < MAX_LOG; j++) {
            for (int i = 1; i <= n; i++) {
                fa[i][j] = fa[fa[i][j - 1]][j - 1];
            }
        }
    }

    // 查询 u 和 v 的最近公共祖先
    static int getLCA(int u, int v) {
        // 1. 保证 u 的深度 >= v 的深度，方便后续操作
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }

        // 2. 深度对齐：让 u 向上跳，直到和 v 在同一深度
        for (int j = MAX_LOG - 1; j >= 0; j--) {
            if (depth[fa[u][j]] >= depth[v]) {
                u = fa[u][j];
            }
        }

        // 如果深度对齐后 u 已经是 v，说明 v 是 u 的祖先
        if (u == v) return u;

        // 3. 同时向上跳：找到 LCA 的直接子节点
        for (int j = MAX_LOG - 1; j >= 0; j--) {
            if (fa[u][j] != fa[v][j]) {
                u = fa[u][j];
                v = fa[v][j];
            }
        }

        // 最后 u 和 v 离 LCA 只有一步之遥
        return fa[u][0];
    }

    public static void main(String[] args) {
        int n = 7; // 树有 7 个节点
        initTree(n);
        /*
         构建的树结构如下：
               1
             /   \
            2     3
           / \     \
          4   5     6
             /
            7
        */
        addEdge(1, 2); addEdge(1, 3);
        addEdge(2, 4); addEdge(2, 5);
        addEdge(3, 6); addEdge(5, 7);

        int root = 1;
        buildLCA(n, root);

        System.out.println("--- LCA 查询测试 ---");
        System.out.println("节点 4 和节点 7 的 LCA: " + getLCA(4, 7)); // 预期输出: 2
        System.out.println("节点 5 和节点 6 的 LCA: " + getLCA(5, 6)); // 预期输出: 1
        System.out.println("节点 7 和节点 2 的 LCA: " + getLCA(7, 2)); // 预期输出: 2
    }
}
