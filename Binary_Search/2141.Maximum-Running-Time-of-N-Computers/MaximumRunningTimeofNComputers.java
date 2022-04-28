public class MaximumRunningTimeofNComputers {
    public long maxRunTime(int n, int[] batteries) {
        long lo = 0;
        long hi = 0;
        for (int battery : batteries) {
            hi += battery;
        }
        hi /= n;

        while (lo < hi) {
            long mid = hi - (hi - lo)/2;
            if (isPossible(n, mid, batteries)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean isPossible(int n, long time, int[] batteries) {
        long total = 0;
        for (int battery : batteries) {
            total += Math.min(time, battery);
        }
        return total >= n * time;
    }
}