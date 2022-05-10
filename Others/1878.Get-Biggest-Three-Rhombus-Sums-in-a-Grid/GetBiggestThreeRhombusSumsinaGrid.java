public class GetBiggestThreeRhombusSumsinaGrid {
    public int[] getBiggestThree(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int limit = Math.min(m, n);

        int[][] preSumMajor = new int[m+2][n+2];
        int[][] preSumMinor = new int[m+2][n+2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                preSumMajor[i+1][j+1] = preSumMajor[i][j] + grid[i][j];
                preSumMinor[i+1][j+1] = preSumMinor[i][j+2] + grid[i][j];
            }
        }
        
        int[] ans = new int[3];
        Arrays.fill(ans, -1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                update(ans, grid[i][j]);
                for (int r = 1; r < limit; ++r) {
                    int xUp = i - r;
                    int xDown = i + r;
                    int yLeft = j - r;
                    int yRight = j + r;
                    if (xUp < 0 || xDown >= m || yLeft < 0 || yRight >= n) {
                        break;
                    }
                    update(ans, getSumPreSum(preSumMajor, preSumMinor, i, j, xUp, xDown, yLeft, yRight));
                } 
            }
        }
        int id = 3;
        while (id > 1 && ans[id - 1] < 0) {
            id--;
        }
        return Arrays.copyOf(ans, id);
    }

    private int getSumPreSum(int[][] preSumMajor, int[][] preSumMinor, int x0, int y0, int xu, int xd, int yl, int yr) {
        int ans = preSumMinor[x0][yl+2] - preSumMinor[xu][y0+2];
        ans += preSumMajor[xd][y0] - preSumMajor[x0][yl];
        ans += preSumMinor[xd+1][y0+1] - preSumMinor[x0+1][yr+1];
        ans += preSumMajor[x0+1][yr+1] - preSumMajor[xu+1][y0+1];
        return ans;
    }

    private void update(int[] ans, int cand) {
        if (cand <= ans[2]) {
            return;
        }

        if (cand > ans[0]) {
            if (ans[0] > ans[1]) {
                if (ans[1] > ans[2]) {
                    ans[2] = ans[1];
                }
                ans[1] = ans[0];
            }
            ans[0] = cand;
        } else if (cand < ans[0] && cand > ans[1]) {
            if (ans[1] > ans[2]) {
                ans[2] = ans[1];
            }
            ans[1] = cand;
        } else if (cand < ans[1]) {
            ans[2] = cand;
        }
    }


    // Brute Force solution
    public int[] getBiggestThreeBF(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        final int limit = Math.min(m, n);

        int[] ans = new int[3];
        Arrays.fill(ans, -1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                update(ans, grid[i][j]);
                for (int r = 1; r < limit; ++r) {
                    int xUp = i - r;
                    int xDown = i + r;
                    int yLeft = j - r;
                    int yRight = j + r;
                    if (xUp < 0 || xDown >= m || yLeft < 0 || yRight >= n) {
                        break;
                    }
                    update(ans, getSumBF(grid, i, j, xUp, xDown, yLeft, yRight));
                } 
            }
        }
        int id = 3;
        while (id > 1 && ans[id - 1] < 0) {
            id--;
        }
        return Arrays.copyOf(ans, id);
    }

    private int getSumBF(int[][] grid, int x0, int y0, int xu, int xd, int yl, int yr) {
        int ans = getSumSideBF(grid, xu, y0, x0, yl, 1, -1);
        ans += getSumSideBF(grid, x0, yl, xd, y0, 1, 1);
        ans += getSumSideBF(grid, xd, y0, x0, yr, -1, 1);
        ans += getSumSideBF(grid, x0, yr, xu, y0, -1, -1);
        return ans;
    }

    private int getSumSideBF(int[][] grid, int xStart, int yStart, int xEnd, int yEnd, int dx, int dy) {
        int ans = 0;
        // the end point is not included so that it would not overlap with another call
        while (xStart != xEnd && yStart != yEnd) {
            ans += grid[xStart][yStart];
            xStart += dx;
            yStart += dy;
        }
        return ans;
    }
}