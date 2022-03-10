public class MinimizeHammingDistanceAfterSwapOperationsDFS {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        final int N = source.length;
        List<Integer>[] graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] allowedSwap : allowedSwaps) {
            int u = allowedSwap[0];
            int v = allowedSwap[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        Map<Integer, Integer>[] groups = new Map[N];
        for (int i = 0; i < N; ++i) {
            if (groups[i] != null) {
                continue;
            }
            dfs(i, source, graph, new HashMap<>(), groups);
        }
        int res = 0;
        for (int i = 0; i < N; ++i) {
            Map<Integer, Integer> cntMap = groups[i];
            if (cntMap.getOrDefault(target[i], 0) == 0) {
                res++;
            } else {
                cntMap.merge(target[i], -1, (v1, v2) -> v1 + v2);
            }
        }
        return res;
    }

    private void dfs(int id, int[] source, List<Integer>[] graph, Map<Integer, Integer> map,  Map<Integer, Integer>[] groups) {
        if (groups[id] != null) {
            return;
        }
        groups[id] = map;
        map.merge(source[id], 1, (v1, v2) -> v1 + v2);
        for (int next : graph[id]) {
            dfs(next, source, graph, map, groups);
        }
    }
}
