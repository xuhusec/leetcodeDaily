public class FindtheSmallestDivisorGivenaThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1;
        int hi = 0;
        for (int num : nums) {
            hi = Math.max(hi, num);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (sumDivRes(nums, mid) <= threshold) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int sumDivRes(int[] nums, int divisor) {
        int ans = 0;
        for (int num : nums) {
            ans += num/divisor + (num % divisor == 0 ? 0 : 1);
        }
        return ans;
    }
}