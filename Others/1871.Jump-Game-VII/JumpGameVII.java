public class JumpGameVII {
    public boolean canReach(String s, int minJump, int maxJump) {
        final int n = s.length();
        if (s.charAt(n - 1) != '0') {
            return false;
        }
        // we need additional one because we need stop at maxJump + 1
        int[] diff = new int[n + 1];

        diff[minJump]++;
        diff[Math.min(maxJump + 1, n)]--;
        int sum = 0;
        // start from 1 because there is no condition at 0.
        for (int i = 1; i < n; ++i) {
            sum += diff[i];
            if (sum == 0 || s.charAt(i) == '1') {
                continue;
            }

            if (i == n - 1) {
                return true;
            }

            if (i + minJump < n) {
                diff[i + minJump]++;
                diff[Math.min(i + maxJump + 1, n)]--;
            }
        }

        return false;
    }
}