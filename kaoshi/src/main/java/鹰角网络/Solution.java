package YJWL;

import java.util.*;



public class Solution {
    public static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param a ListNode类
     * @param b ListNode类
     * @return ListNode类
     */
    public ListNode newList (ListNode a, ListNode b) {
        if(a==null||b==null){
            return a==null?b:a;
        }
        ListNode dummy = new ListNode(-1);
        ListNode pa = new ListNode(-1);
        pa.next = a;
        ListNode pb = new ListNode(-1);
        ListNode reb = reverse(b);
        if (reb == null) {
            System.out.println(-1);
        }
        // while (reb != null) {
        //     System.out.print(reb.val);
        //     reb = reb.next;
        // }
        ListNode t = dummy;
        while(a!=null && reb!=null){
            t.next = new ListNode(a.val);

            System.out.println(a.val);
            t = t.next;
            a = a.next;
            t.next = new ListNode(reb.val);
            System.out.println(reb.val);
            t = t.next;
            reb = reb.next;
        }
        if(a==null){
            while(reb!=null){
                t.next = new ListNode(reb.val);
                t = t.next;
                reb = reb.next;
            }
        }else if(b==null){
            while(a!=null){
                t.next = new ListNode(a.val);
                t = t.next;
                a = a.next;
            }
        }
        return dummy.next;
    }
    public ListNode reverse(ListNode b) {
        if (b == null || b.next == null) {
            return b;
        }
        ListNode dummy = b;
        ListNode cur = dummy.next;
        dummy.next = null;
        ListNode nt = cur.next;
        while (cur != null) {

            // System.out.println(cur.val);
            cur.next = dummy;
            dummy = cur;
            cur = nt;
            if (cur == null) {
                break;
            }
            nt = cur.next;
        }
        return dummy;
    }
}