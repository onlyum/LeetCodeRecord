public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for(int i = 0; i < lists.length; i++){
            res = mergeTwoLists(lists[i], res);
        }
        return res;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null||l2 == null) {
            return l1==null? l2:l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy, aPtr = l1, bPtr = l2;
        while(aPtr != null && bPtr != null) {
            if(aPtr.val <= bPtr.val){
                cur.next = aPtr;
                aPtr = aPtr.next;
            }else{
                cur.next = bPtr;
                bPtr = bPtr.next;
            }
            cur = cur.next;
        }
        cur.next = (aPtr==null?bPtr:aPtr);
        return dummy.next;
    }
}