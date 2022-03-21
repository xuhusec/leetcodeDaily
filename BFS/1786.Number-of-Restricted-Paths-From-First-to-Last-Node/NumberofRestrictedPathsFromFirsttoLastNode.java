import java.util.PriorityQueue;

public class NumberofRestrictedPathsFromFirsttoLastNode {
    private static final int MOD = (int) 1e9 + 7;
    public int countRestrictedPaths(int n, int[][] edges) {
        List<Info>[] adjs = new List[n];
        for (int i = 0; i < adjs.length; ++i) {
            adjs[i] = new ArrayList<>();
        }
        // convert to 0 indexed from 1 indexed
        for (int[] edge : edges) {
            int s = edge[0] - 1;
            int d = edge[1] - 1;
            int c = edge[2];
            adjs[s].add(new Info(d, c));
            adjs[d].add(new Info(s, c));
        }

        int[] dist = shortestDistFromLast(n, adjs);
        int[] cnts = new int[n];
        Arrays.fill(cnts, -1);
        return dfs(0, n - 1, dist, adjs, cnts);
    }

    private int[] shortestDistFromLast(int total, List<Info>[] adjs) {
        int[] dist = new int[total];
        Arrays.fill(dist, -1);
        PriorityQueue<Info> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Info(total-1, 0));

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (dist[cur.node] > -1) {
                continue;
            }
            dist[cur.node] = cur.cost;
            if (--total == 0) {
                break;
            }
            for (Info next : adjs[cur.node]) {
                if (dist[next.node] > -1) {
                    continue;
                }
                pq.add(new Info(next.node, next.cost + cur.cost));
            }
        }
        return dist;
    }

    private int dfs(final int id, final int dest, final int[] dist, final List<Info>[] adjs, final int[] mem) {
        if (id == dest) {
            return 1;
        }

        if (mem[id] > -1) {
            return mem[id];
        }
        int ans = 0;
        final int d = dist[id];
        for (Info next : adjs[id]) {
            if (dist[next.node] < d) {
                ans = (ans + dfs(next.node, dest, dist, adjs, mem)) % MOD;
            }
        }
        mem[id] = ans;
        return ans;
    }

    private static class Info {
        private final int node;
        private final int cost;

        public Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
