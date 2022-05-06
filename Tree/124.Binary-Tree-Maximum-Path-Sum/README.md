### [124.Binary-Tree-Maximum-Path-Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

#### Solution 1: DFS

It asks for some path in a tree. Because a tree does not contain cycles, a path must contains a root of a subtree in the original tree. That could be a single node or multiple nodes. For those multiple nodes, the root of the subtree would connect the left side and right side. However, it is optional to include the max sum down path in the left and the max sum down path in the right because they might be negative. Here the down path meaning there is no turing point. For each node, it would to the left node, the right node or nothing but it would not go to both the left node and the right node. Only the root of the subtree we are looking at can doing so. In this way, we can keep return the max sum of  down path/single side path. The max left side down path + the max right side down path + the cur value would be a candidate for the solution.

So, we have a function to calcaulate the down path and we need record the max path sum during the process.

```java
    private int maxSingleSidePathSum(TreeNode cur, Info info) {
        if (cur == null) {
            return 0;
        }

        int left = maxSingleSidePathSum(cur.left, info);
        int right = maxSingleSidePathSum(cur.right, info);

        info.ans = Math.max(info.ans, cur.val + left + right);

        return Math.max(0, cur.val + Math.max(left, right));
    }
```

Please note a node can be negative. So we need set the starting value as Integer.MIN_VALUE instead of zero. Also, we can skip the negative subpath. it is `Math.max(0, cur.val + Math.max(left, right));` where `cur.val + Math.max(left, right)` is the max path include the current subtree root. However, it might negative and In that case, we just return 0 to indicate the node and its subtree are not selected in the final path