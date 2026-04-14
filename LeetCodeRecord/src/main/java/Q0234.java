import java.util.ArrayList;
import java.util.List;

/*
234.回文链表
 */
public class Q0234 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head!=null){
            list.add(head.val);
            head=head.next;
        }

        int len = list.size();
        for(int i=0;i<=len/2;i++){
            if(list.get(i)!=list.get(len-1-i)){
                return false;
            }
        }
        return true;
    }
}
