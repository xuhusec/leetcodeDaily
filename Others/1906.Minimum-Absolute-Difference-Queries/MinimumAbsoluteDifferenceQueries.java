public class MinimumAbsoluteDifferenceQueries {
    public int[] minDifference(int[] nums, int[][] queries) {
        int[][] presum = new int[nums.length + 1][];
        presum[0] = new int[101];
        
        for (int i = 0; i < nums.length; ++i) {
            presum[i+1] = Arrays.copyOf(presum[i], presum[i].length);
            presum[i+1][nums[i]]++;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            ans[i] = getAnswer(presum, queries[i]);
        }
        return ans;
    }

    private int getAnswer(int[][] presum, int[] query) {
        // the diff of left and right is the element shown in the query range
        int[] left = presum[query[0]];
        int[] right = presum[query[1] + 1];
        int ans = Integer.MAX_VALUE;
        int prev = -1;
        for (int i = 1; i < left.length; ++i) {
            // check if i is shown in the range
            if (right[i] > left[i]) {
                if (prev > -1) {
                    ans = Math.min(i - prev, ans);
                }
                prev = i;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}