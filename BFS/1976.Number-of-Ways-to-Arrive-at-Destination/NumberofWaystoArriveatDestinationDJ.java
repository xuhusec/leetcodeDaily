public class NumberofWaystoArriveatDestinationDJ {
    private static final int MOD = (int)1e9 + 7;
    public int countPaths(int n, int[][] roads) {
        List<Info>[] adjs = new List[n];
        for (int i = 0; i < adjs.length; ++i) {
            adjs[i] = new LinkedList<>();
        }

        for (int[] road : roads) {
            adjs[road[0]].add(new Info(road[1], road[2]));
            adjs[road[1]].add(new Info(road[0], road[2]));
        }
        
        long[] costs = new long[n];
        Arrays.fill(costs, -1);
        //get shortest path by Dijkstra
        PriorityQueue<Info> pq = new PriorityQueue<>((a,b) -> Double.compare(a.cost, b.cost));
        pq.add(new Info(0,0));
        int left = n;
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (costs[cur.node] > -1) {
                continue;
            }
            costs[cur.node] = cur.cost;
            if (--left == 0) {
                break;
            }
            for (Info next : adjs[cur.node]) {
                if (costs[next.node] > -1) {
                    continue;
                }
                pq.add(new Info(next.node, next.cost + cur.cost));
            }
        }
        int[] cnts = new int[n];
        Arrays.fill(cnts, -1);
        return dfs(0, 0, n - 1, costs, adjs, cnts);
    }

    private int dfs(int cur, long cost, int dest, long[] costs, List<Info>[] adjs, int[] cnts) {
        if (cost > costs[cur]) {
            return 0;
        }
        if (cur == dest) {
            return 1;
        }

        if (cnts[cur] > -1) {
            return cnts[cur];
        }

        int res = 0;

        for (Info next : adjs[cur]) {
            res = (res + dfs(next.node, next.cost + cost, dest, costs, adjs, cnts))% MOD;
        }

        cnts[cur] = res;
        return res;
    }

    private static class Info {
        private final int node;
        private final long cost;

        public Info(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}