public class Q0002 {
    public static void main(String[] args) {
        ListNode l1 = ListNode.createLinkedList(new int[]{9, 9, 9, 9, 9, 9, 9});
        ListNode l2 = ListNode.createLinkedList(new int[]{9, 9, 9, 9});
        ListNode.printList(addTwoNumbers(l1,l2));
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy=new ListNode(0);
        ListNode cur = dummy;
        boolean jinWei = false;
        while(l1!=null||l2!=null)
        {
            if(l1==null) l1=new ListNode(0);
            if(l2==null) l2=new ListNode(0);
            int curVal=0;//当前和
            if(jinWei)
                curVal =l1.val+l2.val+1;
            else
            {
                curVal =l1.val+l2.val;
            }
            if(curVal>=10)
            {
                curVal-=10;
                jinWei = true;
            }else{
                jinWei = false;
            }
            cur.next=new ListNode(curVal);
            cur=cur.next;
            l1=l1.next;
            l2=l2.next;
        }
        if(jinWei)
        {
            cur.next=new ListNode(1);
            cur=cur.next;
        }
        return dummy.next;
    }
}
