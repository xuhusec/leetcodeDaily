public class LongestCommonSubpath {
    private static final int MOD = (int) 1e9 + 7;
    private static final int P0 = 31;
    private static final int P1 = 97;
    public int longestCommonSubpath(int n, int[][] paths) {
        int[] min = paths[0];
        for (int i = 1; i < paths.length; ++i) {
            if (min.length > paths[i].length) {
                min = paths[i];
            }
        }
        
        int left = 0;
        int right = min.length;
        
        
        while (left < right) {
            int mid = right - (right - left)/2;
            if (isPossible(paths, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private boolean isPossible(int[][] paths, int len) {
        if (len == 0) {
            return true;
        }
        Map<Info, Integer> cntMap = new HashMap<>();
        long highest0 = 1;
        long highest1 = 1;
        
        for (int i = 0; i < len; ++i) {
            highest0 = (highest0 * P0) % MOD;
            highest1 = (highest1 * P1) % MOD;
        }
        
        for (int[] path : paths) {
            long hash0 = 0;
            long hash1 = 0;
            // for each path only count the hash only once
            Set<Info> set = new HashSet<>();
            for (int i = 0; i < path.length; ++i) {
                if (i < len) {
                    hash0 = (hash0 * P0 + path[i]) % MOD;
                    hash1 = (hash1 * P1 + path[i]) % MOD;
                    if (i == len - 1) {
                        Info info = new Info(hash0, hash1);
                        if (set.add(info)) {
                            cntMap.merge(info, 1 ,(v1, v2) -> v1 + v2);
                        }
                    }
                
                } else {
                    hash0 = ((hash0 * P0 - path[i - len] * highest0 + path[i]) % MOD  + MOD) % MOD;
                    hash1 = ((hash1 * P1 - path[i - len] * highest1 + path[i]) % MOD  + MOD) % MOD;
                    Info info = new Info(hash0, hash1);
                    if (set.add(info)) {
                        cntMap.merge(info, 1 ,(v1, v2) -> v1 + v2);
                    }
                }
            }
        }
        // if we found candidates shows in all paths, we found the common subpath 
        for (int val : cntMap.values()) {
            if (val == paths.length) {
                return true;
            }
        }
        return false;
    }
    // a helper class to combine two hashes into one so that we can use them as key in the HashMap
    private static class Info {
        // hash is mod by 1e9 + 7 so it is always an integer. we use long is for hash0 * P0  and path[i - len] * highest0
        // to prevent overflow
        private int hash0;
        private int hash1;
        
        public Info(long hash0, long hash1) {
            this.hash0 = (int) hash0;
            this.hash1 = (int) hash1;
        }
        
        @Override
        public int hashCode() {
            return hash0 + 31 * hash1;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Info)) {
                return false;
            }
            Info i = (Info) o;
            return this.hash0 == i.hash0 && this.hash1 == i.hash1;
        }
        
    }
}
