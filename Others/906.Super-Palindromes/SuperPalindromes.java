public class SuperPalindromes {
    public int superpalindromesInRange(String left, String right) {
        final long lower = Long.parseLong(left);
        final long upper = Long.parseLong(right);

        long half = (long) Math.pow(10, (left.length() - 1) / 4);
        long next = half * 10;
        int cnt = 0;
        while (genPalinSquare(half, true) <= upper) {
            cnt += dfs(half, next, lower, upper, true);
            cnt += dfs(half, next, lower, upper, false);
            half = next;
            next *= 10;
        }
        return cnt;
    }

    private long genPalinSquare(long half, boolean isOdd) {
        long root = half;
        if (isOdd) {
            half /= 10;
        }

        while (half > 0) {
           root = root * 10 + (half % 10);
           half /= 10;
        }
        return root * root;
    }
    
    private int dfs(long start, final long limit, long lower, long upper, final boolean isOdd) {
        int cnt = 0;
        for (long i = start; i < limit; ++i) {
            long square = genPalinSquare(i, isOdd);
            if (square > upper) {
                break;
            }
            if (square < lower) {
                continue;
            }
            if (isPalindrome(square)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isPalindrome(long square) {
        long reverse = 0;
        long temp = square;
        while (temp > 0) {
            reverse = reverse * 10 + (temp % 10);
            temp /= 10;
        }
        return reverse == square;
    }
}
