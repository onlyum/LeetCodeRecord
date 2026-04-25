package 二叉树;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    // ==========================================
    // 1. 层序遍历建树 (带 null)
    // ==========================================
    public static TreeNode buildByLevelOrder(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode curr = queue.poll();

            // 处理左子节点
            if (i < arr.length && arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.offer(curr.left);
            }
            i++;

            // 处理右子节点
            if (i < arr.length && arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    // ==========================================
    // 2. 先序遍历建树 (带 null) -> 顺序：根-左-右
    // ==========================================
    public static TreeNode buildByPreOrder(Integer[] arr) {
        // 使用长度为 1 的数组作为游标，避免多线程或多次调用时的静态变量污染
        return preOrderHelper(arr, new int[]{0});
    }

    private static TreeNode preOrderHelper(Integer[] arr, int[] index) {
        // 越界检查
        if (index[0] >= arr.length) return null;

        Integer val = arr[index[0]++];
        if (val == null) {
            return null; // 遇到 null，说明这条分支到底了，返回上一层
        }

        TreeNode root = new TreeNode(val);
        // 按 "左 -> 右" 的顺序递归
        root.left = preOrderHelper(arr, index);
        root.right = preOrderHelper(arr, index);
        return root;
    }

    // ==========================================
    // 3. 后序遍历建树 (带 null) -> 顺序：左-右-根
    // ==========================================
    public static TreeNode buildByPostOrder(Integer[] arr) {
        // 核心技巧：后序数组倒着读，顺序就是 "根-右-左"，逻辑和先序极其相似！
        // 因此游标从数组末尾开始往前扫描
        return postOrderHelper(arr, new int[]{arr.length - 1});
    }

    private static TreeNode postOrderHelper(Integer[] arr, int[] index) {
        // 越界检查
        if (index[0] < 0) return null;

        Integer val = arr[index[0]--];
        if (val == null) {
            return null;
        }

        TreeNode root = new TreeNode(val);
        // ⚠️ 注意：倒着读的顺序是 "根 -> 右 -> 左"
        // 所以必须先递归建立右子树，再去建立左子树，不能搞反！
        root.right = postOrderHelper(arr, index);
        root.left = postOrderHelper(arr, index);
        return root;
    }

    // ==========================================
    // 4. ⚠️ 备注：为什么没有中序遍历建树？
    // ==========================================
    /*
     * 即使中序遍历序列中带有 null 标记，依然无法唯一确定一棵二叉树。
     * * 【致命歧义反例】：
     * 假设有两棵形状完全不同的二叉树：
     * 树 A：根节点是 2，左孩子是 1，没有右孩子。
     * 树 B：根节点是 1，没有左孩子，右孩子是 2。
     * * 对它们进行带 null 的中序遍历 (左 -> 根 -> 右)：
     * 树 A 的序列为：[null, 1, null, 2, null]
     * 树 B 的序列为：[null, 1, null, 2, null]
     * * 结论：两棵不同的树得出了完全相同的序列。因为根节点被夹在中间，
     * 导致我们无法分辨谁才是真正的根。因此，中序遍历永远不能用于反向唯一建树。
     */

    // ==========================================
    // 5. 直观格式化打印二叉树 (控制台调试专用)
    // ==========================================
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("空树 (null)");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        System.out.println("二叉树水平分层结构:");
        while (!queue.isEmpty()) {
            // 获取当前队列中的节点数，这就是【当前层】的节点总数
            int levelSize = queue.size();

            System.out.print("第 " + level + " 层: ");

            // 将当前层的所有节点出队打印，并把它们的子节点（下一层）入队
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();

                // 打印当前节点的值
                System.out.print(curr.val + " ");

                // 左右孩子入队，准备下一行的打印
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }

            // 当前层的所有节点打印完毕，换行
            System.out.println();
            level++;
        }
    }
}