public class DiameterofBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        Info info = new Info();
        dfs(root, info);
        // the diameter is the number of edge which is number of nodes - 1
        return info.numberOfNodesInDiameter - 1;
    }

    private int dfs(TreeNode cur, Info info) {
        if (cur == null) {
            return 0;
        }

        int left = dfs(cur.left, info);
        int right = dfs(cur.right, info);

        info.numberOfNodesInDiameter = Math.max(info.numberOfNodesInDiameter, 1 + left + right);
        return 1 + Math.max(left, right);
    }

    private static class Info {
        private int numberOfNodesInDiameter = 0;
    }
}
