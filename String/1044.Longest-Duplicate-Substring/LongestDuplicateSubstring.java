public class LongestDuplicateSubstring {
    private static final int P0 = 31;
    private static final int P1 = 97;
    public String longestDupSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        // if the max length is m, we would have repeating substring in m - 1, m - 2, .. 1
        // so we can use binary search
        int lo = 0;
        int hi = chars.length - 1;
        String ans = "";
        while (lo < hi) {
            //if (lo + (hi - lo)/2) is used and lo = 0, hi = 1, mid = 0.
            //when getDupString can get string; lo = 0 hi = 1. we fall into infity loop
            // so we need hi - (hi - lo)/2
            int mid = hi - (hi - lo)/2;
            String cand = getDupString(chars, mid, s);
            if (cand != null) {
                lo = mid;
                ans = cand;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private String getDupString(char[] chars, int len, String s) {
        if (len == 0) {
            return "";
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
            // we need make sure hash0 and hash1 is positive so we have ((x) % M + M) % M. 
            // Otherwise, the hashmap and set can map the same value differently one positive and one negative
            hash0 = ((hash0 * P0  - toRemove * highest0 + cur) % MOD + MOD) % MOD;
            hash1 = ((hash1 * P1 + MOD - toRemove * highest1 + cur) % MOD + MOD) % MOD;

            if (!map.computeIfAbsent(hash0, k -> new HashSet<>()).add(hash1)) {
                return s.substring(i - len + 1, i + 1);
            }
        }
        return null;
    }
}