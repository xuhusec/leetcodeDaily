public class SumofkMirrorNumbers {
    public long kMirror(int k, int n) {
        long ans = 0;
        long start = 1;
        while (n > 0) {
            for (int i = 0; i < 2 && n > 0; ++i) {
                final boolean isOdd = i == 0;
                for (long j = start; j < start * 10; ++j) {
                    long candidate = genPalindrome(j, isOdd);
                    if (isKPalindrome(candidate, k)) {
                        ans += candidate;
                        if (--n == 0) {
                            break;
                        }
                    }
                }
            }
            start *= 10;
        }
        return ans;
    }

    private long genPalindrome(long half, final boolean isOdd) {
        long ans = half;
        if (isOdd) {
            half /= 10;
        }
        while (half > 0) {
            ans = ans * 10 + (half % 10);
            half /= 10;
        }
        return ans;
    }

    private boolean isKPalindrome(long n, int k) {
        long reversed = 0;
        long temp = n;
        while (temp > 0) {
            reversed = reversed * k + (temp % k);
            temp /= k;
        }
        return reversed == n;
    }
}
