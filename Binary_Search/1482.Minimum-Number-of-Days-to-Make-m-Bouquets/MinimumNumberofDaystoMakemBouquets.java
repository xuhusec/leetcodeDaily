public class MinimumNumberofDaystoMakemBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length/k < m) {
            return -1;
        }
        int lo = Integer.MAX_VALUE;
        int hi = 0;
        for (int bd : bloomDay) {
            hi = Math.max(hi, bd);
            lo = Math.min(lo, bd);
        }
        
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (canGetEnoughFlowers(mid, bloomDay, m, k)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean canGetEnoughFlowers(int days, int[] bloomDay, int m, int k) {
        // cnt how many bouquets can be served
        int bouquets = 0;
        // number of flowers can be picked consecutively
        int consecutiveCnt = 0;
        for (int bd : bloomDay) {
            if (bd <= days) {
                // check if we can pick
                if (++consecutiveCnt == k) {
                    // clear for next round
                    consecutiveCnt = 0;
                    // stop early if we get enough
                    if (++bouquets == m) {
                        return true;
                    }
                }
            } else {
                consecutiveCnt = 0;
            }
        }
        return false;
    }
}