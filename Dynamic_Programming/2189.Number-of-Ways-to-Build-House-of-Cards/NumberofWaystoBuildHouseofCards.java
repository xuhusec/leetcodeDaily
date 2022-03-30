public class NumberofWaystoBuildHouseofCards {
    public int houseOfCards(int n) {
        int[] cardNeeded = new int[(n + 1)/3];
        for (int i = 0; i < cardNeeded.length; ++i) {
            cardNeeded[i] = 3 * (i + 1) - 1;
        }

        int[][] dp = new int[cardNeeded.length + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= cardNeeded.length; ++i) {
            int cardInFirstRow = cardNeeded[i-1];
            for (int j = 0; j <= n; ++j) {
                // not pick cardNeeded[i] as first row
                dp[i][j] = dp[i-1][j];
                if (j >= cardInFirstRow) {
                    dp[i][j] += dp[i-1][j - cardInFirstRow];
                } 
            }
        }

        return dp[cardNeeded.length][n];
    }
}