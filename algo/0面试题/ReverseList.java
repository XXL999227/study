import java.util.Stack;

public class ReverseList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 翻转链表 1 -> 2 -> 3 -> 4 -> 5
    // 用双指针的思路，创建两个指针，一个指针指向当前节点，一个指针指向当前节点的前一个节点
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next; // 保存当前节点的下一个节点
            cur.next = pre; // 当前节点的next指向前一个节点
            pre = cur; // pre指向当前节点
            cur = temp; // cur指向下一个节点
        }
        return pre;
    }

    // 方法二，用栈，思路简单，但是需要空间复杂度为O(n)
    // 原链表：
    //     head → 1 → 2 → 3 → 4 → 5 → null
    //
    // 压栈后（栈顶→栈底）：
    //     | 5 |   ← 栈顶
    //     | 4 |
    //     | 3 |
    //     | 2 |
    //     | 1 |   ← 栈底
    //
    // 重建链表步骤：
    // 1. 弹出 5 → newHead = 5, currentReversed = 5
    // 2. 弹出 4 → 5 → 4, currentReversed 移至 4
    // 3. 弹出 3 → 5 → 4 → 3, currentReversed 移至 3
    // 4. ...直至弹出 1 → 5 → 4 → 3 → 2 → 1 → null
    //
    // 结果：
    //     newHead → 5 → 4 → 3 → 2 → 1 → null
    public static ListNode reverseList2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode newHead = stack.pop();
        ListNode currentReversed = newHead;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            node.next = null; // 关键：断开原节点的next指针，避免循环引用
            currentReversed.next = node; // 将栈中的节点连接起来，5 -> 4 -> 3 -> 2 -> 1
            currentReversed = node; // cur指向当前节点
        }
        return newHead;
    }

    // 方法三，用栈，思路再优化一下，入栈时就去掉节点的指针
    public static ListNode reverseList3(ListNode head) {
        if (head == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        // 入栈时去掉节点的指针
        while (head != null) {
            ListNode temp = head.next;
            head.next = null;
            stack.push(head);
            head = temp;
        }
        ListNode newHead = stack.pop();
        ListNode cur = newHead;
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode res = reverseList3(head);
        // 打印链表
        System.out.println("链表反转结果：");
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }
}
