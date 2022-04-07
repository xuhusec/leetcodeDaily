### [1062. Longest Repeating Substring](https://leetcode.com/problems/longest-repeating-substring/)

#### Solution 1: Rolling Hash + binary search

The brute force solution would require check the every possible substring and figure out if there is any repeating string. That would n<sup>2</sup> subtrings and each of them require O(n<sup>2</sup>) to check so it would be O(n<sup>4</sup>). Can we reduce it? This can be done with the string pattern match algorithm. We can check the rolling hash for each len and find out duplication. Computing rolling hash is based on the Rabin-Karp algorithm. For a given length, it is only O(n) to compute all the hash for all the substring. We can search from the longest possible length (n - 1) and stop early if we found a duplicated substring. That would be O(n<sup>2</sup>).

But we can do better. Note if the max length with duplicated string is m, we must can find duplicated string in m - 1 (by removing the last common character). Similiary, we can find m - 2, m - 3, ... 1. So we can perform binary search. If we found a valid length, it would be our lower bound (inclusive) for the longest length. Otherwise, it would be the higher bound (exclusive). In this way we can reduce the search to O(logn) So the total is O(nlogn).

For finding the duplicated string, we can compare the hash directly. But please note, that could have collision. So in my implemenetation, I compute two hashes for different P. This would reduce the possiblity of collision but it is only a mitgation. You may think of storing the substring itself but making substring is O(n). Also, Maybe due to the weak testcase, you actually can just use one hash function like the following.

```java
    private boolean containRepeating(char[] chars, int len) {
        if (len == 0) {
            return true;
        }
        long hash0 = 0;
        long highest0 = 1;

        Set<Long> set = new HashSet<>();
        for (int i = 0; i < chars.length; ++i) {
            int cur = chars[i] - 'a';
            if (i < len) {
                hash0 = (hash0 * P0 + cur) % MOD;
                highest0 = (highest0 * P0) % MOD;

                if (i == len - 1) {
                    set.add(hash0);
                }
                continue;
            }
            int toRemove = chars[i - len] - 'a';
            hash0 = (hash0 * P0 + MOD - toRemove * highest0 + cur) % MOD;
            if (!set.add(hash0)) {
                return true;
            }
        }
        return false;
    }
```

#### Solution 2: DP

This problem can be converted to a DP problem. We can try to find longest common substring from two string s1 and s2 but the range of indices of the substring is not the same. For this problem s1 == s2. Let's define dp as the longest common substring ending at i in s1 and ending at j in s2. 
if i != j and the chars1[i] == chars2[j], we would have dp[i][j] = dp[i-1][j-1] + 1. Otherwise dp[i][j] = 0. Please note we must have i != j. Otherwise it would be the length of the string and that is not repeating string. This requires a nest loop O(n<sup>2</sup>).