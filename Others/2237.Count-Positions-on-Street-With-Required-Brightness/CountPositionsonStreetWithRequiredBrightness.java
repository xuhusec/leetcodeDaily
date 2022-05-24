public class CountPositionsonStreetWithRequiredBrightness {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
        int[] diff = new int[n + 1];

        for (int[] light : lights) {
            int start = Math.max(0, light[0] - light[1]);
            int end = Math.min(n - 1, light[0] + light[1]);

            diff[start]++;
            diff[end + 1]--;
        }

        int ans = 0;
        int brightness = 0;
        for (int i = 0; i < n; ++i) {
            brightness += diff[i];
            if (brightness >= requirement[i]) {
                ans++;
            }
        }
        return ans;
    }
}
