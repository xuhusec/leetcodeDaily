public class SumofScoresofBuiltStrings {
    private static final int MOD = (int) 1e9 + 7;
    private static final int P0 = 31;
    private static final int P1 = 97;
    public long sumScores(String s) {
        char[] chars = s.toCharArray();
        long[] hash0 = new long[chars.length];
        long[] highest0 = new long[chars.length];

        long[] hash1 = new long[chars.length];
        long[] highest1 = new long[chars.length];

        preprocess(chars, hash0, highest0, P0, MOD);
        preprocess(chars, hash1, highest1, P1, MOD);
        
        // s == sn so we have s.length() anyway.
        long ans = chars.length;
        for (int i = 1; i < chars.length; ++i) {
            ans += commonPrefixLen(chars.length, i, hash0, highest0, hash1, highest1);
        }
        return ans;

    }

    private void preprocess(char[] chars, long[] hash, long[] highest, final int P, final int M) {
        highest[0] = P % M;
        hash[0] = (chars[0] - 'a') % M;
        for (int i = 1; i < chars.length; ++i) {
            highest[i] = (highest[i-1] * P) % M;
            hash[i] = (hash[i-1] * P + (chars[i] - 'a')) % M;
        }
    }

    private int commonPrefixLen(int len, int start, long[] hash0, long[] highest0, long[] hash1, long[] highest1) {
        int lo = 0;
        int hi = len - start;

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (isCommonPrefix(start, mid, hash0, highest0, hash1, highest1)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean isCommonPrefix(int start, int len, long[] hash0, long[] highest0, long[] hash1, long[] highest1) {
        /*
         for rolling hash abcdef
         hash("abcdef") = hash("abc") * P^(len(abcdef) - len(abc)) + hash(def);
         hash("def") = hash("abcdef") - hash("abc") * P^(len(abcdef) 
         it likes a prefix array
         */
        long cand = ((hash0[start + len - 1] - hash0[start - 1] * highest0[len - 1])% MOD + MOD ) % MOD; 
        if (cand != hash0[len - 1]) {
            return false;
        }

        cand = ((hash1[start + len - 1] - hash1[start - 1] * highest1[len - 1])% MOD + MOD ) % MOD; 
        return cand == hash1[len - 1];
    }
}
