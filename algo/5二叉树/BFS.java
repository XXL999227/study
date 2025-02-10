import java.util.LinkedList;
import java.util.Queue;

// 广度优先遍历
public class BFS {
    // 节点的基本结构
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
}
