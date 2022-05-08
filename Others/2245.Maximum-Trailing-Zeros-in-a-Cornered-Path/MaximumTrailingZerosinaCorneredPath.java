public class MaximumTrailingZerosinaCorneredPath {
    public int maxTrailingZeros(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;

        Info[][] rowPreSum = new Info[m+1][n+1];
        Info[][] colPreSum = new Info[m+1][n+1];
        Info zero = new Info(0, 0);
        Arrays.fill(colPreSum[0], zero);

        for (int i = 0; i < m; ++i) {
            rowPreSum[i+1][0] = zero;
            for (int j = 0; j < n; ++j) {
                int cnt2 = cntFactor(grid[i][j], 2);
                int cnt5 = cntFactor(grid[i][j], 5);
                Info preElementInRow = rowPreSum[i+1][j];
                rowPreSum[i+1][j+1] = new Info(cnt2 + preElementInRow.cnt2, cnt5 + preElementInRow.cnt5);
                Info preElementInCol = colPreSum[i][j+1];
                colPreSum[i+1][j+1] = new Info(cnt2 + preElementInCol.cnt2, cnt5 + preElementInCol.cnt5);
            }
        }

        Info[] lastCol = colPreSum[m];
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            Info totalInRow = rowPreSum[i+1][n];
            for (int j = 0; j < n; ++j) {
                //walk down and left
                int up2 = colPreSum[i+1][j+1].cnt2;
                int up5 = colPreSum[i+1][j+1].cnt5;
                
                //(i,j) is already in vertical so not cnt them in horizontal 
                int left2 = rowPreSum[i+1][j].cnt2;
                int left5 = rowPreSum[i+1][j].cnt5;
                ans = Math.max(ans, Math.min(up2 + left2, up5 + left5));

                //walk down and right
                int right2 = totalInRow.cnt2 - rowPreSum[i+1][j+1].cnt2;
                int right5 = totalInRow.cnt5 - rowPreSum[i+1][j+1].cnt5;
                ans = Math.max(ans, Math.min(up2 + right2, up5 + right5));

                //walk up and left
                // include the entry (i. j) so that we can reuse left2, left5, right2 and right5
                int down2 = lastCol[j+1].cnt2 - colPreSum[i][j+1].cnt2;
                int down5 = lastCol[j+1].cnt5 - colPreSum[i][j+1].cnt5;
                ans = Math.max(ans, Math.min(down2 + left2, down5 + left5));

                //walk up and right
                ans = Math.max(ans, Math.min(down2 + right2, down5 + right5));

            }
        }
        return ans;
    }

    private int cntFactor(int n, int factor) {
        int ans = 0;
        while (n % factor == 0) {
            ans++;
            n /= factor;
        }
        return ans;
    }

    private static class Info {
        private final int cnt2;
        private final int cnt5;
        public Info(int cnt2, int cnt5) {
            this.cnt2 = cnt2;
            this.cnt5 = cnt5;
        }
    }
}