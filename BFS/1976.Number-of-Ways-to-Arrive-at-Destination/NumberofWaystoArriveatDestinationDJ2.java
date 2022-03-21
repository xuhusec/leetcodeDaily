public class NumberofWaystoArriveatDestinationDJ2 {
    private static final int MOD = (int) 1e9 + 7;
    public int countPaths(int n, int[][] roads) {
        long[] costs = new long[n];
        Arrays.fill(costs, Long.MAX_VALUE);
        int[] cnts = new int[n];
        cnts[0] = 1;
        
        List<Info>[] adjs = new List[n];
        for (int i = 0; i < n; ++i) {
            adjs[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            adjs[road[0]].add(new Info(road[1], road[2]));
            adjs[road[1]].add(new Info(road[0], road[2]));
        }
        
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
        pq.add(new Info(0,0));
        
        while (!pq.isEmpty()) {            
            Info cur = pq.poll();
            if (cur.cost > costs[cur.node]) {
                continue;
            }

            for (Info next : adjs[cur.node]) {
                long d = cur.cost + next.cost;
                if (d > costs[next.node]) {
                    continue;
                } else if (d < costs[next.node]) {
                    costs[next.node] = d;
                    cnts[next.node] = cnts[cur.node];
                    pq.add(new Info(next.node, d));
                } else {
                    cnts[next.node] = (cnts[next.node] + cnts[cur.node]) % MOD;
                }
                
            }
        }
        return cnts[n-1];
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