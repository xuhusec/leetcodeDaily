public class NetworkDelayTimeFW {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE/2);
        }

        for (int[] time : times) {
            // id start from 1. map to 0 indexed
            dp[time[0] - 1][time[1] - 1] = time[2];
        }
        
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 0;
        }
        
        for (int m = 0; m < n; ++m) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m][j]);
                }
            }
        }

        int res = 0;
        
        for (int i = 0; i < n; ++i) {
            //again map to 0 indexed
            if (dp[k-1][i] == Integer.MAX_VALUE/2) {
                return -1;
            } 
            res = Math.max(dp[k-1][i], res);
        }
        return res;
    }
}