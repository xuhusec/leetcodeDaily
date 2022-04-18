public class SumofMutatedArrayClosesttoTarget {
    public int findBestValue(int[] arr, int target) {
        int lo = 0;
        int hi = 0;
        for (int a : arr) {
            hi = Math.max(hi, a);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            int sum = getMutatedSum(arr, mid);
            if (sum < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        int diff0 = getMutatedSum(arr, lo) - target;
        int diff1 = target - getMutatedSum(arr, lo - 1);
        return diff0 < diff1 ? lo : lo - 1;
    }
    
    private int getMutatedSum(int[] nums, int limit) {
        int ans = 0;
        for (int num : nums) {
            ans += Math.min(num, limit);
        }
        return ans;
    }
}
