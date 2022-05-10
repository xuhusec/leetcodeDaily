public class MergeIntervalsSort {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        int[][] ans = new int[intervals.length][];
        int id = 0;
        for (int i = 1; i < intervals.length; ++i) {
            int[] interval = intervals[i];
            if (interval[0] <= end) {
                end = Math.max(end, interval[1]);
            } else {
                ans[id++] = new int[]{start, end};
                start = interval[0];
                end = interval[1];
            }
        }
        ans[id++] = new int[]{start, end};
        return Arrays.copyOf(ans, id);
    }
}
