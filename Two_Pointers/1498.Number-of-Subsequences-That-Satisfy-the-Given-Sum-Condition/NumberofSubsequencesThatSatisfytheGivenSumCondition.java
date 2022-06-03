public class NumberofSubsequencesThatSatisfytheGivenSumCondition {
    private static final int MOD = (int)1e9 + 7;
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);

        int[] power = genPowerOfTwo(nums.length);

        int ans = 0;
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            if (nums[start] + nums[end] > target) {
                end--;
            } else {
                ans = (ans + power[end - start]) % MOD;
                start++;
            }
        }
        return ans;
    }

    private int[] genPowerOfTwo(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;

        for (int i = 1; i <= n; ++i) {
            ans[i] = (2 * ans[i - 1]) % MOD;
        }
        return ans;
    }
}