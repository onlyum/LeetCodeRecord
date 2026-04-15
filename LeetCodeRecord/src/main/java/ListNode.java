public class ListNode {
    int val;
    ListNode next;
    ListNode random;
    ListNode(int x) {
        val = x;
        next = null;
        random = null;
    }
    ListNode(int x, ListNode nxt){
        val = x;
        next = nxt;
        random = null;
    }
    ListNode(int x, ListNode nxt, ListNode ra){
        val = x;
        next = nxt;
        random = ra;
    }

    public static ListNode createLinkedList(int[] nums) {
        ListNode dummy = new ListNode(0); // 虚拟头节点
        ListNode curr = dummy;
        for (int num : nums) {
            curr.next = new ListNode(num);
            curr = curr.next;
        }
        return dummy.next; // 返回真正的头节点
    }
    public static ListNode createLinkedList(int[][] matrix) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int[] row : matrix) {
            for (int num : row) {
                curr.next = new ListNode(num);
                curr = curr.next;
            }
        }
        return dummy.next;
    }
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println(); // 换行
    }
}

