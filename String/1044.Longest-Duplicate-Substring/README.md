### [1044.Longest-Duplicate-Substring](https://leetcode.com/problems/longest-duplicate-substring/)

Given a string `s`, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.

Return **any** duplicated substring that has the longest possible length. If `s` does not have a duplicated substring, the answer is `""`.

Constraints:
2 <= s.length <= 3 * 10<sup>4</sup>
s consists of lowercase English letters.

#### Solution 1: Rolling Hash + binary search

The problem is the same as the question 1062. The only different is that it contains large data the s.length can be 3 * 10<sup>4</sup>. With this change, we have two problems.
1. the DP solution in 1062 will not work because that is O(n<sup>2</sup>)
2. the hash collision is more likely because there are more cases.

So, by the time of writting this artcile, the double hash solution still works. However, double hash reduce the possiblity but that does not eliminate the cases. A stronger test cases might still invalid this solution. The more accurate (and faster) solution would be [suffix automation](https://cp-algorithms.com/string/suffix-automaton.html). But it is kind of advanced algorithm (drived from trie). It might be added later. For details of double hash solution, one can check the 1062 solution    