public class SpecialArrayWithXElementsGreaterThanorEqualXBucket {
    public int specialArray(int[] nums) {
        int [] cnts = new int[nums.length + 1];
        for (int num : nums) {
            cnts[Math.min(num, nums.length)]++;
        }
        int suffixSum = 0;
        for (int i = cnts.length - 1; i >= 0; --i) {
            suffixSum += cnts[i];
            if (suffixSum == i) {
                return i;
            }
        }
        return -1;
    }
}
