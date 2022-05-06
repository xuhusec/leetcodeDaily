public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        Info info = new Info();
        maxSingleSidePathSum(root, info);
        return info.ans;
    }

    private int maxSingleSidePathSum(TreeNode cur, Info info) {
        if (cur == null) {
            return 0;
        }

        int left = maxSingleSidePathSum(cur.left, info);
        int right = maxSingleSidePathSum(cur.right, info);

        info.ans = Math.max(info.ans, cur.val + left + right);

        return Math.max(0, cur.val + Math.max(left, right));
    }

    private static class Info {
        private int ans = Integer.MIN_VALUE;
    }
}