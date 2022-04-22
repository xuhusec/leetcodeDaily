public class MaximumValueataGivenIndexinaBoundedArray {
    public int maxValue(int n, int index, int maxSum) {
        int lo = 1;
        int hi = maxSum;

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (miniSum(n, index, mid) <= maxSum) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private long miniSum(int n, int index, int val) {
        // assign val to ans to avoid compute multiple times
        long ans = val;
        // compute the left part. There should index elements (due to 0 indexed for 1 indexed it should be index - 1)
        // the max value is val - 1
        ans += slopSum(index, val - 1);
        // compute the right part
        // there should be n - index elements include the max so the right part would be n - index - 1 elements
        // the max value is val - 1
        ans += slopSum(n - index - 1, val - 1);
        return ans;
    }

    private long slopSum(long cnt, long max) {
        // if cnt < max, it won't reach 1. the minimum is max - cnt + 1
        // otherwise, the minimum is 1 and after it reaches 1, all the rest would be one i.e. cnt - max
        return cnt < max ? ((max + max - cnt + 1) * cnt / 2) :((max + 1) * max /2 + cnt - max);
    }
}