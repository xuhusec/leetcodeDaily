import java.util.Collections;

public class CheapestFlightsWithinKStopsDP {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Info>> adjs = new HashMap<>();
        for (int[] f : flights) {
            adjs.computeIfAbsent(f[0], ak -> new LinkedList<>()).add(new Info(f[1], f[2], 0));
        }

        PriorityQueue<Info> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        pq.add(new Info(src, 0, 0));
        boolean[][] visited = new boolean[n][k+2];

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.node][cur.times]) {
                continue;
            }
            visited[cur.node][cur.times] = true;
            if (cur.node == dst) {
                return cur.cost;
            }
            int nextTimes = cur.times + 1;
            if (nextTimes > k + 1) {
                continue;
            }
            for (Info next : adjs.getOrDefault(cur.node, Collections.emptyList())) {
                if (visited[next.node][nextTimes]) {
                    continue;
                }
                pq.add(new Info(next.node, next.cost + cur.cost, nextTimes));
            }
        }
        return -1;
    }

    private static class Info {
        private final int node;
        private final int cost;
        private final int times;

        public Info(int node, int cost, int times) {
            this.node = node;
            this.cost = cost;
            this.times = times;
        }
    }
}