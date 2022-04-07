public class LongestRepeatingSubstring {
    private static final int MOD = (int)1e9 + 7;
    private static final int P0 = 31;
    private static final int P1 = 97;
    public int longestRepeatingSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] chars = s.toCharArray();
        // if the max length is m, we would have repeating substring in m - 1, m - 2, .. 1
        // so we can use binary search
        int lo = 0;
        int hi = chars.length - 1;
        while (lo < hi) {
            //if (lo + (hi - lo)/2) is used and lo = 0, hi = 1, mid = 0.
            //when containRepeating(chars, mid) is true; lo = 0 hi = 1. we fall into infity loop
            // so we need hi - (hi - lo)/2
            int mid = hi - (hi - lo)/2;
            if (containRepeating(chars, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private boolean containRepeating(char[] chars, int len) {
        if (len == 0) {
            return true;
        }
        long hash0 = 0;
        long hash1 = 0;

        long highest0 = 1;
        long highest1 = 1;
        // to reduce the chance of collision, the first hash, hash0, is used as key.
        // the second hash, hash1, is used as element in the set. 
        // only when both hash0 and hash1 matches, it would claim a repeating string is found 
        Map<Long, Set<Long>> map = new HashMap<>();
        for (int i = 0; i < chars.length; ++i) {
            int cur = chars[i] - 'a';
            if (i < len) {
                hash0 = (hash0 * P0 + cur) % MOD;
                highest0 = (highest0 * P0) % MOD;

                hash1 = (hash1 * P1 + cur) % MOD;
                highest1 = (highest1 * P1) % MOD;

                if (i == len - 1) {
                    map.computeIfAbsent(hash0, k -> new HashSet<>()).add(hash1);
                }
                continue;
            }
            int toRemove = chars[i - len] - 'a';
            hash0 = (hash0 * P0 + MOD - toRemove * highest0 + cur) % MOD;
            hash1 = (hash1 * P1 + MOD - toRemove * highest1 + cur) % MOD;

            if (!map.computeIfAbsent(hash0, k -> new HashSet<>()).add(hash1)) {
                return true;
            }
        }
        return false;
    }
}