package 链表;

/*
24. 两两交换链表中的节点
 */
public class Q0024两两交换链表中的节点 {
    public static void main(String[] args){
        int[] in = {1,2,3,4,5};
        ListNode.printList(swapPairs(ListNode.createLinkedList(in)));
    }
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy,l1 ,l2 ;
        while(cur.next!=null && cur.next.next!=null){
            l1 = cur.next;
            l2 = cur.next.next;
            //注意
            cur.next = l2;
            l1.next = l2.next;
            l2.next = l1;

            cur = cur.next.next;
        }

        return dummy.next;
    }
}
