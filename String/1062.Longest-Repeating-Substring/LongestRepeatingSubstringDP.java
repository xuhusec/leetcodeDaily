public class LongestRepeatingSubstringDP {
    public int longestRepeatingSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[][] dp = new int[chars.length+1][chars.length+1];
        int res = 0;
        for (int i = 0; i < chars.length; ++i) {
            char cur = chars[i];
            for (int j = 0; j < chars.length; ++j) {
                if (i != j && cur == chars[j]) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                    res = Math.max(res, dp[i+1][j+1]);
                }
            }
        }
        return res;
    }
}