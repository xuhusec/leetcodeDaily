public class MinimumNumberofKConsecutiveBitFlips {
    public int minKBitFlips(int[] nums, int k) {
        final int n = nums.length;
        int[] diff = new int[n + 1];

        int ans = 0;
        int flips = 0;
        for (int i = 0; i < n; ++i) {
            flips += diff[i];
            int cur = nums[i] ^ (flips % 2);
            if (cur == 1) {
                continue;
            }
            if (i + k - 1 >= n) {
                return -1;
            }
            ans++;
            flips++;
            diff[i + k]--;
        }
        return ans;
    }
}
