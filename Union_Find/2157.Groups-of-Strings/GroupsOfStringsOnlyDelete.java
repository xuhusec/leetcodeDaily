import java.util.HashMap;
import java.util.Map;

public class GroupsOfStringsOnlyDelete {
    public int[] groupStrings(String[] words) {
        final int N = words.length;
        UnionFind uf = new UnionFind(N);
        Map<Integer, Integer> maskIdMap = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            int cur = getMask(words[i]);
            if (maskIdMap.containsKey(cur)) {
                uf.union(maskIdMap.get(cur), i);
            } else {
                maskIdMap.put(cur, i);
            }

            for (int j = 0; j < 26; ++j) {
                int bit = 1 << j;
                if ((cur & bit) > 0) {
                    int deleted = cur ^ bit;
                    if (maskIdMap.containsKey(deleted)) {
                        uf.union(maskIdMap.get(deleted), i);
                    } else {
                        maskIdMap.put(deleted, i);
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