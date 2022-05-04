### [2049.Count-Nodes-With-the-Highest-Score](https://leetcode.com/problems/count-nodes-with-the-highest-score/)

#### Solution 1: DFS

According to the statement, a score after removing a node is the product of the node cnt of its left subtree, that of the right subtree and that of rest of the origal after removing the subtree with that node as root. Of course, when the cnt is 0, we should not multiply it or we can just multiple the rest score by 1. So this problem becomes getting individual subtree cnts. The total node count is provided by the length of the parents array. The cnt of the rest tree is the total - the cnt of the current subtree.

To get the cnt for each subtree, we can perform DFS. The score can be gotten as a byproduct. When calcualte the cnt, we need to recursively calculate the subtree cnt of the children. The total would be the sum of all its subtree cnts + 1 and the product would be multiply them and the cnt of the rest original tree.

Please be aware that the question asks for the count of nodes with max score. It is NOT the max score. So we need to keep track the max and its count. Also, the score can be very large. so we need long instead of int in case of overflow.

The cnt can also be obtained by topological sorting. 

```java
    public int countHighestScoreNodes(int[] parents) {
	    // an array to record indegree of a node
        int[] indegrees = new int[parents.length];
		// because it is a binary tree, there are most two children of a node. So int[parents.length][2] is enough
		// in general this can be a List<Integer>[] instead of int[][]
        int[][] children = new int[parents.length][2];
		// record number of nodes in the subtree exculde the current node.
        int[] subtreeCnts = new int[parents.length];
		
		subtreeCnts[0] = 1;
		// compute the indgree of a node and record its two children
        for (int i = 1; i < parents.length; ++i)  {
            int p = parents[i];
            indegrees[p]++;
            if (children[p][0] == 0) {
                children[p][0] = i;
            } else {
                children[p][1] = i;
            }
			subtreeCnts[i] = 1;
        }
		// toplogical sorting. 
        Queue<Integer> queue = new LinkedList<>();
		// identify all leaf nodes
        for (int i = 0; i < indegrees.length; ++i) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
			int p = parents[cur];
			// no parent of the root node, skip 
            if (p < 0) {
                continue;
            }
			// the childCnts exclude the node itself. because 
            subtreeCnts[p] +=  subtreeCnts[cur];
            indegrees[p]--;
            if (indegrees[p] == 0) {
                queue.add(p);
            }
        }
        
        long score = 0;
        int res = 0;
        final int TOTAL = subtreeCnts[0];
        for (int i = 0; i < parents.length; ++i) {
            long cur = parents[i] == -1 ? 1 : (TOTAL -  subtreeCnts[i]);
            cur *=(children[i][0] == 0 ? 1 : subtreeCnts[children[i][0]]) * (children[i][1] == 0 ? 1 : subtreeCnts[children[i][1]]);
            if (cur > score) {
                score = cur;
                res = 1;
            } else if (cur == score) {
                res++;
            }
        }
        
        return res;
    }
```