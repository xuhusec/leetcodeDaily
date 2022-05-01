### [354.Russian-Doll-Envelopes](https://leetcode.com/problems/russian-doll-envelopes/)

#### Solution 1: Greedy (Sort + LIS)

The question has two dimension. We can sort one dimension first to reduce the complexity. With one dimension sorted, how can we check the other dimension? Clearly, the envelopes satisfied final maximum number of envelopes can be arranged as a subsequence of the sorted array. That because we need strict increment on both dimensions and the sort keep one dimension increased. We need the other dimension increased as well. So fo the other dimension, says height, We need to keep the relative order because the width needs increased as well. In a sequence, find the strict increasing subsequence is the Longest Increasing Subsequence problem [LC 300](https://leetcode.com/problems/longest-increasing-subsequence/). But for the same width, we should not consider large height would cover the small height. To eliminate such case, we can sort the height from high to low when the width is the same so that it would not be included in the LIS.  The next step is the find out the LIS in height array.

For LIS, we have to solutions. 1) DP (O(n<sup>2</sup>)) or 2) Greedy + Binary Search O(nlogn). I implement the second one in the example. The following is the DP soluition.

```java
    int[] dp = new int[envelopes.length];
    int ans = 1;
    for (int i = 0; i < envelopes.length; ++i) {
        dp[i] = 1;
        int cur = envelopes[i][1];
        // check all the previous evnlopes. if its width is smaller than the current one, we update the cur LIS as LIS ended at that envelope + 1. 
        for (int j = 0; j < i; ++j) {
            if (envelopes[j][1] < cur) {
                dp[i] = Math.max(1 + dp[j], dp[i]);
            }
        }
         ans = Math.max(ans, dp[i]);
    }
    return ans;
```


For the binary search solution, we try to build a LIS increase array. Perform the binary search on previous element, if it is greater than all the previous element, it would append to the end. If  it less than some element in the existing sequence, we replace the large element with the current element. In such way, it would allow build longer sequence, because we do not lose the any length and it is easier to append element in the new subsequence (from start to the replaced place) than before.