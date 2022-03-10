public class GroupsOfStringsTimeOut {
    public int[] groupStrings(String[] words) {
        final int N = words.length;
        UnionFind uf = new UnionFind(N);
        int[][] info = new int[N][2];
        for (int i = 0; i < N; ++i) {
            info[i][0] = getMask(words[i]);
            info[i][1] = words[i].length();
        }

        for (int i = 0; i < N - 1; ++i) {
            int[] cur = info[i];
            for (int j = i + 1; j < N; ++j) {
                int[] cand = info[j];
                int mask = cur[0] ^ cand[0];
                if ((mask & (mask - 1)) == 0) {
                    uf.union(i, j);
                } else if (cand[1] == cur[1]) {
                    mask = mask & (mask - 1);
                    if ((mask & (mask - 1)) == 0) {
                        uf.union(i, j);
                    }
                }
            }

        }

        boolean[] visited = new boolean[N];
        int max = 0;
        int cnt = 0;
        for (int i = 0; i < N; ++i) {
            int p = uf.find(i);
            if (!visited[p]) {
                visited[p] = true;
                cnt++;
                max = Math.max(max, uf.getRanks(p));
            } 
        }
        return new int[]{cnt, max};
    }

    private int getMask(String word) {
        int res = 0;
        for (int i = 0; i < word.length(); ++i) {
            res |= 1 << (word.charAt(i) - 'a');
        }
        return res;
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
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public int getRanks(int x) {
            return ranks[x];
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