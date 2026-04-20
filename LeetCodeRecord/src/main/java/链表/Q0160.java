package 链表;

/*
160. 相交链表
 */
//public class Q160 {
//    public static void main(String[] args) {
//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
//    }
//    public 链表.ListNode getIntersectionNode(链表.ListNode headA, 链表.ListNode headB) {
//        链表.ListNode pA = headA, pB = headB;
//        while(pA!=pB){
//            pA = (pA==null?headB:pA.next);
//            pB = (pB==null?headA:pB.next);
//        }
//        return pA;
//    }
//}
public class Q0160 {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA,pb=headB;
        while(pa!=pb){
            if(pa==null){
                pa = headB;
            }else{
                pa = pa.next;
            }

            if(pb==null){
                pb = headA;
            }else{
                pb = pb.next;
            }
        }
        return pa;
    }
}