import java.util.PriorityQueue;

public class ReachableNodesInSubdividedGraph {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        int[] dist = shortPath(edges, n);
        int res = 0;
        for (int[] edge : edges) {
            int s = edge[0];
            int d = edge[1];
            int e = edge[2];

            int sum = 0;
            //check connectivity
            if (dist[s] > -1) {
                // only work on dist <= maxMoves
                sum += Math.max(0, maxMoves - dist[s]);
            }
            if (dist[d] > -1) {
                sum += Math.max(0, maxMoves - dist[d]);
            }
            res += Math.min(sum, e);
        }
        for (int i = 0; i < n; ++i) {
            if (dist[i] > -1 && dist[i] <= maxMoves) {
                res++;
            }
        }
        return res;
    }

    private int[] shortPath(int[][] edges, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        List<int[]>[] adjs = new List[n];
        for (int i = 0; i < n; ++i) {
            adjs[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjs[edge[0]].add(new int[] {edge[1], edge[2] + 1});
            adjs[edge[1]].add(new int[] {edge[0], edge[2] + 1});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (dist[cur[0]] > -1) {
                continue;
            }
            dist[cur[0]] = cur[1];
            if (--n == 0) {
                break;
            }
            for (int[] next : adjs[cur[0]]) {
                if (dist[next[0]] > -1) {
                    continue;
                }
                pq.add(new int[]{next[0], next[1] + cur[1]});
            }
        }
        return dist;
    }


    //----------------------------------------

    public int reachableNodesPurning(int[][] edges, int maxMoves, int n) {
        int ans = 0;
        //Dijkstra
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        List<int[]>[] adjs = new List[n];
        for (int i = 0; i < n; ++i) {
            adjs[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjs[edge[0]].add(new int[] {edge[1], edge[2] + 1});
            adjs[edge[1]].add(new int[] {edge[0], edge[2] + 1});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (dist[cur[0]] > -1) {
                continue;
            }
            dist[cur[0]] = cur[1];
            if (++ans == n) {
                break;
            }
            for (int[] next : adjs[cur[0]]) {
                if (dist[next[0]] > -1) {
                    continue;
                }
                int nd = next[1] + cur[1];
                // skip if distance exceed maxMoves
                if (nd > maxMoves) {
                    continue;
                }
                pq.add(new int[]{next[0], nd});
            }
        }
        

        for (int[] edge : edges) {
            int s = edge[0];
            int d = edge[1];
            int e = edge[2];

            int sum = 0;
            //check connectivity
            if (dist[s] > -1) {
                // no need to check if distance > maxMoves. they are filtered during the shortest path part
                sum += maxMoves - dist[s];
            }
            if (dist[d] > -1) {
                sum += maxMoves - dist[d];
            }
            ans += Math.min(sum, e);
        }
        return ans;
    }
}