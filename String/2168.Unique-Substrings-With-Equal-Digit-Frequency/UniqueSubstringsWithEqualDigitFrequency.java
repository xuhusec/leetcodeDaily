public class UniqueSubstringsWithEqualDigitFrequency {
    public int equalDigitFrequency(String s) {
        int[] cnts = new int[10];
        Set<String> validStrings = new HashSet<>();
        char[] chars = s.toCharArray();
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; ++i) {
            Arrays.fill(cnts, 0);
            sb.setLength(0);
            max = 0;
            for (int j = i; j < chars.length; ++j) {
                sb.append(chars[j]);
                int id = chars[j] - '0';
                if (++cnts[id] < max) {
                    continue;
                }
                max = cnts[id];
                if (isValid(cnts, max)) {
                    validStrings.add(sb.toString());
                }
            }
        }
        return validStrings.size();
    }

    private boolean isValid(int[] cnts, int max) {
        for (int cnt : cnts) {
            if (cnt == 0) {
                continue;
            }
            if (cnt != max) {
                return false;
            }
        }
        return true;
    }

    public int equalDigitFrequencyEncoding(String s) {
        int[] cnts = new int[10];
        Set<Integer> validEncodings = new HashSet<>();
        char[] chars = s.toCharArray();
        int max = 0;
        int encoding = 0;
        for (int i = 0; i < chars.length; ++i) {
            Arrays.fill(cnts, 0);
            encoding = 0;
            max = 0;
            for (int j = i; j < chars.length; ++j) {
                int id = chars[j] - '0';
                encoding = encoding * 11 + id + 1;
                if (++cnts[id] < max) {
                    continue;
                }
                max = cnts[id];
                if (isValid(cnts, max)) {
                    validEncodings.add(encoding);
                }
            }
        }
        return validEncodings.size();
    }
}
