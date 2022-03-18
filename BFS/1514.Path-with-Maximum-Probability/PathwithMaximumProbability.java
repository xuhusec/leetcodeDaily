public class PathwithMaximumProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Info>[] adjs = new List[n];
        for (int i = 0; i < n; ++i) {
            adjs[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; ++i) {
            int n1 = edges[i][0];
            int n2 = edges[i][1];
            double cost = -Math.log(succProb[i]);
            adjs[n1].add(new Info(n2, cost));
            adjs[n2].add(new Info(n1, cost));
        }

        PriorityQueue<Info> pq = new PriorityQueue<>((a,b) -> Double.compare(a.cost, b.cost));
        boolean[] visited = new boolean[n];

        pq.add(new Info(start, 0));

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.node]) {
                continue;
            }
            visited[cur.node] = true;
            if (cur.node == end) {
                return Math.exp(-cur.cost);
            }

            for (Info next : adjs[cur.node]) {
                if (visited[next.node]) {
                    continue;
                }
                pq.add(new Info(next.node, next.cost + cur.cost));
            }
        }

        return 0;
    }

    private static class Info {
        private final int node;
        private final double cost;

        public Info (int node, double cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
