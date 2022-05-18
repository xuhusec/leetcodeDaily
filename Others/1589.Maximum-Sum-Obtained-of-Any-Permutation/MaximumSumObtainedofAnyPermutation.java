public class MaximumSumObtainedofAnyPermutation {
    private static final int MOD = (int) 1e9 + 7;
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        final int n = nums.length;
        long[] counts = new long[n + 1];
        for (int[] req : requests) {
            counts[req[0]]++;
            counts[req[1] + 1]--;
        }

        for (int i = 1; i < counts.length; ++i) {
            counts[i] += counts[i - 1];
        }

        long ans = 0;
        Arrays.sort(counts);
        Arrays.sort(nums);

        for (int i = n - 1; i >= 0; --i) {
            if (counts[i + 1] < 1) {
                break;
            }
            ans = (ans + nums[i] * counts[i+1]) % MOD;
        }
        return (int) ans;
    }
}