### 765. Couples Holding Hands

#### Solution 1: Greedy

The greedy method would try to pair couples every time a voilation is observed. In this problem, couples would only occupy seats at 2 * i and 2 * i + 1 in the final arrangement. So, we can check if current people sits at 2 * i and 2 * i + 1 are couple. If yes, we do not need to do anything. Otherwise, we keep one person and swap the other for his/her spouse. 

Why this works? Think ith node represents the seats 2 * i and 2 * i + 1. The node is connecting to other nodes where their spouse seated in. For example we have the follow arr.

2 5   1 3  4 6
The node 0 is from seat 0 to seat 1 and people 2 and people 5 are there.
The node 1 is from seat 2 to seat 3 and people 1 and people 3 are there.
The node 2 is from seat 4 to seat 5 and people 4 and people 6 are there.

node 0 is connected to node 1 because people 1 and people 2 are couple
node 0 is connected to node 2 because people 4 and people 5 are couple
node 1 is connected to node 2 because people 3 and people 4 are couple

So node 0, node 1 and node 2 are connected.

If we keep fix one node in each step, for a n-node connected graph (or the order of the connected graph is n), it would make n - 1 swaps to fix all nodes with our greedy solution. Every time a couple is reunion, after n - 1 swaps the last node is also good because the couple are in pair the previous n - 1 couple are good the rest one is also good.  We can view those swaps as edge of the graph. That means we can have n - 1 edges in this graph to reunite the couples.

But for a connected graph with order n, it at least has n - 1 edges. So this is the best solution.

For the above statement. You can prove it by contradication.

For two nodes, it is obviously 1 edge. Also for three node, it would 2 edges. They are satisfy n - 1 edges. Let's assume for n >= 4, there exists a m such that m is the minimum number of nodes such that only at most m - 2 edges are required to get a connected graph.

Let us check the degree of the nodes. The degree of a node here is the number of edges connected to a node.

If there is a node with degree of 0, it does not connected to other node. it won't be in the connected graph with order m.

If there is a node with degree of 1, it is the end node, meaning you can remove its edge and the rest of the graph is still connected. Let's check the rest of the graph.
   There is m - 1 node and it has at most m - 3 edges because the orignal graph contains at most m - 2 edges.  So for the rest of the graph is also satisfy the condition m -1 nodes with m - 3 edges. But it is contradict to m is the minimum number of nodes can form such connected graph and we already know when n <= 3, it does not satisfy at most n - 2. So there is not correct.

If all nodes with at least 2 degree / 2 edges, Let's say there are k edges in the graph. Each edge connecting two nodes. We would get 2 * k (the sum of degree) >= 2 * m ( m nodes with at least 2 degree). And we would get k >= m and m > m - 2. So k > m - 2. that contradicts to at most m - 2 edges.

So there are at least n - 1 edges.

Then we can implement this greedy approach as follows:
1. save the number in row and its id in a map. Since row contains unique iterm, we would store all the information in map. row[i] as key and i as value.
2. iterate ove 2 * i indices in the array and check if the element at 2 * i + 1 is its couple.
   1. if yes, do nothing
   2. if no, swap those two couple and record the swap and update the map with new ids.

 #### Solution 2: UnionFind

As stated in the Greedy method, swapping node within each group would perform m - 1 swapping for the best result where m is number of messed pair that are connected. We can improve this by not performing the actual swapping because we already know how many swaps are required.

1. union 2 * i and 2 * i + 1
2. union row[2 * i] and row[2 * i + 1]
3. iterate over 0, 2, 4, .., N -1. find if the group 2 * i located should be counted
   1. if the size of the group is 2, they are couple skip
   2. if the group is already counted, skip
   3. if the group is not counted, the resut should add number of memebers / 2 - 1. number of memebers /2 is how many couple in the group and make the group visited.

Notes
1. In step 3, you can use row[2 * i] instead of 2 * i but is does not matter. 
2. When create UnionFind object, I add an extra node as special node for visited node, i.e UnionFind(N+1). To mark a group is visited would be union with that special node `uf.union(2 * i, N);` and check if a node is already visited `uf.find(2 * i) == uf.find(N)`.
3. If you want to keep the orignal data in UnionFind for some reason, you can create another array of size N. and calcuate the group size by `cnts[uf.find(2 * i)]++;` and go over the cnts array to compute the result. In current implementation, I use the ranks array to get the count during union and improve the path compression efficiency. So count is already for free.

[Leetcode Link](https://leetcode.com/problems/couples-holding-hands/)
