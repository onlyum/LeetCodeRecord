/*
LRU缓存
主要为两个基类函数：
1、删除当前节点的   removeNode
2、移动节点到表头   moveToHead
和三个数据结构：
1、双向链表
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
2、HashMap存key:DLinkedNode
3、构造函数，记录大小和容量
public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
全局初始化虚拟头尾head,tail

注意get和put默认操作完节点放到表头，put若新加，需判断容量是否足够
 */
import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;

public class Q0146LRU缓存 {
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

    public Q0146LRU缓存(int capacity) {
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


    // --- 打印当前缓存状态的辅助方法 ---
    private void printCache(String operation) {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        DLinkedNode curr = head.next;
        while (curr != tail) {
            sj.add(curr.key + "=" + curr.value);
            curr = curr.next;
        }
        // 格式化输出：操作名称 | 当前缓存状态 (从左到右为从新到旧)
        System.out.printf("%-20s | 缓存状态: %s\n", operation, sj.toString());
    }

    // --- 测试用例 Main 函数 ---
    public static void main(String[] args) {
        System.out.println("操作指令             | 缓存内容 (Head -> Tail)");
        System.out.println("--------------------------------------------------");
        Q0146LRU缓存 cache = new Q0146LRU缓存(2);
        cache.printCache("LRUCache(2)");
        cache.put(1, 1);
        cache.printCache("put(1, 1)");
        cache.put(2, 2);
        cache.printCache("put(2, 2)");
        int r1 = cache.get(1);
        cache.printCache("get(1) -> " + r1);
        cache.put(3, 3);
        cache.printCache("put(3, 3)");
        int r2 = cache.get(2);
        cache.printCache("get(2) -> " + r2);
        cache.put(4, 4);
        cache.printCache("put(4, 4)");
        int r3 = cache.get(1);
        cache.printCache("get(1) -> " + r3);
        int r4 = cache.get(3);
        cache.printCache("get(3) -> " + r4);
        int r5 = cache.get(4);
        cache.printCache("get(4) -> " + r5);
    }
}
