public class MinimizeHammingDistanceAfterSwapOperationsUF {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        final int N = source.length;
        UnionFind uf = new UnionFind(N);

        for (int[] allowedSwap : allowedSwaps) {
            uf.union(allowedSwap[0], allowedSwap[1]);
        }

        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();

        for (int i = 0; i < N; ++i) {
            Integer p = uf.find(i);
            groups.computeIfAbsent(p, k -> new HashMap<>()).merge(source[i], 1, (v1, v2) -> v1 + v2);
        }
        int res = 0;
        for (int i = 0; i < N; ++i) {
            Map<Integer, Integer> cntMap = groups.get(uf.find(i));
            if (cntMap.getOrDefault(target[i], 0) == 0) {
                res++;
            } else {
                cntMap.merge(target[i], -1, (v1, v2) -> v1 + v2);
            }
        }
        return res;
    }
    
    private static class UnionFind {
        private int[] parents;

        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < n; ++i) {
                parents[i] = i;
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

            if (pX < pY) {
                parents[pY] = pX;
            } else {
                parents[pX] = pY;
            }
        }
    }
}
