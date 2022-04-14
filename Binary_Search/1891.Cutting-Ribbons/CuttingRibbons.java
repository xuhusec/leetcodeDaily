public class CuttingRibbons {
    public int maxLength(int[] ribbons, int k) {
        int lo = 0;
        int hi = Integer.MIN_VALUE;
        
        for (int r : ribbons) {
            hi = Math.max(hi, r);
        }
        
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
