### [743. Network Delay Time](https://leetcode.com/problems/network-delay-time/)

#### Solution 1: Brute Force with BFS or DFS

The idea is to go through all possible paths to record the minimum time of traverse all nodes from k. The search begins from k and try all possible paths. For DFS, you can perform the backtracking with visited array to try different paths. 
For BFS, one can have a int array for the minimum travel time discovered so far and only when the current time is less than recorded minimum travel time of that node, it would be offered to the queue. It is actually can be used in DFS as well, instead of visited array, continue explore adjcent node only when it can reach the node with less time/cost than before.

Please note the BFS + visited boolean array does not work here. This is because each edge has a weight and in the BFS, it is not considered. Without weight, BFS would find the shortest path by visiting order. But with weight we need try different path. It might take more edges to reach a node but it costs less.

This approach is not documented here.

#### Solution 2: [Dijkstra](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm) 

Dijkstra can be implemented in two way.
1. BFS + PQ (sparse graph)
2. BFS + node scan (dense graph)

For a graph with V nodes and E edges. The first one provide O(ElogE). And the second one is O(V<sup>2</sup>). Usually, the first approach performance better.

In this first approach, instead of a normal queue. Priority Queue was used so that we always work on a node when the shortest path to that node is found. A difference besides the queue is that we only mark a node is visited when it is out of the queue. We cannot mark it when added it into the queue. That is because a better solution might be added later. And for normal BFS, it does not matter if you mark it when it exits the queue or add to the queue.

In the second appraoch, an array of distance from the origin is maintained along with a visited array. From the begining the dist array is set to Integer.MAX_VALUE or Integer.MAX_VALUE/2 (in case overflow) except the origin which should be 0. Then in each round. 1) find the minimum distance that is not visited. 2) update the rest nodes if a shorter path is found by traveling from that node to the other node 3) mark this node visited and repeat this process until no travel can be made to improve the travel cost.

Please note the above approach only work with positive cost of edges. That gives the cost non decreasing after a node being visted first time. But a negative edge can decrease the cost and in that time this node must be visited again so does the node connected to it.

The example is implemented with the first approach.

It is common that the question gives the costs something like {start, end, cost} array. For BFS, Dijkstra, Tological Sorting etc, it is ususally converted to the adjcent map. The key is the current node, and the value is a list of node it can reaches in the next round. We need this because when a node is reached, we need to know which nodes can be travelled to. You can use `Map<Integer, Info>` or `List<Info>[]` (if total number of node can be easily obtained) where Info represents a custom class to record next node and the cost to the next node. A int[] might be good enough for Info.

This problem asks form one source to all nodes. Usually, it only asks for one source to one destination. The following is an example.
```java
PriorityQueue<Info> pq = new PriorityQueue<>(8, (a,b)->....);
pq.add(o);
boolean[] visited = new boolean[n];
int res = 0;
while (!pq.isEmpty()) {
    Info cur = pq.poll();
    if (visited[cur.node]) {
        continue;
    }
    visited[cur.node] = true;
    
    // for a single node, you can break if ound
    if (cur.node == target) {
        return cur.cost;
    }

    for (Info next : adjMap.get(cur.node)) {
        if (visited[visited[next.node]]) {
            continue;
        }
        Info infoNext = new Info();
        infoNext.setNode(next.node);
        infoNext.setCost(next.cost + cur.cost);
        pq.add(infoNext);
    }
}
```

For this problem, some node might not be reachable, we can have a counter when a node is exists from the queue first time. If not, you can have a array to record the distance and check if the initial value is still here to find out if all nodes are visited or not.


#### Solution 3: [Floyd-Warshall](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)

Floyd-Warshall tries to improve current state by going through a intermediate node.

```java
for (int m = 0; m < n; ++m) {
    for(int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            dist[i][j] = Math.min(dist[i][j], dist[i][m] + dist[m][j]);
        }
    }
}
```

In initialization, dist is set to very large value to repesents infinity except there is a path between i and j, aka `dist[i][j] = cost[i][j]` and `dp[i][i] = 0`. As you can see this is 3 levels nested loop, the time comlexity is O(V<sup>3</sup>). The result is for all nodes to all nodes not a single source. No limitation on negative costs.

