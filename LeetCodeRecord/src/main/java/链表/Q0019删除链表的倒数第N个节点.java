package 链表;/*
19. 删除链表的倒数第 N 个结点
 */

//间隔k个节点进行遍历
public class Q0019删除链表的倒数第N个节点 {
    public static void main(String[] args){
        int[] in = {1,2,3,4,5};
        ListNode.printList(removeNthFromEnd(ListNode.createLinkedList(in), 2));
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1,head);
        ListNode slow = dummy,fast = head;//从虚拟节点开始，防止空指针问题
        //从head开始走n步，即跟slow开始间隔n个节点
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        //停止在目标节点前一步
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
