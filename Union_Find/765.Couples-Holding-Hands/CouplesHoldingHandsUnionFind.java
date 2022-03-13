public class CouplesHoldingHandsGreedy {
    public int minSwapsCouples(int[] row) {
        final int N = row.length;
        UnionFind uf = new UnionFind(N + 1);
        for (int i = 0; i < N/2; ++i) {
            uf.union(2 * i, 2 * i + 1);
            uf.union(row[2 * i], row[2 * i + 1]);
        }
        int ans = 0;
        for (int i = 0; i < N/2; ++i) {
            int p = uf.find(row[2 * i]);
            if (p == uf.find(N)) {
                continue;
            }
            int rank = uf.getRank(p);
            if (rank > 2) {
                ans += rank / 2 - 1; 
            }
            uf.union(p, N);

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
        public int getRank(int x) {
            return ranks[find(x)];
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