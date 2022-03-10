class MinimumNumberofMovestoMakePalindrome {
    public int minMovesToMakePalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        
        int res = 0;
        while (i < j) {
            char ch = chars[i];
            int k = j;
            while (k >= i && chars[k] != ch) {
                k--;
            }
            // find the odd characters (no pairs should be put in the middle)
            // it should be moved after the rest string is palindrome. 
            // Otherwise, it would adding cost when a character is moving from one side from the center to the other side
            if (k == i) {
                res += (j - i)/2;
                i++;
                continue;
            }
            // find a pair move to the right
            while (k < j) {
                res++;
                swap(chars, k, k + 1);
                k++;
            }
            i++;
            j--;
        }
        return res;
    }
    
    private void swap(char[] chars, int i, int j) {
        var ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }
}