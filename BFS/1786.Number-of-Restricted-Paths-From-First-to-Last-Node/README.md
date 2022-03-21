### [1786. Number of Restricted Paths From First to Last Node](https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/)

#### Solution 1: Dijkstra + DFS + Memo

The questions is asking from path follows some rule with shortest path. And the paths does not have to be the shortest path. So we can split the question into two parts.
1. get the shortest cost travel to the last node for each node
2. construct the restricted path based on the result from 1.

The first part can be obtained with a shortest path problem. Because it is a graph with non-negative edge and the single source (the last node). We can apply Dijkstra.

For the second part. we can run dfs and pruning to only search on the satisfied restrict routes. Clearly, a naive dfs would repeat searching a lot of node multiple times, we can memorize it to speed up. This can be hard to implement on dp because the order of travel does not have to follow the node index. DFS + memo works well because there is no restriction on the order of node. Also, because once we get into the same node the number of restrict routes from that node is the same. Only an int array is needed. Please do not forget to record zero cnt. That is why the cnt is filled -1 in the beginning. You can also use a map or the Integer Array where null means have not explored to differentiate zero count path and the unexplored path. 