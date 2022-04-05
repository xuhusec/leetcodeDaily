public class FindPalindromeWithFixedLength {
    public long[] kthPalindrome(int[] queries, int intLength) {
        final int N = queries.length;
        long[] ans = new long[N];
        final boolean isOdd = (intLength % 2 != 0);
        long start = (long) Math.pow(10, (intLength - 1) / 2);
        for (int i = 0; i < N; ++i) {
            ans[i] = getPalindrome(start, queries[i], isOdd);
        }
        return ans;
    }

    private long getPalindrome(long base, int pos, boolean isOdd) {
        long half = base + pos - 1;
        if (half >= base * 10) {
            return -1;
        }

        long ans = half;
        // if odd we should skip the last digit when construct the flip part
        if (isOdd) {
            half /= 10;
        }
        // append the flip part
        while (half > 0) {
            ans = ans * 10 + (half % 10);
            half /= 10;
        }
        
        return ans;
    }
}
