### [2203. Minimum Weighted Subgraph With the Required Paths](https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths/)

#### Solution 1: Dijkstra + prefix + suffix

The problem asks the shortest path from two sources to one destination and the common part only count once. The result is the minimum weight of a subgraph of the graph such that it is possible to reach dest from both src1 and src2 via a set of edges of this subgraph.

The common path must exists. It is either the final destination or somewhere in the middle. Can the two path diverage from met? No, let us say their shortest path to the destination met in the point mid. The shortest path from mid to the destination will be 1. First, from the proof of Dijkstra algorithm, we know,  if C is in the shortest path from A to B, the path from A to C in the original shortest path is also the shortest. Similarly, the path from C to B in the original shortest path is also shortest. Otherwise, we can reduce the cost again. So the path from mid to the dest in each route must be shortest as well. If there is only one such path, that is, Otherwise, because of the target of original question, we only select one of them so that the weight can be minimum.

Then the goal is to find which point is the middle so that the weight is minimum. We can have two "prefix" array and one "suffix" array to resolve this. The three arrays are shortest paths to all other nodes from src1, src2 and dest respectively. And we can try to go through n to find the best middle point. Please note from the dest, we need to go backward. That means we also need to build a reversed adjcent map. For the shortest distance from a single source to all other nodes only with non-negative weight, we can use Dijkstra. The whole idea is similar to Floyd-Warshall but only taken care of three nodes.

1. build the adjcent map and reverse adjcent map
2. run Dijkstra three times on (src1, adj), (src2, adj) and (dest, reverseAdj) to get the "prefix" and "suffix" arrays
3. loop all available nodes to find the minimun weight.

Notes
1. The unavaialbe path in the distance array is marked as -1 you can also use LONG.MAX_VALUE/3 (divided by 3 because we need sum dist1, dist2 and distD) to avoid `dist1[i] > -1 && dist2[i] > -1 && distD[i] > -1` but I think -1 is more generic for large values.
2. As the example shows, there might be two or more edges for a given pair. We can improve the performance by only keep the edge with minimun weight for each pair. I tried to record the following way but I got OOM. Then I sort the edges by from-node , to-node and weight and only keep the first seen edge for each pair.
```java
    private int[][] removeDup(int[][] edges, int n) {
        int[][] positions = new int[n][n];
        for (int[] p : positions) {
            Arrays.fill(p, -1);
        }
        int[][] ans = new int[edges.length][];
        int id = 0;
        for (int[] edge : edges) {
            if (positions[edge[0]][edge[1]] > - 1) {
                int pos = positions[edge[0]][edge[1]];
                if (ans[pos][2] > edge[2]) {
                    ans[pos][2] = edge[2];
                }
                continue;
            }
            positions[edge[0]][edge[1]] = id;
            ans[id++] = edge;
        }
        return Arrays.copyOf(ans, id);
    }
```