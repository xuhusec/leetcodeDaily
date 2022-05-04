public class LongestUnivaluePath {
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Info info = new Info();
        dfs(root, info);
        return info.numOfNodes - 1;
    }

    private int dfs(TreeNode cur, Info info) {
        if (cur == null) {
            return 0;
        }
        int left = dfs(cur.left, info);
        int right = dfs(cur.right, info);
        
        // only count left and right when the they are on the uni value path
        // i.e. their values matches the subtree root.
        int uniLeft = left > 0 && cur.val == cur.left.val ? left : 0;
        int uniRight = right > 0 && cur.val == cur.right.val ? right : 0;

        info.numOfNodes = Math.max(info.numOfNodes, 1 + uniLeft + uniRight);

        return 1 + Math.max(uniLeft, uniRight);
    }

    private static class Info {
        private int numOfNodes;
    }
}
