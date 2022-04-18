public class MaximumSideLengthofaSquarewithSumLessthanorEqualtoThreshold {
    public int maxSideLength(int[][] mat, int threshold) {
        int[][] preSum = genPreSum(mat);

        int lo = 0;
        int hi = Math.min(mat.length, mat[0].length);

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (containsLessThanOrEqualSum(preSum, mid, threshold)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private int[][] genPreSum(int[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        int[][] preSum = new int[M+1][N+1];
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                preSum[i+1][j+1] = preSum[i][j+1] + preSum[i+1][j] - preSum[i][j] + mat[i][j];
            }
        }
        return preSum;
    }

    private boolean containsLessThanOrEqualSum(int[][] preSum, final int len, final int threshold) {
        if (len == 0) {
            return true; 
        }
        final int M = preSum.length;
        final int N = preSum[0].length;

        for (int i = 0; i < M - len; ++i) {
            for (int j = 0; j < N - len; ++j) {
                if (preSum[i+len][j+len] - preSum[i][j+len] - preSum[i+len][j] + preSum[i][j] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}