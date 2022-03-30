public class MaximumValueofKCoinsFromPilesDP {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] dp = new int[piles.size() + 1][k + 1];
        for (int i = 1; i <= piles.size(); ++i) {
            List<Integer> pile = piles.get(i - 1);
            int[] preSum = new int[Math.min(k + 1, pile.size() + 1)];
            for (int j = 1; j < preSum.length; ++j) {
                preSum[j] = preSum[j - 1] + pile.get(j - 1); 
            }

            for (int j = 1; j <= k; ++j) {
                for (int z = 0; z < Math.min(j + 1, preSum.length); ++z) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-z] + preSum[z]);
                }
            }
        }
        return dp[piles.size()][k];
    }
}
