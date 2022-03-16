public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] adjs = new List[n+1];
        for (int i = 1; i < adjs.length; ++i) {
            adjs[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            adjs[time[0]].add(time);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        pq.add(new int[]{0, k});
        boolean[] visited = new boolean[n+1];
        int cnt = 0;
        int res = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (visited[cur[1]]) {
                continue;
            }
            cnt++;
            res = cur[0];
            visited[cur[1]] = true;

            for (int[] next : adjs[cur[1]]) {
                if (visited[next[1]]) {
                    continue;
                }
                pq.add(new int[]{res + next[2], next[1]});
            }
        }

        return cnt < n ? -1 : res;
    }
}