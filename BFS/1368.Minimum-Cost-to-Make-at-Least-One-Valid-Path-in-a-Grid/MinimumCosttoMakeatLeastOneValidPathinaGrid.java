public class MinimumCosttoMakeatLeastOneValidPathinaGrid {
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    public int minCost(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[M][N];
        pq.add(new int[]{0, 0 ,0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[1];
            int c = cur[2];

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;
            if (r == M -1 && c == N - 1) {
                return cur[0];
            }
            for (int i = 0; i < DIRS.length; ++i) {
                int nR = r + DIRS[i][0];
                int nC = c + DIRS[i][1];
                if (nR < 0 || nR >= M || nC < 0 || nC >= N || visited[nR][nC]) {
                    continue;
                }
                pq.add(new int[]{cur[0] + (grid[r][c] - 1 == i ? 0 : 1), nR , nC});
            }
        }

        return -1;

    }
}