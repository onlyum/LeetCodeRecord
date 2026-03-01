import java.util.Map;
import java.util.HashMap;

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<Node, Node>();
        for(Node p = head; p != null; p = p.next){
            map.put(p, new Node(p.val));
        }
        for(Node p = head; p != null; p = p.next){
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
        }
        return map.get(head);
    }
}
