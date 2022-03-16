public class MinimumCosttoMakeatLeastOneValidPathinaGridDFBF {
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    public int minCost(int[][] grid) {
        final int M = grid.length;
        final int N = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[M][N];
        int ans = 0;
        if (dfsAndAddToQueue(grid, 0, 0, queue, visited, M, N)) {
            return ans;
        }

        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (dfsAndAddToQueue(grid, cur[0], cur[1], queue, visited, M, N)) {
                    return ans;
                }
            }
        }
        return -1;
    }

    private boolean dfsAndAddToQueue(int[][] grid, int r, int c, Queue<int[]> queue, boolean[][] visited, final int M, final int N) {
        if (r < 0 || r >= M || c < 0 || c >= N || visited[r][c]) {
            return false;
        }
        if (r == M - 1 && c == N - 1) {
            return true;
        }
        visited[r][c] = true;
        if (dfsAndAddToQueue(grid, r + DIRS[grid[r][c] - 1][0], c + DIRS[grid[r][c] - 1][1], queue, visited, M, N)) {
            return true;
        }

        for (int[] dir : DIRS) {
            int nR = r + dir[0];
            int nC = c + dir[1];
            if (nR < 0 || nR >= M || nC < 0 || nC >= N || visited[nR][nC]) {
                continue;
            }
            queue.add(new int[]{nR, nC});
        }
        return false;
    }
}