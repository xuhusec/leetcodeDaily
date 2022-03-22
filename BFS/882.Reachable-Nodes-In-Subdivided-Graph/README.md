### [882. Reachable Nodes In Subdivided Graph](https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/)

#### Solution 1: Dijkstra

For this problem, we can first check if a original node (node exists before divided) can be reached. When the shortest distance between the source and a node is less than the max moves, the node can be reached. The edge between two node can be viewed as a weighted edge the weight is number of edges after subdivision. One source to all nodes and the weigth is non-negative. Dijkstra can be used. It is not hard to get the weight is cnt<sub>i</sub> + 1. i.e. Insert one point would result to 2 edges. Insert 2 points would lead to 3 edges..... 
After checking all the original nodes, we can check new node inserted in the graph. Obviously, to reach them, we need to start from an ends on the original edge and going through. How many steps one can move from the end? It is the maxMoves - shortest distance to that original node. We can got number of nodes can be reached from two ends. The sum of them would be the number of new nodes we can reach if it is not exceeded the total number of new nodes inserted. So we need to have a upper bound.

There is one catch in the 3rd example. A node may be separate from the graph so we need first check if we can reach that node before the computation. In my implementation, I mark it as -1 and we need to skip such node.

pruning

since we need to check connectivity anyway, we can just ingore the node exceed maxMoves in the shortest path part. We only track paths with a weight less than or euqal to maxMoves. and track the reachable node during the shortest path solution. For details you can check reachableNodesPurning.

