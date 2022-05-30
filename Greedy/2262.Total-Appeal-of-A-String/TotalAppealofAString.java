public class TotalAppealofAString {
    public long appealSum(String s) {
        int[] lastPos = new int[26];
        Arrays.fill(lastPos, -1);
        long ans = 0;
        final int n = s.length();
        for (int i = 0; i < n; ++i) {
            int id = s.charAt(i) - 'a';
            int last =  lastPos[id];
            ans += (i - last) * (n - i);
            lastPos[id] = i;
        }
        return ans;
    }
}