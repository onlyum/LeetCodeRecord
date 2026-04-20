package 链表;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
148.排序链表
 */

//优先队列自动排序
public class Q0148 {
    public static void main(String[] args){
        int[] in = {4,2,1,3};
        ListNode.printList(sortList(ListNode.createLinkedList(in)));
    }
    public static ListNode sortList(ListNode head) {
        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare(ListNode node1, ListNode node2){
                return node1.val - node2.val;
            }
        });

        for(ListNode p=head;p!=null;p=p.next){
            pq.offer(p);
        }

        ListNode cur = pq.poll();
        ListNode dummy = new ListNode(-1, cur);
        while(!pq.isEmpty()){
            cur.next = new ListNode(pq.poll().val);
            cur = cur.next;
        }
        return dummy.next;
    }
}
