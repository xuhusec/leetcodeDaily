public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k+2][n];
        for (int i = 0; i < k+2; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE/2);
        }
        
        dp[0][src] = 0;
        int res = Integer.MAX_VALUE/2;
        for (int i = 1 ; i <= k + 1; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = dp[i-1][j];
            }
            for (int[] f : flights) {
                int s = f[0];
                int d = f[1];
                int p = f[2];
                dp[i][d] = Math.min(dp[i][d], dp[i-1][s] + p);
            }
        }
        return dp[k+1][dst] >= Integer.MAX_VALUE/2 ? -1 : dp[k+1][dst];
    }
}