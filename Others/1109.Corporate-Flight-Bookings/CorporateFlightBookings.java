public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        // record diff
        for (int[] booking : bookings) {
            // the stop is recorded as 1 to n. So booking[0] - 1 is the index in 0-indexed array
            ans[booking[0] - 1] += booking[2];
            // does not record for n stop because n + 1 does not exists and it is always 0 in the end.
            if (booking[1] < n) {
                ans[booking[1]] -= booking[2];
            }
        }

        // presum array
        for (int i = 1; i < ans.length; ++i) {
            ans[i] += ans[i - 1];
        }
        return ans;
    }
}
