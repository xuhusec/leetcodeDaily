public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        Info info = new Info();
        inorderTraverse(root, info);
        swap(root, info);
    }

    private void inorderTraverse(TreeNode cur, Info info) {
        if (cur == null) {
            return;
        }

        inorderTraverse(cur.left, info);
        // <= is because the value of node can be Integer.MIN_VALUE 
        // Then it will equal to our initial value Integer.MIN_VALUE for info.prev.val
        if (info.prev.val <= cur.val) {
            info.prev = cur;
        } else {
            if (info.first == null) {
                // for the first node it is greater than the previous node. So it is info.prev.val
                info.first = info.prev;
                // keep the current node as candidates in case they were adjacent and it cannot be told by comparision
                info.second = cur;
            } else {
                // for the second node, it is less than the previous node. So it is the current node.
                info.second = cur;
                // after finding two nodes, we can stop
                return;
            }
            info.prev = cur;
        }
        inorderTraverse(cur.right, info);
    }

    private void swap(TreeNode root, Info info) {
        var temp = info.first.val;
        info.first.val = info.second.val;
        info.second.val = temp;
    }

    private static class Info {
        private TreeNode first;
        private TreeNode second;
        private TreeNode prev;
        
        public Info() {
            prev = new TreeNode(Integer.MIN_VALUE);
        }
    }
}
