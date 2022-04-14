public class MaximumCandiesAllocatedtoKChildren {
    public int maximumCandies(int[] candies, long k) {
        int lo = 0;
        int hi = 0;
        for (int candy : candies) {
            hi = Math.max(candy, hi);
        }

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (dividePiles(candies, mid) >= k) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private long dividePiles(int[] candies, int unit) {
        if (unit == 0) {
            return Long.MAX_VALUE;
        }
        long ans = 0;
        for (int candy : candies) {
            ans += candy/unit;
        }
        return ans;
    }
}
