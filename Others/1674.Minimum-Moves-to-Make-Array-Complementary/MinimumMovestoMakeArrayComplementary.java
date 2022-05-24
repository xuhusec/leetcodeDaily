public class MinimumMovestoMakeArrayComplementary {
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * limit + 2];
        final int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            int lo = nums[i];
            int hi = nums[n - i - 1];
            if (lo > hi) {
                var temp = lo;
                lo = hi;
                hi = temp;
            }

            diff[2] += 2;
            diff[lo + 1]--;
            diff[lo + hi]--;
            diff[lo + hi + 1]++;
            diff[limit + hi + 1]++;
        }

        int ans = Integer.MAX_VALUE;
        int moves = 0;
        for (int i = 2; i < diff.length; ++i) {
            moves += diff[i];
            ans = Math.min(moves, ans);
        }

        return ans;
    }
}