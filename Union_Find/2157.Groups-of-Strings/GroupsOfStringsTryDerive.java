import java.util.HashMap;
import java.util.Map;

public class GroupsOfStringsTryDerive {
    public int[] groupStrings(String[] words) {
        final int N = words.length;
        UnionFind uf = new UnionFind(N);
        Map<Integer, Integer> maskIdMap = new HashMap<>();
        int[] masks = new int[N];
        for (int i = 0; i < N; ++i) {
            masks[i] = getMask(words[i]);
            maskIdMap.put(masks[i], i);
        }

        int[] exists = new int[26];
        int[] absent = new int[26];
        int eId = 0;
        int aId = 0;
        for (int i = 0; i < N; ++i) {
            int cur = masks[i];
            eId = 0;
            aId = 0;
            for (int j = 0; j < 26; ++j) {
                int bit = 1 << j; 
                if ((cur & bit) > 0) {
                    exists[eId++] = j;

                    // rule 1 and 2
                    Integer deleted = cur ^ bit;
                    if (maskIdMap.containsKey(deleted)) {
                        uf.union(maskIdMap.get(deleted), i);
                    }
                } else {
                    absent[aId++] = j;
                }
            }

            // rule 3
            int id = maskIdMap.get(cur);
            if (id != i) {
                uf.union(id, i);
            }

            if (eId == 0 || aId == 0) {
                continue;
            }

            for (int j = 0; j < eId; ++j) {
                int deleted = cur ^ (1 << exists[j]);
                for (int k = 0; k < aId; ++k) {
                    Integer replaced = deleted | (1 << absent[k]);
                    if (maskIdMap.containsKey(replaced)) {
                        uf.union(maskIdMap.get(replaced), i);
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