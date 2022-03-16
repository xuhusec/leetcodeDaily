### [1368. Minimum Cost to Make at Least One Valid Path in a Grid](https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)

#### Solution 1: Dijkstra

We can view this problem as a graph. Each node contains different status of nodes. The status is our current position. If you follow the direction in the graph, there is a link costing 0 between the current status to the next status. If you do not follow the direction, there is a link costing 1 between the current status to the next status. So the problem becomes a weighted graph problem with only two weights 0 and 1. And the question is to reach the bottom-right corner with minimum cost. That is a shortest path problem.

So we can just follow the Dijkstra procedure. For each node, we add the adjcent node to the priority queue. If it follows the direction specified by the grid[][]. There is no cost but if it does not we add one to the cost and then add it to the queue. Of course we need to check if the node is out of boundary after following some directions.

To make the program easier, I create a DIR array for the direction. Because the order is the same listed in the problem statement. But because its index starts from 0, it needs to be converted so we have `DIRS[grid[r][c] - 1]`. You can also use a map or insert an empty array in the DIRS to avoid the conversion.

#### Solution 2: DFS + BFS

The idea is the same as Dijkstra but we want to avoid the cost of priority queue which is O(logE) for insertion and deletion. Because the weights are only 0 and 1. We can perform the search greedily with DFS. Go as far as possible by following the direction specified by grid. If it reaches the end, we return the current cost. If not, we need the neighbors to the queue for BFS. In next round, we poll add the elements from last iteration and perform DFS again. In this way, we always search from the node with minimum cost by far but we do not need to use priority queue. This is because to reach any of neighbors not explored by previous or the current DFS is 1, the other nodes costs the same would be connected by DFS and the rest would cost more than 1. Also, since we know the cost increase by 1 each time, we can also not include them in the queue instead of a global cost is maintained just like normal BFS. 