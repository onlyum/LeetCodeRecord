package 链表;/*
25. K 个一组翻转链表
 */

//间隔k-1个节点进行遍历
public class Q0025 {
    public static void main(String[] args){
        int[] in = {1,2,3,4,5};
        ListNode.printList(reverseKGroup(ListNode.createLinkedList(in), 3));
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        //pre,after存当前分组的前一节点和后一节点
        //head,tail存当前分组的头节点和尾节点
        ListNode pre = dummy, tail, after;
        while(head!=null){
            //tail基于pre前进k次，刚好k个元素
            tail = pre;
            for(int i=0;i<k;i++){
                tail = tail.next;
                if(tail==null)  return dummy.next;
            }

            after = tail.next;
            ListNode[] rv = reverseK(head, tail);//反转当前分组
            head = rv[0];
            tail = rv[1];
            //连接新头尾
            pre.next = head;
            tail.next = after;
            //更新
            pre = tail;
            head = after;
        }
        return dummy.next;
    }

    //三指针链表反转，中止条件从cur==null改为cur==end(其中end=tail.next)
    public static ListNode[] reverseK(ListNode head, ListNode tail){
        ListNode end = tail.next;

        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur!=end){
            temp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = temp;
        }
        return new ListNode[]{tail, head};
    }
}
