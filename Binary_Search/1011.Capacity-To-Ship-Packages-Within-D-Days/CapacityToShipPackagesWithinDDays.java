public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        int lo = 0;
        int hi = 0;
        for (int w : weights) {
            hi += w;
            lo = Math.max(lo, w);
        }
        
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (spendDays(weights, mid) <= days) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    
    private int spendDays(int[] weights, int max) {
        int cnt = 0;
        int cur = 0;
        for (int w : weights) {
            // // we start lo from max m, so it won't hit the comment check
            // // but if you start from 1. Please enable this
            // if (w > max) {
            //     return Integer.MAX_VALUE;
            // }
            if (cur + w > max) {
                cur = 0;
                cnt++;
            }
            cur += w;
        }
        return cnt + (cur > 0 ? 1 : 0);
    }
}
