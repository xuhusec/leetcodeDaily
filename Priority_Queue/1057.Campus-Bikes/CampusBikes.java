public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        final int M = workers.length;
        final int N = bikes.length;
        int[][] dist = new int[M][N];

        for (int i = 0; i < M; ++i) {
            int[] worker = workers[i];
            for (int j = 0; j < N; ++j) {
                dist[i][j] = Math.abs(worker[0] - bikes[j][0]) + Math.abs(worker[1] - bikes[j][1]);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]);
        boolean[] usedBikes = new boolean[N];
        
        for (int i = 0; i < M; ++i) {
            pq.add(findClosestBike(i, dist, usedBikes));
        }
        int[] ans = new int[M];
        int left = M;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int bikeId = cur[2];
            int workerId = cur[1];
            if (usedBikes[bikeId]) {
                pq.add(findClosestBike(workerId, dist, usedBikes));
                continue;
            }
            usedBikes[bikeId] = true;
            ans[workerId] = bikeId;
            if (--left == 0) {
                break;
            } 
        }
        return ans;
    }

    private int[] findClosestBike(int workerId, int[][] dist, boolean[] usedBikes) {
        int[] ans = {Integer.MAX_VALUE, workerId, -1};
        int[] d = dist[workerId];
        for (int i = 0; i < d.length; ++i) {
            if (usedBikes[i]) {
                continue;
            }
            if (d[i] < ans[0]) {
                ans[0] = d[i];
                ans[2] = i;
            }
        }
        return ans;
    }
}
