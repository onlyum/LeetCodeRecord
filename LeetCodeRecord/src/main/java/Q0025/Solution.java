package Q0025;
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        while(head!=null){
            ListNode tail = pre;
            for(int i = 0; i < k; i++){
                tail = tail.next;
                if(tail==null){
                    return dummy.next;
                }
            }
            ListNode after = tail.next;
            ListNode[] reverse = reverseK(head, tail);
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = after;
            pre = tail;
            head = after;
        }
        return dummy.next;
    }
    public ListNode[] reverseK(ListNode head, ListNode tail){
        ListNode after = tail.next;
        ListNode cur = head;
        while(after != tail){
            ListNode prev = cur.next;
            cur.next = after;
            after = cur;
            cur = prev;
        }
        return new ListNode[]{tail,head};
    }
}
