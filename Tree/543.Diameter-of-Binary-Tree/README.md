### [543.Diameter-of-Binary-Tree](https://leetcode.com/problems/diameter-of-binary-tree)

#### Solution 1: DFS

The longest path would contain a turning point. In a tree, there is no cycle. The truning point is the root of the subtree contains the two segements. Please note it is possible that any of those two segment contains no nodes. For a given turning point, we can get the longest path as `1 + the depth in the left subtree and + the depth path in the right subtree`. Here we define depth as the longest path from the root of the subtree to its farthest leaf node. Also the depth of the current node would be `1 + Math.max(left depth, right depth)`. To find the depth for every node, we can perform dfs. 

```java
    private int dfs(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        
        int left = dfs(cur.left);
        int right = dfs(cur.right);

        return 1 + Math.max(left, right);
    }
```

During the process, we can record the longest path `1 + left + right` as a byproduct. It can be a global or stored in a helper class. I prefer the later one as it is thread safe.
