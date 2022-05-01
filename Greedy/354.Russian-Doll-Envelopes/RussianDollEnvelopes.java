public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] lis = new int[envelopes.length];
        int id = 0;
        for (int[] envelope : envelopes) {
            int pos = Arrays.binarySearch(lis, 0, id, envelope[1]);
            if (pos >= 0) {
                continue;
            }
            pos = -pos - 1;
            if (pos == id) {
                id++;
            }
            lis[pos] = envelope[1];
        }
        return id;
    }
}
