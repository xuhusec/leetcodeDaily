import java.util.PriorityQueue;

public class MinimumCosttoReachCityWithDiscounts {
    public int minimumCost(int n, int[][] highways, int discounts) {
        List<Info>[] adjs = new List[n];
        for (int i = 0; i < n; ++i) {
            adjs[i] = new LinkedList<>();
        }
        for (int[] h : highways) {
            adjs[h[0]].add(new Info(h[1], h[2], 0));
            adjs[h[1]].add(new Info(h[0], h[2], 0));
        }

        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.toll, b.toll));
        pq.add(new Info(0, 0, discounts));
        boolean[][] visited = new boolean[n][discounts + 1];
        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.node][cur.discounts]) {
                continue;
            }
            visited[cur.node][cur.discounts] = true;
            if (cur.node == n - 1) {
                return cur.toll;
            }
            for (Info next : adjs[cur.node]) {
                // if [next.node][cur.discounts] has been visited. the cost of current cur.toll + next.toll would be larger than that
                // also a smaller toll with cur.discounts - 1 has been added into the pq. there is no need to explore neither option 
                if (visited[next.node][cur.discounts]) {
                    continue;
                }
                pq.add(new Info(next.node, cur.toll + next.toll, cur.discounts));
                //make sure we still have discount
                //[next.node][cur.discounts - 1] can polled before [next.node][cur.discounts] so it is still valid to check 
                if (cur.discounts > 0 && !visited[next.node][cur.discounts - 1]) {
                    pq.add(new Info(next.node, cur.toll + next.toll/2, cur.discounts - 1));
                }
            }
        }
        return -1;
    }

    private static class Info {
        private final int node;
        private final int toll;
        private final int discounts;

        public Info(int node, int toll, int discounts) {
            this.node = node;
            this.toll = toll;
            this.discounts = discounts;
        }
    }
}