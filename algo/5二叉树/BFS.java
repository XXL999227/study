import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 广度优先遍历
public class BFS {
    // 节点的基本结构
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 广度优先遍历
    public void bfs(TreeNode root) {
        // 这是最简单的bfs写法，缺点是没有记录层级
        if (root == null) {
            return;
        }
        // 1. 创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 2. 将根节点放入队列，offer()方法是队列的入队操作，如果队列满了，返回false
        queue.offer(root);
        // 3. 循环遍历队列
        while (!queue.isEmpty()) {
            // 4. 弹出队列头部元素，poll()方法是队列的出队操作，如果队列为空，返回null
            TreeNode node = queue.poll();
            // 5. 业务代码写在这里
            System.out.println(node.val);
            // 6. 将左右子节点放入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public void bfs2(TreeNode root) {
        // 这是bfs的另一种写法，可以记录层级
        if (root == null) {
            return;
        }
        // 1. 创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 2. 将根节点放入队列
        queue.offer(root);
        // 3. 初始化层级, 从1开始
        int depth = 1;
        // 4. 循环遍历队列
        while (!queue.isEmpty()) {
            // 5. 记录当前层级的节点个数
            int size = queue.size();
            // 6. 遍历当前层级的节点，这里要用size，因为queue.size()是变化的
            for (int i = 0; i < size; i++) {
                // 7. 弹出队列头部元素
                TreeNode node = queue.poll();
                // 8. 业务代码写在这里
                System.out.println("当前层级：" + depth + "，当前节点：" + node.val);
                // 9. 将左右子节点放入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 10. 层级加1
            depth++;
        }
    }

    // 将bfs2中的队列换成栈，就是dfs
    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 1. 创建一个栈
        Stack<TreeNode> stack = new Stack<>();
        // 2. 将根节点放入栈，push()方法是栈的入栈操作
        stack.push(root);
        // 3. 初始化层级, 从1开始
        int depth = 1;
        // 4. 循环遍历栈
        while (!stack.isEmpty()) {
            // 5. 记录当前层级的节点个数
            int size = stack.size();
            // 6. 遍历当前层级的节点，这里要用size，因为stack.size()是变化的
            for (int i = 0; i < size; i++) {
                // 7. 弹出栈顶元素，pop()方法会返回栈顶元素，并且将其从栈中删除，peek()方法只会返回栈顶元素，不会删除
                TreeNode node = stack.pop();
                // 8. 业务代码写在这里
                System.out.println("当前层级：" + depth + "，当前节点：" + node.val);
                // 9. 将右左子节点放入栈
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            // 10. 层级加1
            depth++;
        }
    }

    public static void main(String[] args) {
        // 构造一棵二叉树
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftLeft = new TreeNode(4);
        TreeNode leftRight = new TreeNode(5);
        TreeNode rightLeft = new TreeNode(6);
        TreeNode rightRight = new TreeNode(7);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;
        //    树的结构如下：
        //         1
        //        / \
        //       2   3
        //      / \ / \
        //     4  5 6  7
        BFS bfs = new BFS();
        System.out.println("bfs方法一，没有记录层级");
        bfs.bfs(root);
        System.out.println("bfs方法二，记录层级");
        bfs.bfs2(root);
        System.out.println("dfs，将bfs2中的队列换成栈，就是dfs");
        bfs.dfs(root);
    }
}
