/*
23.合并k个升序链表
 */

//归并排序思想
public class Q0023合并K个升序链表 {
    public static void main(String[] args) {
        int[][] input = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        ListNode[] list = new ListNode[input.length];
        for(int i =0;i<input.length;i++){
            list[i] = ListNode.createLinkedList(input[i]);
        }
        ListNode.printList(mergeKLists(list));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length-1);
    }

    //返回列表里(l,r)的所有链表合并的结果
    public static ListNode merge(ListNode[] lists, int l , int r){
        if(l==r)    return lists[l];
        if(l > r)   return null;
        int mid = (l+r)/2;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid+1, r));
    }

    //返回两个链表合并的结果
    public static ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1  = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1==null?l2:l1;
        return dummy.next;
    }
}
