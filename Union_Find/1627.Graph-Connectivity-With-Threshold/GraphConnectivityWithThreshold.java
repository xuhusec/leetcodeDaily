public class GraphConnectivityWithThreshold {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n + 1);

        boolean[] visited = new boolean[n + 1];
        for (int i = threshold + 1; i <= n; ++i) {
            if (visited[i]) {
                continue;
            }
            int j = 2 * i;
            while (j <= n) {
                visited[j] = true;
                uf.union(i, j);
                j += i;
            }
        }
        List<Boolean> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            ans.add(uf.find(q[0]) == uf.find(q[1]));
        }

        return ans;
    }

    private static class UnionFind {
        private int[] parents;
        private int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];

            for (int i = 0; i < n; ++i) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            int pX = find(x);
            int pY = find(y);

            if (pX == pY) {
                return;
            }

            if (ranks[pX] > ranks[pY]) {
                ranks[pX] += ranks[pY];
                parents[pY] = pX;
            } else {
                ranks[pY] += ranks[pX];
                parents[pX] = pY;
            }
        }
    }
}