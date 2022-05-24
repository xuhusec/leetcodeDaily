### [995.Minimum-Number-of-K-Consecutive-Bit-Flips](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/)

#### Solution 1: Diff Array

Start from Greedy
To flip the first zero to one, do we flip when we encounter the zero or do we flip at a previous position and the zero is in the range k? Clearly, we should flip at zero. Otherwise, say, we flip at i. We need to flip i again to make it to 1. If we flip at the same location. The original first zero become zero again. If we move the flip point to the left. We flip other 1 to zero. So we flip at first zero. And for the rest, we keep flip at first zero met.

That gives O(nk) algorithm. Iterate the array, for the first zero met, we flip the next k element and continue this process until all zero become one or the there are less than k elements to flip. The later gives -1 as answer.

Diff Array

The greedy algorithm would time out as n and k are large. How to reduce the time complexity? For a flip, we are changing a range. Maybe we can try the diff array. Unlike the normal diff array problem. Here we can diff on number of times flipped. For an element flip even times, its value is not changed. Otherwise, it is changed form 1 to 0 or from 0 to 1. We can keep a diff array. To add 1 when we met a zero and to add -1 for at the i + k position ((i + k - 1) - (i) + 1 = k elements). In the implementation, the adding 1 is directly added in presum and skip the diff array and flip is computed as nums[i] ^ (flips % 2). flips % 2 is only 1  for odd flips or 0 for even case. And nums[i] ^ 1 is flip the last digit. nums[i] ^ 0 does not change anything. An easy version can be found the following

```java
for (int i = 0; i < n; ++i) {
    flips += diff[i];
    int cur = flips % 2 == 0 ? nums[i] : 1 - nums[i];
    if (cur == 1) {
        continue;
    }
    if (i + k - 1 >= n) {
        return -1;
    }
    ans++;
    flips++;
    diff[i]++;
    diff[i + k]--;
}
```