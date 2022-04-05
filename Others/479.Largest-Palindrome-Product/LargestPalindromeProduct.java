public class LargestPalindromeProduct {
    private static final int MOD = 1337;
    public int largestPalindrome(int n) {
        long upper = (long) Math.pow(10, n) - 1;
        long lower = (long) Math.pow(10, n - 1);
        for (long half = upper; half > lower; --half) {
            long p = genPalindrom(half, false);
            if (isSat(p, upper, lower)) {
                // 2n case is always greater than 2n - 1 case
                // if it is found, we can return directly
                return (int) (p % MOD);
            }
        } 
        for (long half = upper; half > lower; --half) {
            long p = genPalindrom(half, true);
            if (isSat(p, upper, lower)) {
                return (int) (p % MOD);
            }
        } 
        return -1;
        
    }

    private boolean isSat(long p, long upper, long lower) {
        for (long i = upper ; i * i >= p; --i) {
            if ((p % i) == 0 && p / i >= lower && p / i <= upper ){
                return true;
            }
        }
        return false;
    }

    private long genPalindrom(long half, boolean isOdd) {
        long res = half;
        if (isOdd) {
            half /= 10;
        }

        while (half > 0) {
            res = res * 10 + (half % 10);
            half /= 10;
        }
        return res;
    }
}