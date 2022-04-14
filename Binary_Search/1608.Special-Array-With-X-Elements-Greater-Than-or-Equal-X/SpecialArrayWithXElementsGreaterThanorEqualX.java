public class SpecialArrayWithXElementsGreaterThanorEqualX {
    public int specialArray(int[] nums) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (cntGreaterAndEquals(nums, mid) >= mid) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return cntGreaterAndEquals(nums, lo) == lo ? lo : -1;
    }

    private int cntGreaterAndEquals(int[] nums, int min) {
        int ans = 0;
        for (int num : nums) {
            ans += num >= min ? 1 : 0;
        }
        return ans;
    }
    /*
       another template for binary search especially suits for at most one solution. 
       Note it is while (lo <= hi) instead of while (lo < hi). If there is any solution it must be found in the loop 
    */
    public int specialArray2(int[] nums) {
        int lo = 0;
        int hi = nums.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cnt = cntGreaterAndEquals(nums, mid);
            if (cnt > mid) {
                lo = mid + 1;
            } else if (cnt < mid) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
