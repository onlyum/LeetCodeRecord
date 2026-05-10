package 倍增算法;
import java.util.*;

public class 树上K步跳跃求节点的第K个祖先 {
    static final int MAX_NODE = 100;
    static final int MAX_LOG = 17;
    static int[][] fa = new int[MAX_NODE][MAX_LOG];
    static int[] depth = new int[MAX_NODE];
    static List<Integer>[] tree = new ArrayList[MAX_NODE];

    static void init(int n) {
        for (int i = 0; i <= n; i++) tree[i] = new ArrayList<>();
    }

    // 只给出了 u 和它的父节点 parent 的边，简化了输入
    static void buildTree(int u, int p) {
        fa[u][0] = p; // 预处理 2^0 步
        tree[p].add(u);
    }

    // 计算深度并处理倍增数组
    static void build(int n, int root) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        depth[root] = 1;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int j = 1; j < MAX_LOG; j++) {
                // 如果 fa[curr][j-1] 为 0，说明跳出去了，不再跳
                fa[curr][j] = fa[fa[curr][j - 1]][j - 1];
            }
            for (int child : tree[curr]) {
                depth[child] = depth[curr] + 1;
                q.offer(child);
            }
        }
    }

    // 查询节点 u 的第 k 个祖先
    static int getKthAncestor(int u, int k) {
        // 如果要跳的步数超出了当前的深度，直接返回 0 (表示不存在)
        if (depth[u] <= k) return 0;

        // 将步数 K 二进制拆分
        for (int j = 0; j < MAX_LOG; j++) {
            // 检查 K 的第 j 位是否为 1
            if ((k & (1 << j)) != 0) {
                u = fa[u][j]; // 向上跳 2^j 步
            }
        }
        return u;
    }

    public static void main(String[] args) {
        int n = 7;
        init(n);
        /*
          这是一条链： 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
          这种极端情况最能体现倍增 $O(\log K)$ 跳跃的优势。
        */
        int root = 1;
        buildTree(2, 1);
        buildTree(3, 2);
        buildTree(4, 3);
        buildTree(5, 4);
        buildTree(6, 5);
        buildTree(7, 6);

        build(n, root);

        System.out.println("--- K 步跳跃查询测试 ---");
        System.out.println("节点 7 的第 2 个祖先 (跳 2 步): " + getKthAncestor(7, 2)); // 预期输出: 5
        System.out.println("节点 7 的第 5 个祖先 (跳 5 步): " + getKthAncestor(7, 5)); // 预期输出: 2
        System.out.println("节点 5 的第 4 个祖先 (跳 4 步): " + getKthAncestor(5, 4)); // 预期输出: 1
        System.out.println("节点 3 的第 10 个祖先 (越界): " + getKthAncestor(3, 10)); // 预期输出: 0
    }
}
