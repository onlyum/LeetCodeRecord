import java.util.Map;
import java.util.HashMap;

public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        DLinkedNode(){}
        DLinkedNode(int _key, int _value){
            key = _key;
            value = _value;
        }
    }

    private Map<Integer, DLinkedNode> map = new HashMap<Integer, DLinkedNode>();
    private DLinkedNode head, tail;
    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = map.get(key);
        if (node == null){
            return -1;
        }
        moveToHead(node);
        return map.get(key).value;
    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node != null){
            node.value = value;
            moveToHead(node);
        }else{
            DLinkedNode newNode = new DLinkedNode(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            size++;
            //大于负载的删去
            if(size > capacity){
                DLinkedNode beforeTail = removeTail();
                map.remove(beforeTail.key);
                size--;
            }
        }
    }

    private void moveToHead(DLinkedNode node){  //已有节点移动到到链表头
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node){   //新节点添加到链表头
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node){  //删除已有节点
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private DLinkedNode removeTail(){   //删除尾节点并返回key值，以便在HashMap中删除
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }

}
