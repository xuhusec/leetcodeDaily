public class CuttingRibbons {
    public int maxLength(int[] ribbons, int k) {
        int lo = Integer.MAX_VALUE;
        int hi = Integer.MIN_VALUE;
        
        for (int r : ribbons) {
            lo = Math.min(lo, r);
            hi = Math.max(hi, r);
        }
        
        lo = 0;
        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (getRibbons(ribbons, mid) >= k) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
        
    }
    
    private int getRibbons(int[] ribbons, int targetLen) {
        int res = 0;
        for (int r : ribbons) {
            res += r / targetLen;
        }
        return res;
    }
}
