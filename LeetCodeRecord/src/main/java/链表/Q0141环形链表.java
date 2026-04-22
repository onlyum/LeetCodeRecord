package 链表;

/*
141.环形链表
 */
public class Q0141环形链表 {
    public boolean hasCycle(ListNode head) {
        //快慢指针,有环则不为NULL
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
}
