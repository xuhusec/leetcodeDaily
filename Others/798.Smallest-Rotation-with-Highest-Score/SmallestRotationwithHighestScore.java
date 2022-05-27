public class SmallestRotationwithHighestScore {
    public int bestRotation(int[] nums) {
        final int n = nums.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= i) {
                diff[0]++;
                diff[i - (nums[i] - 1)]--;
                diff[i + 1]++;
            } else {
                diff[i + 1]++;
                diff[i + 1 + n - nums[i]]--;
            }
        }

        int ans = -1;
        int max = -1;
        int sum = 0;

        for (int i = 0; i < n; ++i) {
            sum += diff[i];
            if (sum > max) {
                max = sum;
                ans = i;
            }
        }
        return ans;
    }
}
