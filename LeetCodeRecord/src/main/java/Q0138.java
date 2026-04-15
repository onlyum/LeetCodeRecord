import java.util.Map;
import java.util.HashMap;
/*
138.随即链表的复制
 */
//hash存储（旧节点， 新节点）
//public class Q0138 {
//    public static void main(String[] args){
//        int[] in = {1,2,3,4,5};
//        ListNode.printList(copyRandomList(ListNode.createLinkedList(in)));
//    }
//    public static ListNode copyRandomList(ListNode head) {
//        Map<ListNode, ListNode> map = new HashMap<ListNode, ListNode>();
//        for(ListNode p = head; p != null; p = p.next){
//            map.put(p, new ListNode(p.val));
//        }
//        for(ListNode p = head; p != null; p = p.next){
//            map.get(p).next = map.get(p.next);
//            map.get(p).random = map.get(p.random);
//        }
//        return map.get(head);
//    }
//}

public class Q0138 {
    public static void main(String[] args){
        int[] in = {1,2,3,4,5};
        ListNode.printList(copyRandomList(ListNode.createLinkedList(in)));
    }
    public static ListNode copyRandomList(ListNode head) {
        Map<ListNode, ListNode> map = new HashMap<>();//map存储，新旧节点完全一一对应
        ListNode ptr = head;
        //初始化map,顺便节点数值拷贝
        while(ptr!=null){
            map.put(ptr, new ListNode(ptr.val));
            ptr = ptr.next;
        }
        //节点指针拷贝
        ptr = head;
        while (ptr!=null){
            map.get(ptr).next = map.get(ptr.next);
            map.get(ptr).random = map.get(ptr.random);
            ptr = ptr.next;
        }

        return map.get(head);
    }
}