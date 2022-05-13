public class CheckifAlltheIntegersinaRangeAreCovered {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }

        int[] sum = new int[52];
        for (int i = 1; i < sum.length; ++i) {
            sum[i] = sum[i-1] + diff[i];
        }

        for (int i = left; i <= right; ++i) {
            if (sum[i] == 0) {
                return false;
            }
        }
        return true;
    }
}
