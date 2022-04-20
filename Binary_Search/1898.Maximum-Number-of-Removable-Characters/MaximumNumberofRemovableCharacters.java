public class MaximumNumberofRemovableCharacters {
    public int maximumRemovals(String s, String p, int[] removable) {
        int[] removeOrder = new int[s.length()];
        Arrays.fill(removeOrder, Integer.MAX_VALUE);
        for (int i = 0; i < removable.length; ++i) {
            removeOrder[removable[i]] = i;
        }

        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();

        int lo = 0;
        // as removable is unique, s.length() - removed <= p.length()
        int hi = Math.min(s.length() - p.length(), removable.length);

        while (lo < hi) {
            int mid = hi - (hi - lo)/2;
            if (isSubStringAfterRemoval(charS, charP, removeOrder, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private boolean isSubStringAfterRemoval(char[] charS, char[] charP, int[] removeOrder, int removed) {
        int idP = 0;
        for (int i = 0; i < charS.length && idP < charP.length; ++i) {
            if (removeOrder[i] < removed) {
                continue;
            }
            if (charS[i] == charP[idP]) {
                idP++;
            }
        }
        return idP == charP.length;
    }
}