public class MinimumWeightedSubgraphWiththeRequiredPaths {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<int[]>[] adjs = new List[n];
        List<int[]>[] reAdjs = new List[n];
        
        for (int i = 0; i < n; ++i) {
            adjs[i] = new ArrayList<>();
            reAdjs[i] = new ArrayList<>();
        }
        // sort to only keep the shortest edge from a given pair
        Arrays.sort(edges, (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);
        int[] prev = {-1,-1,-1};
        for (int[] edge : edges) {
            if (prev[0] == edge[0] && prev[1] == edge[1]) {
                continue;
            }
            adjs[edge[0]].add(new int[]{edge[1], edge[2]});
            reAdjs[edge[1]].add(new int[]{edge[0], edge[2]});
            prev = edge;
        }

        long[] dist1 = shortestPathFrom(src1, adjs);
        long[] dist2 = shortestPathFrom(src2, adjs);
        long[] distD = shortestPathFrom(dest, reAdjs);

        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (dist1[i] > -1 && dist2[i] > -1 && distD[i] > -1) {
                res = Math.min(res, dist1[i] + dist2[i] + distD[i]);
            }
        }

        return res < Long.MAX_VALUE ? res : - 1;
    }

    private long[] shortestPathFrom(int source, List<int[]>[] adjs) {
        long[] ans = new long[adjs.length];
        Arrays.fill(ans, -1L);
        PriorityQueue<Info>  pq = new PriorityQueue<>((a,b) -> Long.compare(a.weight, b.weight));
        pq.add(new Info(source, 0));
        int left = adjs.length;
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (ans[cur.node] > -1) {
                continue;
            }
            ans[cur.node] = cur.weight;
            if (--left == 0) {
                break;
            }
            for (int[] next : adjs[cur.node]) {
                if (ans[next[0]] > -1) {
                    continue;
                }
                pq.add(new Info(next[0], next[1] + cur.weight));
            }
        }
        return ans;
    }

    private static class Info {
        private final int node;
        private final long weight;

        public Info(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}