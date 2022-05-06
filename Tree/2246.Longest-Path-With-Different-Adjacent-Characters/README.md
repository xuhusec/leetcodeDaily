### [2246.Longest-Path-With-Different-Adjacent-Characters](https://leetcode.com/problems/longest-path-with-different-adjacent-characters/)

#### Solution 1: DFS

This is another path problem in a tree. Similar to other problem, we can think about the qusetion by locating a turning point connect the left root path and the right path to form a the longest path. This is because a tree does not contains any cycle. The only way to connect two segement is to through the root of the minimum subtree contains all those nodes in the path. To find the max path through a node, we can work with dfs. It is the max path going through all its children nodes with different characters. The longest path connected by the node, on the other hand, is 1 + max(path from children with different character) + secondMax(path from children with different character) where 1 is itself. The dfs would help to list all path so that we can find the max and the secondMax (max can be equal to the second max). So we can perform dfs and record the longest path as a by product.

Please note this tree is a N-ary tree so it may contains more than two children.

Like 2049, this can also be resolved by topological sorting. Identity all leaves and going up.
```java
    // topological sorting solution
    public int longestPath(int[] parent, String s) {
        if (parent.length == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        
        int[] indegree = new int[parent.length];
        int[] dp = new int[parent.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1 ; i < parent.length; ++i) {
            indegree[parent[i]]++;
            map.computeIfAbsent(parent[i], k -> new ArrayList<>()).add(i);
        } 
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; ++i) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int ans = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (!map.containsKey(cur)) {
                ans = Math.max(ans, 1);
                dp[cur] = 1;
            } else {
                int max = 0;
                int secMax = 0;
                char ch = chars[cur];
                for (int i : map.get(cur)) {
                    if (chars[i] != ch) {
                        if (dp[i] > max) {
                            secMax = max;
                            max = dp[i];
                        } else if (dp[i] > secMax) {
                            secMax = dp[i];
                        }
                    }
                }
                dp[cur] = 1 + max;
                ans = Math.max(1 + max + secMax, ans);
            }
            if (cur > 0 && --indegree[parent[cur]] == 0) {
                queue.add(parent[cur]);
            }
            
        }
        return ans;
    }
```    