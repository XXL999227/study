// 深度优先遍历
public class DFS {
    // 节点的基本结构
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 深度优先遍历
    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 业务代码写在这里，就是前序遍历（根左右）
        dfs(root.left);
        // 业务代码写在这里，就是中序遍历（左根右）
        dfs(root.right);
        // 业务代码写在这里，就是后序遍历（左右根）
    }
}