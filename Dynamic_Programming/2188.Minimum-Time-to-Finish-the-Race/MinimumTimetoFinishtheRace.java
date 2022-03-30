public class MinimumTimetoFinishtheRace {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int[][] newTires = filterTire(tires);
        long[] minTime = getMiTime(newTires, changeTime, numLaps);

        long[] dp = new long[numLaps + 1];
        for (int i = 1 ; i <= numLaps; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= Math.min(i, minTime.length); ++j) {
                dp[i] = Math.min(dp[i], minTime[j-1] + (j == i ? 0 : changeTime) + dp[i - j]);
            }
        }
        return (int) dp[numLaps];

    }

    //If one tire with higher f and higher r than another trie, we would always choose the other tire over this one.
    // so we can remove those tire
    private int[][] filterTire(int[][] tires) {
        Arrays.sort(tires, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int[][] ans = new int[tires.length][];
        int id = 0;
        for (int[] tire : tires) {
            if (id > 0 && ans[id - 1][0] <= tire[0]) {
                continue;
            }
            ans[id++] = tire;
        }
        return Arrays.copyOf(ans, id);
    }

    private long[] getMiTime(int[][] tires, int changeTime, int numLaps) {
        int n = 0;
        for (int[] tire : tires) {
            int f = tire[0];
            int r = tire[1];
            n = Math.max(n, 1 + (int) (Math.log((changeTime + (double)f)/f)/Math.log(r)));
        }
        n = Math.min(n, numLaps);
        long[] ans = new long[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (int[] tire : tires) {
            int f = tire[0];
            int r = tire[1];
            long sum = f;
            ans[0] = Math.min(ans[0], f);
            long cur = f;
            for (int i = 1; i < n; ++i) {
                cur *= r;
                if (cur > changeTime + f) {
                    break;
                }
                sum += cur;
                ans[i] = Math.min(ans[i], sum);
            }
        }
        return ans;
    }
}