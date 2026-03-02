import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[][] input = {{1, 4, 5}, {1, 3, 4}, {2, 6}};

        // 初始化 ListNode[] 数组
        ListNode[] lists = new ListNode[input.length];
        for (int i = 0; i < input.length; i++) {
            lists[i] = create(input[i]);
        }

        Solution solution = new Solution();
        ListNode res = solution.mergeKLists(lists);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    // 经典的数组转链表方法
    private static ListNode create(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int x : nums) {
            cur.next = new ListNode(x);
            cur = cur.next;
        }
        return dummy.next;
    }
}
