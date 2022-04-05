public class PrimePalindrome {
    private final int LIMIT = 20000;
    public int primePalindrome(int n) {
        if (n >= 8 && n <= 11) {
            return 11;
        }

        for (int i = genStart(n); i < LIMIT; ++i) {
            int p = genPan(i);
            if (p >= n && isPrime(p)) {
                return p;
            }
        }
        return -1;
    }
    
    private int genStart(int num) {
        int n = new StringBuilder().append(num).length();
        return (int) Math.pow(10, n/2);
    }
    private int genPan(int num) {
        int res = num;
        // odd only
        num /= 10;
        while (num > 0) {
            res = res * 10 + (num % 10);
            num /= 10;
        }
        return res;
    }

    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
