public class TheNumberofWeakCharactersintheGameStack {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]));
        int ans = 0;
        int maxDen = 0;

        for (int[] prop : properties) {
            if (maxDen > prop[1]) {
                ans++;
            } else {
                maxDen = prop[1];
            }
            
        }
        return ans;
    }
}
