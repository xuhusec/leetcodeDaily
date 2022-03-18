public class PathwithMaximumProbabilityDirecty {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Info>[] adjs = new List[n];
        for (int i = 0; i < n; ++i) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; ++i) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            double prob = succProb[i];
            adjs[n1].add(new Info(n2, prob));
            adjs[n2].add(new Info(n1, prob));
        }

        // large probablity comes first
        PriorityQueue<Info> pq = new PriorityQueue<>((a,b) -> Double.compare(b.prob, a.prob));
        boolean[] visited = new boolean[n];

        // start with possiblity 1 because we begin with start the probability to reach start is 1
        pq.add(new Info(start, 1d));

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;
            if (cur.node == end) {
                return cur.prob;
            }

            for (Info next : adjs[cur.node]) {
                if (visited[next.node]) {
                    continue;
                }
                pq.add(new Info(next.node, next.prob * cur.prob));
            }
        }

        return 0;
    }

    private static class Info {
        private final int node;
        private final double prob;

        public Info (int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
}
