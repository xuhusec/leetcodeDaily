### [2081. Sum of k-Mirror Numbers](https://leetcode.com/problems/sum-of-k-mirror-numbers/)

#### Solution 1: Brute Force

The question asks fro the sum of first n k-Mirror numbers (palindrome in both 10-base and k-base). We can search from either 10-base or k-base and check if it is also a palindrome in another case. we are more familiar 10-base than k-base so we can use 10-base here. Sometimes 10-base is better and sometimes work with k-base is faster. For simplicity, I did not make any decision in the program but stick with 10-base.

Clearly, if we iterate over natural order and check if the number is palindrome in 10-base, it would be very slow. Instead, we can try to generate the palindrome. To do so, it only requires to try all possible "half" of the number. for example 1 -> 11, 2 -> 22, 3 -> 33 ... 10 -> 1001, 11 -> 1111. But please note, we have odd length palindrome and even length palindrome. e.g. 1 -> 1 or 1 -> 11, 12 -> 121 or 12 -> 1221. For the same "half", the odd-length case is less than the even-length case. Actually as long as the "half" has the same number of digits, the even-length case is larger. So for a given number digits, we can iterate all the odd-length cases and than we can go over the even-length cases.

When check with k-base palindrome, there are two methods, 1) get every digits in k - base. 2) compute the flipped/reversed k-base directly. I prefer the second. it is faster and with less-memory consumption.

```java
    private boolean isKPalindrome(long n, int k) {
        long[] digits = new long[(int) (Math.log(n)/Math.log(k)) + 1];
        int id = 0;
        while (n > 0) {
            digits[id++] = n % k;
            n /= k;
        }

        int left = 0;
        int right = id - 1;

        while (left < right>) {
            if (digits[left++] != digits[right--]) {
                return false;
            }
        }
        return true;
    }
```

```java
    private boolean isKPalindrome(long n, int k) {
        long reversed = 0;
        long temp = n;
        while (temp > 0) {
            reversed = reversed * k + (temp % k);
            temp /= k;
        }
        return reversed == n;
    }
```

In this implementation, I did not wrap the generate 10-base palindrome into a function, because I would like to check how many number I get and if that exceeds n. A loop is added, from 0 to 1, is just for odd and even cases to avoid duplicated code. 