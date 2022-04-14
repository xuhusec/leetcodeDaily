### [2223.Sum-of-Scores-of-Built-Strings](https://leetcode.com/problems/sum-of-scores-of-built-strings/)

#### Solution 1: Rolling Hash + binary search + prefix

The datasize is 10<sup>5</sup>. If the size is small, it would be an easy question. We can just have a O(n<sup>2</sup>) solution. For each s<sub>i</sub>, we start at character at i and try to find the max common prefix by comparing the prefix of the original string. s[0] ? s[i], s[1] ? s[i+1], s[2] ? s[i+2] etc. This would time out.

To use rolling hash would be a common technique in pattern matching, we can try this way. However, the length of common prefix varies. If we try all the possible common prefix. That would be O(n<sup>2</sup>) that is no better than the brute force way. Note for a string abcdef. we can the following
hash("abcdef") = hash("abc") * P^(len("abcdef") + len("abc")) + hash("def")

hash("def") = hash("abcdef") - hash("abc") * P^(len("abcdef")

So, we can use the rolling hash as prefix sum. This would provide a given hash in O(1) if we compute all the prefix hash of the original string and all P^n and save them into an array. But we still do not know the length for each starting point. If we try all the cases, it would be O(n<sup>2</sup>) in total. We can use binary search to find the max len with O(logn) (the total is O(nlogn)).

To avoid collision, double hash is used.

A non-false positive solution would be applying [Z Function](https://cp-algorithms.com/string/z-function.html).