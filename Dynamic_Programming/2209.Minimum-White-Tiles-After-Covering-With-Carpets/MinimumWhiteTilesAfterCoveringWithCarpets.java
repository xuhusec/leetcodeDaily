public class MinimumWhiteTilesAfterCoveringWithCarpets {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        final int N = floor.length();
        if (N <= numCarpets * carpetLen) {
            return 0;
        }

        int[][] dp = new int[N+1][numCarpets+1];
        for (int i = 1; i <= N; ++i) {
            int newTile = floor.charAt(i-1) - '0';
            dp[i][0] = dp[i-1][0] + newTile;
            if (i <= carpetLen) {
                continue;
            }
            for (int j = 1; j <= numCarpets; ++j) {
                dp[i][j] = Math.min(dp[i-carpetLen][j-1], dp[i-1][j] + newTile);
            }
        }
        return dp[N][numCarpets];
    }
}