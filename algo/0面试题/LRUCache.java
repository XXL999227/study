import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/lru-cache-lcci/description/
// 实现一个LRU缓存，设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。
public class LRUCache {
    // 通常可以使用双向链表+HashMap实现
    private Map<Integer, Node> map = new HashMap<>();
    // 虚拟头结点
    private Node head;
    // 虚拟尾节点
    private Node tail;
    // 最大容量
    private int capacity;

    static class Node {
        // 但是双向链表中需要维护key和value，方便从map中根据key删除元素
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    // 构造函数
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    // get方法
    public int get(int key) {
        // 如果不存在，返回-1
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            // 如果存在，需要将此元素移动到链表头
            moveToHead(node);
            return node.value;
        }
    }

    // 存入时，先判断是否存在，存在则覆盖原来的值，并且移动到链表头
    // 不存在，先判断容量是否足够，足够则存入并移动到链表头，不足则先移除最后一个元素，再存入
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            if (capacity > map.size()) {
                Node curNode = new Node(key, value);
                map.put(key, curNode);
                addToHead(curNode);
            } else {
                removeLastNode();
                Node curNode = new Node(key, value);
                map.put(key, curNode);
                addToHead(curNode);
            }
        }
    }

    private void removeLastNode() {
        Node readlLast = tail.prev;
        readlLast.prev.next = tail;
        tail.prev = readlLast.prev;

        // 方便这里删除map中的元素
        map.remove(readlLast.key);
    }

    private void addToHead(Node curNode) {
        Node realHead = head.next;
        head.next = curNode;
        curNode.prev = head;
        curNode.next = realHead;
        realHead.prev = curNode;
    }

    private void moveToHead(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;

        Node realHead = head.next;
        head.next = node;
        node.prev = head;
        node.next = realHead;
        realHead.prev = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        System.out.println(cache.get(1));
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        System.out.println(cache.get(2));
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        System.out.println(cache.get(1));
        cache.get(3);       // 返回  3
        System.out.println(cache.get(3));
        cache.get(4);       // 返回  4
        System.out.println(cache.get(4));
    }
}
