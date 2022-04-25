public class UglyNumberIII {
    public int nthUglyNumber(int n, int a, int b, int c) {
        // preprocess lcm(a, b), lcm(b, c) lcm (a, c) and lcm (a, b, c) 
        long[][] divisor = genDivisor(a, b, c);
        int lo = 1;
        int hi = Integer.MAX_VALUE;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (count(mid, divisor) >= n) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    // cnt = m/a + m/b + m/c - m/ lcm(a,b) - m / lcm(b, c) - m /lcm(a, c) + m / lcm(a, b, c) 
    private long count(int boundary, long[][] divisor) {
        long ans = 0;
        for (int i = 0; i < divisor.length; ++i) {
            boolean isNeg = i % 2 != 0;
            for (long d : divisor[i]) {
                ans += isNeg ? -boundary / d : boundary / d;
            }
        }
        return ans;
    }

    private long[][] genDivisor(int a, int b, int c) {
        long[][] ans = new long[3][];
        // remove duplicates
        Set<Integer> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        ans[0] = new long[set.size()];
        int id = 0;
        for (int num : set) {
            ans[0][id++] = num;
        }

        if (ans[0].length > 2) {
            //three factors ans[1] = {lcm(a, b), lcm(b, c), lcm{a, c}}, ans[2] = {lcm(a, b, c)}
            ans[1] = new long[]{lcm(ans[0][0], ans[0][1]), lcm(ans[0][1], ans[0][2]), lcm(ans[0][0], ans[0][2])};
            ans[2] = new long[]{lcm(lcm(ans[0][0], ans[0][1]),ans[0][2])};
        } else if (ans[0].length > 1) {
            // two factors ans[1] = {lcm(a, b)}, ans[2] = {}
            ans[1] = new long[]{lcm(ans[0][0], ans[0][1])};
            ans[2] = new long[0];
        } else {
            // only factors ans[1] = {}, ans[2] = {}
            ans[1] = new long[0];
            ans[2] = new long[0];
        }
        return ans;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }
}