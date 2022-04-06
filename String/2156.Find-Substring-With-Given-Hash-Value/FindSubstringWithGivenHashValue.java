public class FindSubstringWithGivenHashValue {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        final int N = s.length();
        final int DIFF = 'a' - 1;
        long hash = 0;
        long highest = 1;

        for (int i = N - 1; i >= N - k; --i) {
            hash = (hash * power + (s.charAt(i) - DIFF)) % modulo;
            highest = (highest * power) % modulo; 
        }
        int start = hash == hashValue ? N - k : -1;

        for (int i = N - k - 1; i >= 0; --i) {
            hash = (hash * power - (s.charAt(i + k) - DIFF) * highest + (s.charAt(i) - DIFF)) % modulo;
            if (hash < 0) {
                hash += modulo;
            }
            if (hash == hashValue) {
                start = i;
            }
        }


        return start > -1 ? s.substring(start, start + k) : "";
    }
}